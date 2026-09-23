import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ahorcado extends JFrame {

    private record Palabra(String texto, String pista) {}

    private static final Palabra[] PALABRAS = {
            new Palabra("JAVA", "Lenguaje de programacion con el que esta hecho este juego"),
            new Palabra("ORDENADOR", "Maquina electronica que procesa informacion"),
            new Palabra("TECLADO", "Periferico usado para escribir"),
            new Palabra("PANTALLA", "Dispositivo de salida que muestra imagenes"),
            new Palabra("VIDEOJUEGO", "Programa interactivo pensado para divertirse"),
            new Palabra("INTERNET", "Red global de redes"),
            new Palabra("ALGORITMO", "Secuencia finita de pasos para resolver un problema"),
            new Palabra("PROGRAMA", "Conjunto de instrucciones que ejecuta una computadora"),
            new Palabra("COMPILADOR", "Traduce el codigo fuente a un formato ejecutable"),
            new Palabra("EXCEPCION", "Evento que puede interrumpir el flujo normal del programa")
    };

    private static final int MAX_FALLOS = 6;
    private static final String LETRAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    private final Random aleatorio = new Random();
    private final Set<Character> acertadas = new HashSet<>();
    private final Set<Character> falladas = new HashSet<>();
    private final ArrayList<JButton> botones = new ArrayList<>();

    private final JLabel etiquetaPista = new JLabel();
    private final JLabel etiquetaPalabra = new JLabel();
    private final JLabel etiquetaMensaje = new JLabel();
    private final PanelAhorcado panelDibujo = new PanelAhorcado();

    private String palabraSecreta = "";
    private boolean partidaTerminada = false;

    public Ahorcado() {
        setTitle("El Ahorcado - JDK 21");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crearInterfaz();
        nuevaPartida();
        setSize(820, 720);
        setMinimumSize(new Dimension(720, 620));
        setLocationRelativeTo(null);
    }

    private void crearInterfaz() {
        setLayout(new BorderLayout(10, 10));

        JPanel norte = new JPanel(new GridLayout(0, 1, 5, 5));
        norte.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel titulo = new JLabel("EL AHORCADO", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 30f));

        etiquetaPista.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaMensaje.setFont(etiquetaMensaje.getFont().deriveFont(Font.BOLD, 16f));

        norte.add(titulo);
        norte.add(etiquetaPista);

        etiquetaPalabra.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        etiquetaPalabra.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        centro.add(panelDibujo, BorderLayout.CENTER);
        centro.add(etiquetaPalabra, BorderLayout.SOUTH);

        JPanel teclado = new JPanel(new GridLayout(0, 9, 6, 6));
        teclado.setBorder(BorderFactory.createEmptyBorder(0, 15, 5, 15));

        for (char letra : LETRAS.toCharArray()) {
            JButton boton = new JButton(String.valueOf(letra));
            boton.setFocusPainted(false);
            boton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            boton.addActionListener(e -> {
                probarLetra(letra);
                boton.setEnabled(false);
            });
            botones.add(boton);
            teclado.add(boton);
        }

        JButton botonReiniciar = new JButton("Nueva partida");
        botonReiniciar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        botonReiniciar.addActionListener(e -> nuevaPartida());

        JPanel sur = new JPanel(new BorderLayout(8, 8));
        sur.add(etiquetaMensaje, BorderLayout.NORTH);
        sur.add(teclado, BorderLayout.CENTER);

        JPanel panelBotonera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonera.add(botonReiniciar);
        sur.add(panelBotonera, BorderLayout.SOUTH);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
    }

    private void nuevaPartida() {
        Palabra palabra = PALABRAS[aleatorio.nextInt(PALABRAS.length)];
        palabraSecreta = palabra.texto().toUpperCase();
        acertadas.clear();
        falladas.clear();
        partidaTerminada = false;

        for (JButton boton : botones) {
            boton.setEnabled(true);
        }

        etiquetaPista.setText("Pista: " + palabra.pista());
        etiquetaMensaje.setText("Pulsa una letra. Puedes fallar " + MAX_FALLOS + " veces.");
        etiquetaMensaje.setForeground(new Color(0, 90, 170));

        actualizarVista();
    }

    private void probarLetra(char letra) {
        if (partidaTerminada) {
            return;
        }

        if (acertadas.contains(letra) || falladas.contains(letra)) {
            return;
        }

        if (palabraSecreta.indexOf(letra) >= 0) {
            acertadas.add(letra);
        } else {
            falladas.add(letra);
        }

        if (falladas.size() >= MAX_FALLOS) {
            partidaTerminada = true;
            etiquetaMensaje.setText("Has perdido. La palabra era: " + palabraSecreta);
            etiquetaMensaje.setForeground(Color.RED);
            desactivarTeclado();
        } else if (palabraCompleta()) {
            partidaTerminada = true;
            etiquetaMensaje.setText("Has ganado. La palabra era: " + palabraSecreta);
            etiquetaMensaje.setForeground(new Color(0, 130, 0));
            desactivarTeclado();
        } else {
            etiquetaMensaje.setText("Fallos: " + falladas.size() + " de " + MAX_FALLOS);
            etiquetaMensaje.setForeground(new Color(0, 90, 170));
        }

        actualizarVista();
    }

    private boolean palabraCompleta() {
        for (char letra : palabraSecreta.toCharArray()) {
            if (Character.isLetter(letra) && !acertadas.contains(letra)) {
                return false;
            }
        }
        return true;
    }

    private void desactivarTeclado() {
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
    }

    private void actualizarVista() {
        StringBuilder texto = new StringBuilder();

        for (char letra : palabraSecreta.toCharArray()) {
            if (acertadas.contains(letra)) {
                texto.append(letra);
            } else {
                texto.append('_');
            }
            texto.append(' ');
        }

        etiquetaPalabra.setText(texto.toString().trim());
        panelDibujo.repaint();
    }

    private final class PanelAhorcado extends JPanel {

        private PanelAhorcado() {
            setBackground(new Color(247, 249, 252));
            setPreferredSize(new Dimension(520, 360));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            // Dibujo de la horca
            g2.setColor(new Color(88, 64, 42));
            g2.drawLine(60, 290, 380, 290);   // suelo
            g2.drawLine(120, 290, 120, 50);  // poste vertical
            g2.drawLine(120, 50, 260, 50);   // viga superior
            g2.drawLine(260, 50, 260, 85);   // cuerda

            int fallos = falladas.size();

            // Dibujo del personaje
            g2.setColor(new Color(25, 35, 90));

            if (fallos >= 1) {
                g2.drawOval(235, 85, 50, 50); // cabeza
            }

            if (fallos >= 2) {
                g2.drawLine(260, 135, 260, 205); // cuerpo
            }

            if (fallos >= 3) {
                g2.drawLine(260, 150, 220, 180); // brazo izquierdo
            }

            if (fallos >= 4) {
                g2.drawLine(260, 150, 300, 180); // brazo derecho
            }

            if (fallos >= 5) {
                g2.drawLine(260, 205, 220, 250); // pierna izquierda
            }

            if (fallos >= 6) {
                g2.drawLine(260, 205, 300, 250); // pierna derecha
            }

            // Cara si termina la partida
            if (partidaTerminada && falladas.size() >= MAX_FALLOS) {
                g2.setColor(Color.RED);

                // Ojos en forma de X
                g2.drawLine(248, 102, 256, 110);
                g2.drawLine(256, 102, 248, 110);
                g2.drawLine(264, 102, 272, 110);
                g2.drawLine(272, 102, 264, 110);

                // Boca triste
                g2.drawArc(245, 112, 30, 14, 180, 180);
            } else if (partidaTerminada && fallos >= 1) {
                g2.setColor(new Color(0, 130, 0));

                // Boca sonriente
                g2.drawArc(245, 110, 30, 14, 180, -180);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ahorcado().setVisible(true));
    }
}
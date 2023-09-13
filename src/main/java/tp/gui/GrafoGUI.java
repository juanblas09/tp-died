package tp.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class GrafoGUI extends JPanel {
    private Grafo<String> grafo = new Grafo<>();
    private Circle selectedCircle = null;
    private boolean dragging = false;

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw edges (lines) first
        for (Arista<String> arista : grafo.aristas) {
            Line line = arista.getLine();
            drawLine(g2d, line);
        }

        // Draw nodes (circles)
        for (Nodo<String> nodo : grafo.nodos) {
            Circle circle = nodo.getCirculo();
            drawCircle(g2d, circle);
        }

        g2d.dispose();
    }

    private void drawLine(Graphics2D g2d, Circle startCircle, Circle endCircle) {
        g2d.setColor(Color.BLACK);

        if (startCircle != null && endCircle != null) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(startCircle.getCentroX(), startCircle.getCentroY(), endCircle.getCentroX(), endCircle.getCentroY());
        }
    }


    private void drawLine(Graphics2D g2d, Line line) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

    private void drawCircle(Graphics2D g2d, Circle circle) {
        g2d.setColor(circle.getColor());
        g2d.fillOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
        g2d.setColor(circle.getBorderColor());
        g2d.drawOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
        g2d.setColor(Color.BLACK);
        g2d.drawString(circle.getLabel(), circle.getCentroX() - 5, circle.getCentroY() + 5);
    }

    public GrafoGUI() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (!isInsideCircle(event.getX(), event.getY())) {
                    deselectCircle();
                } else {
                    for (Nodo<String> nodo : grafo.nodos) {
                        Circle circle = nodo.getCirculo();
                        if (circle.contains(event.getX(), event.getY())) {
                            if (selectedCircle != null) {
                                selectedCircle.setColor(Color.RED);
                            }
                            selectedCircle = circle;
                            circle.setColor(Color.BLUE);
                            repaint();
                            return;
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }

            public void mousePressed(MouseEvent event) {
                if (!isInsideCircle(event.getX(), event.getY())) {
                    deselectCircle();
                } else {
                    for (Nodo<String> nodo : grafo.nodos) {
                        Circle circle = nodo.getCirculo();
                        if (circle.contains(event.getX(), event.getY())) {
                            if (selectedCircle != null) {
                                selectedCircle.setColor(Color.RED);
                            }
                            selectedCircle = circle;
                            circle.setColor(Color.BLUE);
                            dragging = true;
                            repaint();
                            return;
                        }
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if (dragging && selectedCircle != null) {
                    int newX = event.getX();
                    int newY = event.getY();

                    // Restrict the new position within the bounds of the JPanel
                    newX = Math.max(selectedCircle.getDiameter() / 2, Math.min(getWidth() - selectedCircle.getDiameter() / 2, newX));
                    newY = Math.max(selectedCircle.getDiameter() / 2, Math.min(getHeight() - selectedCircle.getDiameter() / 2, newY));

                    selectedCircle.move(newX, newY);
                    updateLines(selectedCircle);
                    repaint();
                }
            }
        });

    }

    private void deselectCircle() {
        if (selectedCircle != null) {
            selectedCircle.setColor(Color.RED);
            selectedCircle = null;
            repaint();
        }
    }

    private boolean isInsideCircle(int x, int y) {
        for (Nodo<String> nodo : grafo.nodos) {
            Circle circle = nodo.getCirculo();
            if (circle.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void addNodo(int x, int y, int diameter, String label) {
        grafo.agregarNodo(new Nodo<>(label, x, y, diameter));
        repaint();
    }

    public void addArista(String datoInicio, String datoFinal) {
        Optional<Nodo<String>> inicio =  grafo.nodos.stream()
                                        .filter(objeto -> objeto.getValor().equals(datoInicio))
                                        .findFirst();
        Optional<Nodo<String>> fin =  grafo.nodos.stream()
                                     .filter(objeto -> objeto.getValor().equals(datoFinal))
                                     .findFirst();
        if(inicio.isPresent() && fin.isPresent()){
            Nodo<String> nodoInicial = inicio.get();
            Nodo<String> nodoFinal = fin.get();

            Line line = new Line(nodoInicial.getX(), nodoInicial.getY(), nodoFinal.getX(), nodoFinal.getY());

            grafo.agregarArista(nodoInicial, nodoFinal, line);

            repaint();
        }
    }
    private Circle findCircleAt(int x, int y) {
        for (Nodo nodo : grafo.nodos) {
            Circle circle = nodo.getCirculo();
            if (circle.contains(x, y)) {
                return circle;
            }
        }
        return null;
    }
    private void updateLines(Circle circle) {
        for (Arista<String> arista : grafo.getAristas()) {
            if (arista.getNodoInicio().getValor().equals(circle.getLabel())) {
                Circle endCircle = arista.getNodoFinal().getCirculo();
                if (endCircle != null) {
                    arista.getLine().setStartX(circle.getCentroX());
                    arista.getLine().setStartY(circle.getCentroY());
                }
            }
            if (arista.getNodoFinal().getValor().equals(circle.getLabel())) {
                Circle startCircle = arista.getNodoInicio().getCirculo();
                if (startCircle != null) {
                    arista.getLine().setEndX(circle.getCentroX());
                    arista.getLine().setEndY(circle.getCentroY());
                }
            }
        }
    }



    private static class Line {
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public int getStartX() {
            return startX;
        }

        public int getStartY() {
            return startY;
        }

        public int getEndX() {
            return endX;
        }

        public int getEndY() {
            return endY;
        }

        public void setStartX(int centroX) {
            startX = centroX;
        }

        public void setStartY(int centroY) {
            startY = centroY;
        }

        public void setEndX(int centroX) {
            endX = centroX;
        }

        public void setEndY(int centroY) {
            endY = centroY;
        }
    }


    // Clase Circle
    private class Circle {
        private int x;
        private int y;
        private int diameter;
        private Color color;
        private Color borderColor;
        private String label;
        private Point centro;

        public Circle(int x, int y, int diameter, String label) {
            this.x = x - diameter / 2;
            this.y = y - diameter / 2;
            this.diameter = diameter;
            this.label = label;
            this.centro = new Point(x, y);
            this.color = Color.RED;
            this.borderColor = Color.GREEN;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDiameter() {
            return diameter;
        }

        public Color getColor() {
            return color;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public Point getCentro() {
            return centro;
        }

        public int getCentroX() {
            return centro.x;
        }

        public int getCentroY() {
            return centro.y;
        }

        public String getLabel() {
            return label;
        }

        public boolean contains(int x, int y) {
            int radius = diameter / 2;
            int centerX = this.x + radius;
            int centerY = this.y + radius;
            return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= radius;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void toggleColors() {
            if (color == Color.RED) {
                color = Color.BLUE;
                borderColor = Color.ORANGE;
            } else {
                color = Color.RED;
                borderColor = Color.GREEN;
            }
        }

        public void move(int x, int y) {
            int offsetX = x - this.x;
            int offsetY = y - this.y;
            this.x = x - diameter / 2;
            this.y = y - diameter / 2;
            this.centro = new Point(x, y);
        }
    }

    // Clase Grafo
    private class Grafo<T> {
        private List<Nodo<T>> nodos;
        private List<Arista<T>> aristas;

        public Grafo() {
            nodos = new ArrayList<>();
            aristas = new ArrayList<>();
        }

        public List<Nodo<T>> getNodos() {
            return nodos;
        }

        public List<Arista<T>> getAristas() {
            return aristas;
        }

        public void agregarNodo(Nodo<T> nodo) {
            if (!this.nodos.contains(nodo)) {
                this.nodos.add(nodo);
            }
        }


        public void agregarArista(Nodo<T> nodoInicio, Nodo<T> nodoFinal, Line line) {
            aristas.add(new Arista<>(nodoInicio, nodoFinal, line));
            nodos.add(nodoInicio);
            nodos.add(nodoFinal);
        }

    }

    // Clase Arista
    private class Arista<T> {
        private Nodo<T> nodoInicio;
        private Nodo<T> nodoFinal;

        private Line line;

        public Arista(Nodo<T> nodoInicio, Nodo<T> nodoFinal, Line line) {
            this.nodoInicio = nodoInicio;
            this.nodoFinal = nodoFinal;
            this.line = line;
        }

        public Nodo<T> getNodoInicio() {
            return nodoInicio;
        }

        public Nodo<T> getNodoFinal() {
            return nodoFinal;
        }

        public Line getLine() {
            return this.line;
        }
    }

    // Clase Nodo
    private class Nodo<T> {
        private T valor;
        private int x;
        private int y;
        Circle circulo;

        public Nodo(T valor, int x, int y, int radio) {
            this.valor = valor;
            this.x = x;
            this.y = y;
            this.circulo = new Circle(x, y, radio, valor.toString());
        }

        public T getValor() {
            return valor;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Circle getCirculo() {
            return circulo;
        }

        public void setCirculo(Circle circulo) {
            this.circulo = circulo;
        }
    }
    
    public void limpiarCanvas() {
        // Limpia todos los nodos y aristas del grafo
        grafo.getNodos().clear();
        grafo.getAristas().clear();
        
        // Limpia la selección actual
        selectedCircle = null;
        
        // Repinta el canvas
        repaint();
    }
}
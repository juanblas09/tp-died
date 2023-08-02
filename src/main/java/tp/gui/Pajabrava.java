package tp.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Pajabrava extends JPanel {
    private List<Circle> circles = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private Circle selectedCircle = null;
    private boolean selected = false;

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (Line line : lines) {
            drawLine(g2d, line);
        }
        for (Circle circle : circles) {
            drawCircle(g2d, circle);
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
    }

    public Pajabrava() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                for (Circle circle : circles) {
                    if (circle.contains(event.getX(), event.getY())) {
                        if (selectedCircle != null) {
                            selectedCircle.setColor(Color.RED);
                        }
                        circle.setColor(Color.BLUE);
                        selectedCircle = circle;
                        repaint();
                        return; // Exit the loop after handling the click
                    }
                }
                if (selectedCircle != null) {
                    selectedCircle.setColor(Color.RED);
                    selectedCircle = null;
                    repaint();
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (selected) {
                    Circle endCircle = findCircleAt(e.getX(), e.getY());
                    if (endCircle != null && endCircle != selectedCircle) {
                        lines.add(new Line(selectedCircle, endCircle));
                        repaint();
                    }
                }
                selected = false;
            }

            public void mousePressed(MouseEvent event) {
                for (Circle circle : circles) {
                    if (circle.contains(event.getX(), event.getY())) {
                        selected = true;
                        selectedCircle = circle;
                        break;
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if (selected && selectedCircle != null) {
                    selectedCircle.move(event.getX(), event.getY());
                    updateLines(selectedCircle);
                    repaint();
                }
            }
        });
    }

    public void addCircle(int x, int y, int diameter) {
        circles.add(new Circle(x, y, diameter));
        repaint();
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        Circle startCircle = findCircleAt(x1, y1);
        Circle endCircle = findCircleAt(x2, y2);

        if (startCircle != null && endCircle != null) {
            lines.add(new Line(startCircle, endCircle));
            repaint();
        }
    }

    private Circle findCircleAt(int x, int y) {
        for (Circle circle : circles) {
            if (circle.contains(x, y)) {
                return circle;
            }
        }
        return null;
    }

    private void updateLines(Circle circle) {
        for (Line line : lines) {
            if (line.getStartCircle() == circle) {
                line.setStartPoint(circle.getCentro());
            }
            if (line.getEndCircle() == circle) {
                line.setEndPoint(circle.getCentro());
            }
        }
    }

    private static class Circle {
        private int x;
        private int y;
        private int diameter;
        private Color color;
        private Color borderColor;
        private Point centro;

        public Circle(int x, int y, int diameter) {
            this.x = x - diameter / 2;
            this.y = y - diameter / 2;
            this.diameter = diameter;
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

    private static class Line {
        private Point startPoint;
        private Point endPoint;
        private Circle startCircle;
        private Circle endCircle;

        public Line(Circle startCircle, Circle endCircle) {
            this.startCircle = startCircle;
            this.endCircle = endCircle;
            this.startPoint = startCircle.getCentro();
            this.endPoint = endCircle.getCentro();
        }

        public Point getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(Point startPoint) {
            this.startPoint = startPoint;
        }

        public Point getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(Point endPoint) {
            this.endPoint = endPoint;
        }

        public int getStartX() {
            return startPoint.x;
        }

        public int getStartY() {
            return startPoint.y;
        }

        public int getEndX() {
            return endPoint.x;
        }

        public int getEndY() {
            return endPoint.y;
        }

        public Circle getStartCircle() {
            return startCircle;
        }

        public Circle getEndCircle() {
            return endCircle;
        }
    }
}

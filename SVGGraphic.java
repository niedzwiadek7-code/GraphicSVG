import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class SVGGraphic extends JPanel implements MouseListener {
    Points points = new Points();
    BufferedImage svg;
    int pointSize = 5;

    public SVGGraphic() {
        super();
        setSize(new Dimension(600, 600));
        clear();
        addMouseListener(this);
    }

    public void setSize(Dimension r)
    {
        svg = new BufferedImage((int)r.getWidth(), (int)r.getHeight(), BufferedImage.TYPE_INT_RGB);
        setPreferredSize(r);
        setMaximumSize(r);
    }

    public void clear()
    {
        Graphics2D g = (Graphics2D) svg.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, svg.getWidth(), svg.getHeight());
        //ustalenie obramowania
        setBorder(BorderFactory.createLineBorder(Color.black));
        repaint();
    }

    public void operation(Point[] point) {
        clear();

        this.points.change(point);

        for (int j = 0; j < points.i; j++) {
            drawPoint(points.get(j));
        }

    }

    public void brokenLines() {
        for (int j = 0; j < points.i - 1; j++) {
            drawLine(points.get(j), points.get(j + 1));
        }
    }

    private void drawLine(Point from, Point to) {
        Graphics2D g = (Graphics2D) svg.getGraphics();
        g.setColor(Color.black);
        g.drawLine(from.x, from.y, to.x, to.y);
        repaint();
    }

    public void bezierCurve() {
        int N = Integer.parseInt(
                JOptionPane.showInputDialog("Podaj ilość punktów, którą chcesz osiągnąć:")
        );

        Point[] R = new Point[N];
        Point[] Q = new Point[N];
        Point[] result = new Point[N];
        int n = points.i;
        double m;
        double t = 1.0 / N;

        for (int j = 0; j < N; j++) {
            if (n >= 0) System.arraycopy(points.point, 0, R, 0, n);
            m = n;
            while (m > 0) {
                for (int k = 0; k < m - 1; k++) {
                    Q[k] = new Point();
                    Q[k].x = (int) (R[k].x + t * (R[k + 1].x - R[k].x));
                    Q[k].y = (int) (R[k].y + t * (R[k + 1].y - R[k].y));
                }

                m--;

                for (int k = 0; k < m; k++) {
                    R[k] = Q[k];
                }
            }
            result[j] = R[0];
            t += 1.0 / N;
        }

        drawLine(points.get(0), result[0]);

        for (int j = 0; j < N - 1; j++) {
            drawLine(result[j], result[j + 1]);
        }
    }

    public void drawPoint(Point p) {
        Graphics2D g = (Graphics2D) svg.getGraphics();
        g.setColor(Color.black);
        g.fillOval(p.x - pointSize, p.y - pointSize, 2 * pointSize, 2 * pointSize);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(), e.getY());
        if (checkArray(p)) {
            drawPoint(p);
            points.add(p);
        }
    }

    private boolean checkArray(Point p) {
        for (int j = 0; j < points.i; j++) {
            if (points.get(j).x == p.x && points.get(j).y == p.y) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //wyrysowanie naszego płótna na panelu
        g2d.drawImage(svg, 0, 0, this);
    }
}
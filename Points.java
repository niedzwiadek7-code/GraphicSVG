import javax.swing.*;
import java.awt.*;

public class Points extends JFrame {
    public Point[] point;
    public int i; // iterator of array point

    JTextArea textArea;

    public Points() {
        super("Punkty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        setResizable(false);

        // create text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea);
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 30));
        printPoints();

        matchTheContent();
        setVisible(true);

        point = new Point[100000];
        i = 0;
    }

    public void add(Point point) {
        this.point[i] = point;
        i++;
        printPoint(point);
    }
    public void change(Point[] points) {
        point = points;
        printPoints();
    }

    public Point get(int i) {
        return point[i];
    }

    public void matchTheContent() {
        pack();
        setLocationRelativeTo(null);
    }

    public void printPoints() {
        textArea.setText(null);
        textArea.append("Punkty: \n");
        for (int j = 0; j < i; j++) {
            textArea.append("Punkt " + (j + 1) + ": " + point[j].x + ", " + point[j].y + "\n");
        }
        matchTheContent();
    }

    public void printPoint(Point p) {
        textArea.append("Punkt " + i + ": " + p.x + ", " + p.y + "\n");
        matchTheContent();
    }
}

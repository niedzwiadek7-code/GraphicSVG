import javax.swing.*;
import java.awt.*;

public class Transformation extends JFrame {
    private double[][] matrix;

    SVGGraphic svg;
    JTextArea textArea;

    public Transformation(SVGGraphic svg) {
        super("Macierz przekształceń");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        this.matrix = new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        this.svg = svg;

        // create text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        printMatrix();

        matchTheContent();
        setVisible(true);
    }

    public void transform(Point[] pointOld, int count) {
        Point[] pointNew = new Point[10000];

        for (int i = 0; i < count; i++) {
            double [][] resultMatrix = MatrixOperation.multiply(new double[][]{{pointOld[i].x, pointOld[i].y, 1}}, this.matrix);
//            MatrixOperation.printMatrix(resultMatrix);
            int px = (int) Math.round(resultMatrix[0][0]);
            int py = (int) Math.round(resultMatrix[0][1]);

            pointNew[i] = new Point(px, py);
//            System.out.println(pointNew[i].x + " " + pointNew[i].y);
        }

        svg.operation(pointNew);
        reset();
    }

    public void translation() {
        double sx = Double.parseDouble(
                JOptionPane.showInputDialog("Podaj dane dla współrzędnej x:")
        );
        double sy = Double.parseDouble(
                JOptionPane.showInputDialog("Podaj dane dla współrzędnej y:")
        );

        double[][] me = new double[][]{{1, 0, 0}, {0, 1, 0}, {sx, sy, 1}};
        this.matrix = MatrixOperation.multiply(this.matrix, me);

        printMatrix();
    }

    public void scale() {
        double sx = Double.parseDouble(
                JOptionPane.showInputDialog("Podaj dane dla współrzędnej x:")
        );
        double sy = Double.parseDouble(
                JOptionPane.showInputDialog("Podaj dane dla współrzędnej y:")
        );

        double[][] me = new double[][]{{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};
        this.matrix = MatrixOperation.multiply(this.matrix, me);

        printMatrix();
    }

    public void rotation() {
        double angle = Double.parseDouble(
                JOptionPane.showInputDialog("Podaj kąt:")
        ) / 180.0 * Math.PI;

        double[][] me = new double[][]{{Math.cos(angle), Math.sin(angle), 0}, {-Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}};
        this.matrix = MatrixOperation.multiply(this.matrix, me);

        printMatrix();
    }

    public void reset() {
        this.matrix = new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        printMatrix();
    }

    public void matchTheContent() {
        pack();
        setLocationRelativeTo(null);
    }

    public void printMatrix() {
        textArea.setText(null);
        textArea.append("Macierz:\n");

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                textArea.append(String.format("%5f", matrix[i][j]) + "    ");
            }
            textArea.append("\n");
        }
    }
}

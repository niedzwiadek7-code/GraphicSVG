import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Window extends JFrame implements ActionListener {

    SVGGraphic svg = new SVGGraphic();
    MenuBar menu = new MenuBar();
    Transformation transformationMatrix = new Transformation(svg);

    public Window() {
        super("Grafika komputerowa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        setJMenuBar(menu);
        add(svg);
        matchTheContent();
        setUpListener();
        setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Rotacja" -> transformationMatrix.rotation();
            case "Skalowanie" -> transformationMatrix.scale();
            case "Przesunięcie" -> transformationMatrix.translation();
            case "Połącz punkty" -> {
                transformationMatrix.transform(svg.points.point, svg.points.i);
                matchTheContent();
                svg.brokenLines();
            }
            case "Krzywa Beziera" -> {
                transformationMatrix.transform(svg.points.point, svg.points.i);
                matchTheContent();
                svg.bezierCurve();
            }
            case "Usuń punkty" -> {
                svg.points.i = 0;
                svg.clear();
                matchTheContent();
            }
        }
    }

    public void matchTheContent() {
        pack();
        setLocationRelativeTo(null);
    }

    public void setUpListener() {
        menu.rotation.addActionListener(this);
        menu.scale.addActionListener(this);
        menu.translation.addActionListener(this);
        menu.connectPoints.addActionListener(this);
        menu.bezierCurve.addActionListener(this);
        menu.reset.addActionListener(this);
    }
}

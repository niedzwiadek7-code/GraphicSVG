import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class MenuBar extends JMenuBar
{
    JMenu edit = new JMenu("Operacje");

    // edit
    JMenuItem rotation = new JMenuItem("Rotacja");
    JMenuItem scale = new JMenuItem("Skalowanie");
    JMenuItem translation = new JMenuItem("Przesunięcie");
    JMenuItem connectPoints = new JMenuItem("Połącz punkty");
    JMenuItem bezierCurve = new JMenuItem("Krzywa Beziera");
    JMenuItem reset = new JMenuItem("Usuń punkty");

    public MenuBar()
    {
        // menu Operacje Graficzne
        edit.add(rotation);
        edit.add(scale);
        edit.add(translation);
        edit.add(new JSeparator());
        edit.add(connectPoints);
        edit.add(bezierCurve);
        edit.add(new JSeparator());
        edit.add(reset);
        add(edit);
    }
}

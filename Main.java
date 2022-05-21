import javax.swing.*;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // TODO odpowiednie paddingi dla tabeli
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window();
            }
        });
    }
}
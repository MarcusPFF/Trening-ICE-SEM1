package JFrame;

import javax.swing.*;

public class gui {
    JFrame frame = new JFrame();

    public void runGUI() {
        frame.setTitle("Trening - App for Muscle Growth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}

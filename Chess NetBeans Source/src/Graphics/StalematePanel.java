package Graphics;

import static Graphics.Main.DIMENSION;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StalematePanel extends JPanel{
    public StalematePanel(){
        JLabel label = new JLabel("<html><h1>Stalemate</h1></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);
    }
    
    public static void main(String[] args) {
        JPanel p = new StalematePanel();       
        JFrame frame = new JFrame();
        frame.add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DIMENSION, DIMENSION);
        frame.setVisible(true);
        
    }
}

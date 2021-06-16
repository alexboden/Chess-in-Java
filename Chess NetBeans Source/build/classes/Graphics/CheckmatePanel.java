package Graphics;

import Board.Turn;
import General.Colour;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CheckmatePanel extends JPanel {
    CheckmatePanel(){
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        String c = (Turn.getColour().equals(Colour.BLACK)) ? "White" : "Black";
        label.setText("<html><h1>" +  c + " wins by Checkmate");
        add(label);
    }
}

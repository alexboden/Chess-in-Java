package Graphics;

import Board.Board;
import Board.Turn;
import General.Colour;
import static Graphics.Main.DIMENSION;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

    Menu() {
        JMenu menu = new JMenu("Menu");
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        
        JMenuItem link = new JMenuItem("Link to Source Code");
        link.addActionListener((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/alexboden/chess-in-java"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                
        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener((ActionEvent e) -> {
            if(Turn.turn.equals(Colour.BLACK)){
                try {
                    Turn.changeTurn();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Main.container.removeAll();
            Main.container.add(Main.boardPanel);
            Board.setUpStartingPosition();
            BoardPanel.resetPieces(Board.boardMap, BoardPanel.getButtons());
            BoardPanel.enableButtons();
            for(int i = 0; i < 64; i++)ClickListener.resetColor(i);
            Main.frame.setSize(DIMENSION, DIMENSION);
        });

        
        file.add(newGame);
        file.add(link);
        file.add(exit);
        add(file);
    }
}

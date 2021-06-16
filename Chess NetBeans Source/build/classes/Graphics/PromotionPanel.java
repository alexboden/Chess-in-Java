package Graphics;

import Board.Board;
import Board.Move;
import General.Colour;
import Pieces.Bishop;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class PromotionPanel extends JPanel {

    public PromotionPanel(Move move, Colour colour) {
        
        JLabel label = new JLabel();
        label.setText("<html><h3>Promote to:</h3></html>");
        add(label);
        ArrayList<JButton> choices = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            choices.add(new JButton());
            choices.get(i).setOpaque(true);
            choices.get(i).setBorderPainted(false);
            add(choices.get(i));
        }
        if (colour.equals(Colour.BLACK)) {
            choices.get(0).setIcon(BoardPanel.BLACK_QUEEN_ICON);
            choices.get(1).setIcon(BoardPanel.BLACK_ROOK_ICON);
            choices.get(2).setIcon(BoardPanel.BLACK_BISHOP_ICON);
            choices.get(3).setIcon(BoardPanel.BLACK_KNIGHT_ICON);
        }
        else{
            choices.get(0).setIcon(BoardPanel.WHITE_QUEEN_ICON);
            choices.get(1).setIcon(BoardPanel.WHITE_ROOK_ICON);
            choices.get(2).setIcon(BoardPanel.WHITE_BISHOP_ICON);
            choices.get(3).setIcon(BoardPanel.WHITE_KNIGHT_ICON);
        }
        choices.get(0).addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e) { 
          Piece pawn = Board.boardMap.get(move.getMovedPiece().getPiecePosition());
            Board.boardMap.remove(pawn.getPiecePosition());
            if (pawn.getPieceColour().equals(Colour.BLACK)) {
                Board.boardMap.put(move.getDestination(), new Queen(move.getDestination(), Colour.BLACK));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.BLACK_QUEEN_ICON);
            }
            else{
                Board.boardMap.put(move.getDestination(), new Queen(move.getDestination(), Colour.WHITE));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.WHITE_QUEEN_ICON);
            }
            try {
                Main.removePromotionPanel();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        choices.get(1).addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e) { 
          Piece pawn = Board.boardMap.get(move.getMovedPiece().getPiecePosition());
            Board.boardMap.remove(pawn.getPiecePosition());
            if (pawn.getPieceColour().equals(Colour.BLACK)) {
                Board.boardMap.put(move.getDestination(), new Rook(move.getDestination(), Colour.BLACK, true));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.BLACK_ROOK_ICON);
            }
            else{
                Board.boardMap.put(move.getDestination(), new Rook(move.getDestination(), Colour.WHITE, true));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.WHITE_ROOK_ICON);
            }
            try {
                Main.removePromotionPanel();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }});
        choices.get(2).addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e) { 
           Piece pawn = Board.boardMap.get(move.getMovedPiece().getPiecePosition());
            Board.boardMap.remove(pawn.getPiecePosition());
            if (pawn.getPieceColour().equals(Colour.BLACK)) {
                Board.boardMap.put(move.getDestination(), new Bishop(move.getDestination(), Colour.BLACK));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.BLACK_BISHOP_ICON);
            }
            else{
                Board.boardMap.put(move.getDestination(), new Bishop(move.getDestination(), Colour.WHITE));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.WHITE_BISHOP_ICON);
            }
            try {
                Main.removePromotionPanel();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        choices.get(3).addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e) { 
          Piece pawn = Board.boardMap.get(move.getMovedPiece().getPiecePosition());
            Board.boardMap.remove(pawn.getPiecePosition());
            if (pawn.getPieceColour().equals(Colour.BLACK)) {
                Board.boardMap.put(move.getDestination(), new Knight(move.getDestination(), Colour.BLACK));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.BLACK_KNIGHT_ICON);
            }
            else{
                Board.boardMap.put(move.getDestination(), new Knight(move.getDestination(), Colour.WHITE));
                ClickListener.setBothIcons(move.getDestination(), BoardPanel.WHITE_KNIGHT_ICON);
            }
            try {
                Main.removePromotionPanel();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        this.setLayout(new GridLayout(0, 1));
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}

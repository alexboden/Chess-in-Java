package Graphics;

import Board.Board;
import Board.ExecuteMove;
import Board.Move;
import General.Colour;
import General.PieceType;
import General.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;

public class ClickListener implements ActionListener {

    static ArrayList<JButton> buttons = BoardPanel.getButtons();
    static ArrayList<Move> possibleMoves = new ArrayList<>();
    static int lastSelected = -1;

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 64; i++) {
            if (e.getSource().equals(buttons.get(i))) {
                if (lastSelected != -1) {
                    resetColor(lastSelected);
                }
                
                buttons.get(i).setBackground(BoardPanel.SELECTED_COLOUR);
                try {
                    possibleMoves = Main.getPossibleMoves(lastSelected);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ClickListener.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (Move possibleMove : possibleMoves) {
                    resetColor(possibleMove.getDestination());
                }
                
                
                for (Move possibleMove : possibleMoves) {
                    if (possibleMove.getDestination() == i) {                
                        try {
                            ExecuteMove.executeMove(possibleMove);
                        } catch (Exception ex) {
                            Logger.getLogger(ClickListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return;
                    }
                }
                
                lastSelected = i;                                
                
                try {
                    possibleMoves = Main.getPossibleMoves(i);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ClickListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(Board.boardMap.containsKey(i)){
                    if(Board.boardMap.get(i).getPieceType().equals(PieceType.PAWN)){
                        if(Board.boardMap.get(i).getPieceColour().equals(Colour.WHITE) && Utils.FIRST_ROW[i]){
                        }
                        else if(Board.boardMap.get(i).getPieceColour().equals(Colour.BLACK) && Utils.EIGHTH_ROW[i]){
                        }
                        else{
                            for (Move possibleMove : possibleMoves) {
                                buttons.get(possibleMove.getDestination()).setBackground(BoardPanel.POTENTIAL_MOVE_COLOUR);
                            }    
                        }
                    }
                    else{
                        for (Move possibleMove : possibleMoves) {
                            buttons.get(possibleMove.getDestination()).setBackground(BoardPanel.POTENTIAL_MOVE_COLOUR);
                        }  
                    }
                }
                
            }
        }
    }

    public static void resetColor(int i) {
        if (Utils.squareColors[i]) {
            buttons.get(i).setBackground(BoardPanel.WHITE_COLOUR);
        } else {
            buttons.get(i).setBackground(BoardPanel.BLACK_COLOUR);
        }
    }

    public static void setBothIcons(int i, Icon icon) {
        buttons.get(i).setIcon(icon);
        buttons.get(i).setPressedIcon(icon);
    }
    
    public static void clearPossibleMoveSquares(){
         for (int i = 0; i < 64; i++) {
            if(buttons.get(i).getBackground().equals(BoardPanel.SELECTED_COLOUR)){
               resetColor(i);                 
            }
        }
    }
    
    public static void clearPossibleMoves(){
        possibleMoves.clear();
    }
        
            
    public static int getLastSelected(){
        return lastSelected;
    }
    
    public static void setLastSelected(int i) {
        resetColor(lastSelected);
        lastSelected = i;
    }
}

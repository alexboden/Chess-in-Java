package Graphics;

import Board.Board;
import General.Colour;
import General.Utils;
import Pieces.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
    final static Color BLACK_COLOUR = new Color(225, 195, 150);
    final static Color WHITE_COLOUR = new Color(158, 108, 35);
    final static Color SELECTED_COLOUR = new Color(225, 60, 60);
    final static Color POTENTIAL_MOVE_COLOUR = new Color(225, 130, 130);
    final static Color LAST_MOVE_COLOUR = new Color(213, 222, 82);
    
    final boolean[] squareColors = Utils.squareColors;
    final static int DIMENSION = Main.DIMENSION;

    public final static Icon WHITE_KING_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whiteking.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_KING_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackking.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon WHITE_QUEEN_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whitequeen.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_QUEEN_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackqueen.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon WHITE_ROOK_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whiterook.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_ROOK_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackrook.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon WHITE_BISHOP_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whitebishop.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_BISHOP_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackbishop.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon WHITE_KNIGHT_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whiteknight.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_KNIGHT_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackknight.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon WHITE_PAWN_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/whitepawn.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    final public static Icon BLACK_PAWN_ICON = resizeIcon(new ImageIcon(BoardPanel.class.getResource("/Resources/blackpawn.png")), DIMENSION/8 - 15, DIMENSION/8 - 15);
    

    static ArrayList<JButton> buttons = new ArrayList<>();

    public BoardPanel() {
        Board.setUpStartingPosition();
        Map<Integer, Piece> boardMap = Board.getBoardMap();
        
        for(int i = 0; i < 64; i++){
            buttons.add(new JButton());
        }

        for (int i = 0; i < 64; i++) {
            if(squareColors[i]) buttons.get(i).setBackground(WHITE_COLOUR); 
            else buttons.get(i).setBackground(BLACK_COLOUR);
            buttons.get(i).setOpaque(true);
            buttons.get(i).setBorderPainted(false);
            add(buttons.get(i));
            ClickListener clickListener = new ClickListener();
            buttons.get(i).addActionListener((ActionListener) clickListener);
        }

        resetPieces(boardMap, buttons);
        setLayout(new GridLayout(8, 8)); 
        setVisible(true);      
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
    }

    public static ArrayList<JButton> getButtons(){
        return BoardPanel.buttons;
    }
    
    public static void disableButtons(){
        for(JButton button : buttons){
            button.setEnabled(false);
        }
    }
    
     public static void enableButtons(){
        for(JButton button : buttons){
            button.setEnabled(true);
        }
    }
      
      
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }


    public static void resetPieces(Map<Integer, Piece> boardMap, ArrayList<JButton> buttons){
        for(int i = 0; i < 64; i++){
            buttons.get(i).setIcon(null);
            buttons.get(i).setIcon(null);
            if(boardMap.containsKey(i)){
                ImageIcon piece = null;
                if(boardMap.get(i).getPieceColour().equals(Colour.WHITE)){
                    switch (boardMap.get(i).getPieceType()) {
                        case PAWN:
                            piece = (ImageIcon) WHITE_PAWN_ICON;
                            break;
                        case ROOK:
                            piece = (ImageIcon) WHITE_ROOK_ICON;
                            break;
                        case KNIGHT:
                            piece = (ImageIcon) WHITE_KNIGHT_ICON;
                            break;
                        case BISHOP:
                            piece = (ImageIcon) WHITE_BISHOP_ICON;
                            break;
                        case QUEEN:
                            piece = (ImageIcon) WHITE_QUEEN_ICON;
                            break;
                        case KING:
                            piece =  (ImageIcon) WHITE_KING_ICON;
                            break;
                        default:
                            break;
                    }
                }

                else {
                    switch (boardMap.get(i).getPieceType()) {
                        case PAWN:
                            piece = (ImageIcon) BLACK_PAWN_ICON;
                            break;
                        case ROOK:
                            piece = (ImageIcon) BLACK_ROOK_ICON;
                            break;
                        case KNIGHT:
                            piece = (ImageIcon) BLACK_KNIGHT_ICON;
                            break;
                        case BISHOP:
                            piece = (ImageIcon) BLACK_BISHOP_ICON;
                            break;
                        case QUEEN:
                            piece = (ImageIcon) BLACK_QUEEN_ICON;
                            break;
                        case KING:
                            piece = (ImageIcon) BLACK_KING_ICON;
                            break;
                        default:
                            break;
                    }
                }
                Icon resizedPiece = resizeIcon(piece, DIMENSION/8 - 15, DIMENSION/8 - 15);
                buttons.get(i).setIcon(resizedPiece);
                buttons.get(i).setPressedIcon(resizedPiece);
                ClickListener.resetColor(i);
            }
        }
    }
}
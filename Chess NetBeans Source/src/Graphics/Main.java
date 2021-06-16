package Graphics;

import Board.Board;
import Board.Move;
import Board.Turn;
import Board.LegalMoves;
import General.Colour;
import Pieces.King;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    
    static JPanel container = new JPanel();
    static final Turn turn = new Turn(Colour.WHITE);
    static final int DIMENSION = 650;
    static final BoardPanel boardPanel = new BoardPanel();
    static final Menu menu = new Menu();
    static JFrame frame = new JFrame();

    public static void main(String[] args) throws CloneNotSupportedException {
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        frame.setTitle("Chess in Java");
        frame.setJMenuBar(menu);
        container.add(boardPanel);
        frame.setResizable(false);
        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DIMENSION, DIMENSION);
        frame.setVisible(true);
    }

    public static ArrayList<Move> getPossibleMoves(int i) throws CloneNotSupportedException {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        if (!Board.getBoardMap().containsKey(i)) {
            return possibleMoves;
        }
        if (!Turn.getColour().equals(Board.getBoardMap().get(i).getPieceColour())) {
            return possibleMoves;
        }
        King king = Board.getKing(Turn.getColour());
        for (Move move : LegalMoves.getAllMoves(king)) {
            if (move.getMovedPiece().getPiecePosition() == i) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
    
    public static void addPromotionPanel(Colour colour, Move move){
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        if(colour.equals(Colour.WHITE)) container.add(new PromotionPanel(move, Colour.WHITE));
        else container.add(new PromotionPanel(move, Colour.BLACK));
        frame.setSize(DIMENSION + 100, DIMENSION);
        frame.add(container);
        BoardPanel.disableButtons();
    }
    
    public static void removePromotionPanel() throws CloneNotSupportedException, InterruptedException{
        Turn.changeTurn();
        BoardPanel.enableButtons();
        container.remove(1);
        frame.setSize(DIMENSION, DIMENSION);
    }
    
    public static void addCheckmatePanel() throws InterruptedException{
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        BoardPanel.disableButtons();
        frame.setSize(DIMENSION, DIMENSION + 75);
        container.add(new CheckmatePanel());
    }
    
     public static void addStalematePanel() throws InterruptedException{
        frame.setSize(DIMENSION, DIMENSION + 75);
        BoardPanel.disableButtons();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(new StalematePanel());
    }
}
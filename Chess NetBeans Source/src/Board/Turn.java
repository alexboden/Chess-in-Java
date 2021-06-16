package Board;

import General.Colour;
import Graphics.Main;
import Pieces.Piece;
import java.util.ArrayList;

public class Turn {

    public static Colour turn;
    
    static Colour cpuColour = Colour.BLACK;

    public Turn(Colour turn) {
        Turn.turn = turn;
    }

    public static void changeTurn() throws CloneNotSupportedException, InterruptedException {
        Turn.turn = Turn.turn.equals(Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        if(LegalMoves.getAllMoves(Board.getKing(Turn.turn)).isEmpty()){
            ArrayList<Piece> opponentPieces = new ArrayList<>();
            if(Turn.turn.equals(Colour.WHITE)){
                opponentPieces = Board.getPieces(Colour.BLACK);
            }
            else{
               opponentPieces = Board.getPieces(Colour.WHITE);
            }
            
            for(Piece piece : opponentPieces){
                ArrayList<Move> moves = piece.calculateLegalMoves();
                for(Move move : moves){
                    if(Board.getKing(Turn.turn).getPiecePosition() == move.getDestination()){
                        Main.addCheckmatePanel();
                        return;
                    }
                }
            }
            Main.addStalematePanel();
        }
       
        if(Turn.turn.equals(cpuColour)){
            ExcuteRandomLegalMove.excuteRandomLegalMove();
        }
        
    }

    public static Colour getColour() {
        return Turn.turn;
    }
}

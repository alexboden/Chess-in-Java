package Board;

import Pieces.King;
import Pieces.Piece;
import General.Colour;
import General.MoveType;
import General.PieceType;
import General.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LegalMoves {

    public static ArrayList<Move> getAllMoves(King king) throws CloneNotSupportedException {
        ArrayList<Piece> pieces = Board.getPieces(king.getPieceColour());
        ArrayList<Move> validMoves = new ArrayList<>();
        ArrayList<Move> invalidMoves = new ArrayList<>();
        Map<Integer, Piece> savedPosition = cloneBoardMap(Board.getBoardMap());
        Map<Piece, Integer> savedPiecePositions = new HashMap<>();

        pieces.forEach(piece -> {
            savedPiecePositions.put(piece, piece.getPiecePosition());
        });

        for (Piece piece : pieces) {
            ArrayList<Move> moves = piece.calculateLegalMoves();
            if(piece.getPieceType().equals(PieceType.PAWN)){
                if(enPassant(piece) != null){
                    moves.add(enPassant(piece));
                }
            }
            for (Move move : moves) {
                validMoves.add(move);
                Board.setBoardMap((cloneBoardMap(savedPosition)));
                int last = savedPiecePositions.get(piece);
                piece.setPiecePosition(move.getDestination());
                if(move.getMoveType().equals(MoveType.ENPASSANT)){
                    if(piece.getPieceColour().equals(Colour.WHITE)){
                        Board.boardMap.remove(move.getDestination() + 8);
                    }
                    else{
                        Board.boardMap.remove(move.getDestination() - 8);
                    }
                }
                Board.setPiece(piece, last);
                ArrayList<Piece> opponentPieces = (king.getPieceColour().equals(Colour.WHITE)) ? Board.getPieces(Colour.BLACK) : Board.getPieces(Colour.WHITE);
                for (Piece opponentPiece : opponentPieces) {
                    ArrayList<Move> opponentMoves = opponentPiece.calculateLegalMoves();
                    for (Iterator<Move> it = opponentMoves.iterator(); it.hasNext();) {
                        Move opponentMove = it.next();
                        if (opponentMove.getDestination() == Board.getKing(king.getPieceColour()).getPiecePosition()) {
                            invalidMoves.add(move);
                            break;
                        }
                    }
                }
                piece.setPiecePosition(last);
                Board.setBoardMap(cloneBoardMap(savedPosition));
            }
        }
        Board.setBoardMap(cloneBoardMap(savedPosition));
//        System.out.println("VALID");
//        for (Move move : validMoves) {
//            System.out.println(move.getMovedPiece().getPieceColour() + " " + move.getMovedPiece() + " " + move.getDestination());
//        }
//        System.out.println(validMoves.size());
//        System.out.println("INVALID");
//        for (Move move : invalidMoves) {
//            System.out.println(move.getMovedPiece().getPieceColour() + " " + move.getMovedPiece() + " " + move.getDestination());
//            if (validMoves.contains(move)) {
//                validMoves.remove(move);
//            }
//        }
        validMoves.removeAll(invalidMoves);
        return validMoves;
    }

    public static Map<Integer, Piece> cloneBoardMap(Map<Integer, Piece> bm) throws CloneNotSupportedException {
        Map<Integer, Piece> clone = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            if (bm.containsKey(i)) {
                Piece clonedPiece = (Piece) bm.get(i).clone();
                clone.put(i, clonedPiece);
            }
        }
        return clone;
    }

    private static Move enPassant(Piece piece) {
        if(Board.getDoubleJumpByPawn()){
            if((piece.getPiecePosition() + 1 == Board.getMostRecentMove().getDestination()  &&  !Utils.EIGHTH_COLUMN[piece.getPiecePosition()]) ||
                (piece.getPiecePosition() - 1 == Board.getMostRecentMove().getDestination() &&  !Utils.FIRST_COLUMN[piece.getPiecePosition()])){
                if(piece.getPieceColour().equals(Colour.WHITE)){
                    return new EnPassant(piece, Board.getMostRecentMove().getDestination() - 8);
                }
                else{
                    return new EnPassant(piece, Board.getMostRecentMove().getDestination() + 8);
                }
            }
        }
        return null;
    }
}

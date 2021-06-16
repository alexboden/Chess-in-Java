package Board;

import Pieces.*;
import General.*;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Board {

    public static Map<Integer, Piece> boardMap = new HashMap<>();
    public static Move mostRecentMove;
    public static boolean doubleJumpByPawn = false;
    public static ArrayList<Map<Integer, Piece>> pastBoardMaps = new ArrayList<>();
    
    public Board(Map<Integer, Piece> boardMap) {
        if (boardMap == null) {
            setUpStartingPosition();
        } else {
            Board.boardMap = boardMap;
        }
        setDoubleJumpByPawn(false);
    }

    public static King getKing(Colour colour) {
        for (int i = 0; i < 64; i++) {
            if (Board.boardMap.containsKey(i)) {
                if (Board.boardMap.get(i).getPieceColour().equals(colour)) {
                    if (Board.boardMap.get(i).getPieceType().equals(PieceType.KING)) {
                        return (King) Board.boardMap.get(i);
                    }
                }
            }
        }
        return null;
    }

    public static Map<Integer, Piece> getBoardMap() {
        return Board.boardMap;
    }

    public static void setBoardMap(Map<Integer, Piece> boardMap) {
        Board.boardMap = boardMap;
    }

    public static void setPiece(Piece piece, int last) {
        Board.boardMap.put(piece.getPiecePosition(), piece);
        Board.boardMap.remove(last);
    }

    public static ArrayList<Piece> getPieces(Colour colour) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            if (Board.boardMap.containsKey(i)) {
                if (Board.boardMap.get(i).getPieceColour().equals(colour)) {
                    pieces.add(Board.boardMap.get(i));
                }
            }
        }
        return pieces;
    }
    
    public static void addToPastBoardMaps(Map<Integer, Piece> boardMap){
        pastBoardMaps.add(boardMap);
    }
    
    public static void setMostRecentMove(Move move){
        mostRecentMove = move;
    }
    public static Move getMostRecentMove(){
        return mostRecentMove;
    }
    
    public static boolean getDoubleJumpByPawn(){
        return doubleJumpByPawn;
    }
    
    public static void setDoubleJumpByPawn(boolean b){
        doubleJumpByPawn = b;
    }          

    public ArrayList<Move> getLegalMovesForPlayer(ArrayList<Piece> pieces) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves());
        }
        return legalMoves;
    }

    public static void setUpStartingPosition() {
        boardMap.clear();
        boardMap.put(0, new Rook(0, Colour.BLACK, false));
        boardMap.put(1, new Knight(1, Colour.BLACK));
        boardMap.put(2, new Bishop(2, Colour.BLACK));
        boardMap.put(3, new Queen(3, Colour.BLACK));
        boardMap.put(4, new King(4, Colour.BLACK, false));
        boardMap.put(5, new Bishop(5, Colour.BLACK));
        boardMap.put(6, new Knight(6, Colour.BLACK));
        boardMap.put(7, new Rook(7, Colour.BLACK, false));

        for (int i = 8; i < 16; i++) {
            boardMap.put(i, new Pawn(i, Colour.BLACK));
        }

        boardMap.put(56, new Rook(56, Colour.WHITE, false));
        boardMap.put(57, new Knight(57, Colour.WHITE));
        boardMap.put(58, new Bishop(58, Colour.WHITE));
        boardMap.put(59, new Queen(59, Colour.WHITE));
        boardMap.put(60, new King(60, Colour.WHITE, false));
        boardMap.put(61, new Bishop(61, Colour.WHITE));
        boardMap.put(62, new Knight(62, Colour.WHITE));
        boardMap.put(63, new Rook(63, Colour.WHITE, false));

        for (int i = 48; i < 56; i++) {
            boardMap.put(i, new Pawn(i, Colour.WHITE));
        }

    }
}

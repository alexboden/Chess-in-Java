package Pieces;

import Board.Board;
import Board.Move;
import Board.CommonMove;
import Board.AttackingMove;
import Board.KingSideCastle;
import Board.QueenSideCastle;
import Board.Turn;
import General.Colour;
import General.PieceType;
import General.Utils;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    boolean hasMoved;

    public King(int piecePosition, Colour pieceColour, boolean hasMoved) {
        super(piecePosition, pieceColour);
        this.hasMoved = hasMoved;
        this.pieceType = PieceType.KING;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean b) {
        this.hasMoved = b;
    }

    @Override
    public ArrayList<Move> calculateLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        int[] ADJACENT_DIRECTIONS = {-9, -8, -7, -1, 1, 7, 8, 9};
        for (int move : ADJACENT_DIRECTIONS) {
            int destination = this.piecePosition + move;

            if (!Utils.validCoordinate(destination)) {
                continue;
            }

            if (Utils.FIRST_COLUMN[this.piecePosition] && (move == -9 || move == -1 || move == 7)) {
                continue;
            }

            if (Utils.EIGHTH_COLUMN[this.piecePosition] && (move == 9 || move == 1 || move == -7)) {
                continue;
            }

            if (Board.getBoardMap().containsKey(destination)) {
                if (!Board.getBoardMap().get(destination).getPieceColour().equals(this.pieceColour)) {
                    legalMoves.add(new AttackingMove(this, destination, Board.getBoardMap().get(destination)));
                }
            } else {
                legalMoves.add(new CommonMove(this, destination));
            }
        }
        if (Turn.getColour().equals(this.pieceColour)) {
            if (!this.hasMoved && kingSideCastle() != null) {
                legalMoves.add(kingSideCastle());
            }

            if (!this.hasMoved && queenSideCastle() != null) {
                legalMoves.add(queenSideCastle());
            }
        }

        return legalMoves;
    }

    private Move kingSideCastle() {
        if (this.pieceColour.equals(Colour.BLACK)) {
            if (Board.getBoardMap().containsKey(5) || Board.getBoardMap().containsKey(6)) {
                return null;
            }
            if (!Board.getBoardMap().containsKey(7) || !Board.getBoardMap().containsKey(4)) {
                return null;
            }
            if (!Board.getBoardMap().get(7).getPieceType().equals(PieceType.ROOK)) {
                return null;
            }
            Rook rook = (Rook) Board.getBoardMap().get(7);
            if (rook.hasMoved) {
                return null;
            }
            if (!Board.getBoardMap().get(4).getPieceType().equals(PieceType.KING)) {
                return null;
            }
            King king = (King) Board.getBoardMap().get(4);
            if (king.hasMoved) {
                return null;
            }

            List<Piece> opponentPieces = Board.getPieces(Colour.WHITE);
            for (Piece opponentPiece : opponentPieces) {
                List<Move> opponentMoves = opponentPiece.calculateLegalMoves();
                for (Move opponentMove : opponentMoves) {
                    if (opponentMove.getDestination() == 4) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 5) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 6) {
                        return null;
                    }
                }
            }
            return new KingSideCastle(this, 6);
        } else {
            if (Board.getBoardMap().containsKey(61) || Board.getBoardMap().containsKey(62)) {
                return null;
            }
            if (!Board.getBoardMap().containsKey(63) || !Board.getBoardMap().containsKey(60)) {
                return null;
            }
            if (!Board.getBoardMap().get(63).getPieceType().equals(PieceType.ROOK)) {
                return null;
            }
            Rook rook = (Rook) Board.getBoardMap().get(63);

            if (rook.hasMoved) {
                return null;
            }

            if (!Board.getBoardMap().get(60).getPieceType().equals(PieceType.KING)) {
                return null;
            }
            King king = (King) Board.getBoardMap().get(60);
            if (king.hasMoved) {
                return null;
            }
            List<Piece> opponentPieces = Board.getPieces(Colour.BLACK);
            for (Piece opponentPiece : opponentPieces) {
                List<Move> opponentMoves = opponentPiece.calculateLegalMoves();
                for (Move opponentMove : opponentMoves) {
                    if (opponentMove.getDestination() == 60) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 61) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 62) {
                        return null;
                    }
                }
            }
            return new KingSideCastle(this, 62);
        }
    }

    private Move queenSideCastle() {
        if (this.pieceColour.equals(Colour.BLACK)) {
            if (Board.getBoardMap().containsKey(1) || Board.getBoardMap().containsKey(2) || Board.getBoardMap().containsKey(3)) {
                return null;
            }
            if (!Board.getBoardMap().containsKey(0) || !Board.getBoardMap().containsKey(4)) {
                return null;
            }
            if (!Board.getBoardMap().get(0).getPieceType().equals(PieceType.ROOK)) {
                return null;
            }
            Rook rook = (Rook) Board.getBoardMap().get(0);
            if (rook.hasMoved) {
                return null;
            }
            if (!Board.getBoardMap().get(4).getPieceType().equals(PieceType.KING)) {
                return null;
            }
            King king = (King) Board.getBoardMap().get(4);
            if (king.hasMoved) {
                return null;
            }
            List<Piece> opponentPieces = Board.getPieces(Colour.WHITE);
            for (Piece opponentPiece : opponentPieces) {
                List<Move> opponentMoves = opponentPiece.calculateLegalMoves();
                for (Move opponentMove : opponentMoves) {
                    if (opponentMove.getDestination() == 2) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 3) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 4) {
                        return null;
                    }
                }
            }
            return new QueenSideCastle(this, 2);
        } else {
            if (Board.getBoardMap().containsKey(57) || Board.getBoardMap().containsKey(58) || Board.getBoardMap().containsKey(59)) {
                return null;
            }
            if (!Board.getBoardMap().containsKey(56) || !Board.getBoardMap().containsKey(60)) {
                return null;
            }
            if (!Board.getBoardMap().get(56).getPieceType().equals(PieceType.ROOK)) {
                return null;
            }
            Rook rook = (Rook) Board.getBoardMap().get(56);
            if (rook.hasMoved) {
                return null;
            }
            if (!Board.getBoardMap().get(60).getPieceType().equals(PieceType.KING)) {
                return null;
            }
            King king = (King) Board.getBoardMap().get(60);
            if (king.hasMoved) {
                return null;
            }
            List<Piece> opponentPieces = Board.getPieces(Colour.BLACK);
            for (Piece opponentPiece : opponentPieces) {
                List<Move> opponentMoves = opponentPiece.calculateLegalMoves();
                for (Move opponentMove : opponentMoves) {
                    if (opponentMove.getDestination() == 58) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 59) {
                        return null;
                    }
                    if (opponentMove.getDestination() == 60) {
                        return null;
                    }
                }
            }
            return new QueenSideCastle(this, 58);
        }
    }
}

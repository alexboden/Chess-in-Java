package Pieces;

import Board.Board;
import Board.Move;
import Board.CommonMove;
import Board.AttackingMove;
import General.Colour;
import General.PieceType;
import General.Utils;
import java.util.ArrayList;

public class Knight extends Piece {

    int[] ALL_POTENTIALLY_POSSIBLE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        this.pieceType = PieceType.KNIGHT;
    }

    @Override
    public ArrayList<Move> calculateLegalMoves() {
        int destination;
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int possibleMove : ALL_POTENTIALLY_POSSIBLE_MOVES) {
            if (illegalFirstColumn(this.piecePosition, possibleMove)
                    || illegalSecondColumn(this.piecePosition, possibleMove)
                    || illegalSeventhColumn(this.piecePosition, possibleMove)
                    || illegalEighthColumn(this.piecePosition, possibleMove)) {
                continue;
            }

            destination = this.piecePosition + possibleMove;
            if (Utils.validCoordinate(destination)) {
                if (Board.getBoardMap().containsKey(destination)) {
                    if (!Board.getBoardMap().get(destination).getPieceColour().equals(this.pieceColour)) {
                        legalMoves.add(new AttackingMove(this, destination, Board.getBoardMap().get(destination)));
                    }
                    continue;
                }
                legalMoves.add(new CommonMove(this, destination));
            }
        }
        return legalMoves;
    }

    private static boolean illegalFirstColumn(int currentPosition, int candidateOffset) {
        return Utils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10
                || candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean illegalSecondColumn(int currentPosition, int candidateOffset) {
        return Utils.SECOND_COLUMN[currentPosition] && (candidateOffset == 6 || candidateOffset == -10);
    }

    private static boolean illegalSeventhColumn(int currentPosition, int candidateOffset) {
        return Utils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean illegalEighthColumn(int currentPosition, int candidateOffset) {
        return Utils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 17 || candidateOffset == 10
                || candidateOffset == -6 || candidateOffset == -15);
    }
}

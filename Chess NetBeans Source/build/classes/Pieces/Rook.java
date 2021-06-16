package Pieces;

import Board.Board;
import Board.Move;
import Board.AttackingMove;
import Board.CommonMove;
import General.Colour;
import General.PieceType;
import General.Utils;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    int[] DIRECTLY_ADJACENT = {-8, -1, 1, 8};
    boolean hasMoved;

    public Rook(int piecePosition, Colour pieceColour, boolean hasMoved) {
        super(piecePosition, pieceColour);
        this.pieceType = PieceType.ROOK;
        this.hasMoved = hasMoved;
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
        for (int singleAdjacent : DIRECTLY_ADJACENT) {
            int n = 1;
            while (true) {
                int possibleMove = n * singleAdjacent;
                n++;
                if (!Utils.validCoordinate(this.piecePosition + possibleMove)) {
                    break;
                }
                int destination = this.piecePosition + possibleMove;

                if (Utils.FIRST_COLUMN[destination - singleAdjacent] && singleAdjacent == -1) {
                    break;
                }
                if (Utils.EIGHTH_COLUMN[destination - singleAdjacent] && singleAdjacent == 1) {
                    break;
                }
                if (Board.getBoardMap().containsKey(destination)) {
                    if (!Board.getBoardMap().get(destination).getPieceColour().equals(this.pieceColour)) {
                        legalMoves.add(new AttackingMove(this, destination, Board.getBoardMap().get(destination)));
                    }
                    break;
                }
                legalMoves.add(new CommonMove(this, destination));
            }
        }
        return legalMoves;
    }
}

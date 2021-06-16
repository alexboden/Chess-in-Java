package Pieces;

import Board.Board;
import Board.Move;
import Board.AttackingMove;
import Board.CommonMove;
import General.Utils;
import General.Colour;
import General.PieceType;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        this.pieceType = PieceType.QUEEN;
    }

    int[] ADJACENT_DIRECTIONS = {-9, -8, -7, -1, 1, 7, 8, 9};

    @Override
    public ArrayList<Move> calculateLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int singleAdjacent : ADJACENT_DIRECTIONS) {
            int n = 1;
            while (true) {
                int possibleMove = n * singleAdjacent;
                n++;

                if (!Utils.validCoordinate(this.piecePosition + possibleMove)) {
                    break;
                }
                int destination = this.piecePosition + possibleMove;

                if (Utils.FIRST_COLUMN[destination - singleAdjacent] && (singleAdjacent == -1 || singleAdjacent == -9 || singleAdjacent == 7)) {
                    break;
                }

                if (Utils.EIGHTH_COLUMN[destination - singleAdjacent] && (singleAdjacent == 1 || singleAdjacent == 9 || singleAdjacent == -7)) {
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

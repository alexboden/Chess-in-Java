package Pieces;

import Board.AttackingMove;
import Board.CommonMove;
import Board.Board;
import Board.Move;
import General.Colour;
import General.PieceType;
import General.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    int[] ONE_SQUARE_DIAGONAL = {-9, -7, 7, 9};

    public Bishop(int piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        this.pieceType = PieceType.BISHOP;
    }

    @Override
    public ArrayList<Move> calculateLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (int singleDiagonal : ONE_SQUARE_DIAGONAL) {
            int n = 1;
            while (true) {
                int possibleMove = singleDiagonal * n;
                n++;
                int destination = this.piecePosition + possibleMove;
                if (!Utils.validCoordinate(destination)) {
                    break;
                }
                if (Utils.FIRST_COLUMN[destination - singleDiagonal] && (singleDiagonal == 7 || singleDiagonal == -9)) {
                    break;
                }
                if (Utils.EIGHTH_COLUMN[destination - singleDiagonal] && (singleDiagonal == -7 || singleDiagonal == 9)) {
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

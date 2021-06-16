package Pieces;

import Board.Board;
import Board.Move;
import Board.AttackingMove;
import Board.CommonMove;
import General.Utils;
import General.Colour;
import General.PieceType;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
        this.pieceType = PieceType.PAWN;
    }

    @Override
    public ArrayList<Move> calculateLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        if (this.pieceColour == Colour.BLACK) {
            int destination = this.piecePosition + 8;
            if (!Board.getBoardMap().containsKey(destination)) {
                legalMoves.add(new CommonMove(this, this.piecePosition + 8));
                if (Utils.SECOND_ROW[this.piecePosition]) {
                    destination = this.piecePosition + 16;
                    if (!Board.getBoardMap().containsKey(destination)) {
                        legalMoves.add(new CommonMove(this, this.piecePosition + 16));
                    }
                }
            }
            int attack1 = this.piecePosition + 7;
            int attack2 = this.piecePosition + 9;
            if (Board.getBoardMap().containsKey(attack1) && !Utils.FIRST_COLUMN[this.piecePosition]) {
                if (Board.getBoardMap().get(attack1).pieceColour.equals(Colour.WHITE)) {
                    legalMoves.add(new AttackingMove(this, this.piecePosition + 7, Board.getBoardMap().get(attack1)));
                }
            }
            if (Board.getBoardMap().containsKey(attack2) && !Utils.EIGHTH_COLUMN[this.piecePosition]) {
                if (Board.getBoardMap().get(attack2).pieceColour.equals(Colour.WHITE)) {
                    legalMoves.add(new AttackingMove(this, this.piecePosition + 9, Board.getBoardMap().get(attack2)));
                }
            }
        } 
        else { //Colour == White
            int destination = this.piecePosition - 8;
            if (!Board.getBoardMap().containsKey(destination)) {
                legalMoves.add(new CommonMove(this, this.piecePosition - 8));
                if (Utils.SEVENTH_ROW[this.piecePosition]) {
                    destination = this.piecePosition - 16;
                    if (!Board.getBoardMap().containsKey(destination)) {
                        legalMoves.add(new CommonMove(this, this.piecePosition - 16));
                    }
                }
            }
            int attack1 = this.piecePosition - 7;
            int attack2 = this.piecePosition - 9;
            if (Board.getBoardMap().containsKey(attack1) && !Utils.EIGHTH_COLUMN[this.piecePosition]) {
                if (Board.getBoardMap().get(attack1).pieceColour.equals(Colour.BLACK)) {
                    legalMoves.add(new AttackingMove(this, this.piecePosition - 7, Board.getBoardMap().get(attack1)));
                }
            }
            if (Board.getBoardMap().containsKey(attack2) && !Utils.FIRST_COLUMN[this.piecePosition]) {
                if (Board.getBoardMap().get(attack2).pieceColour.equals(Colour.BLACK)) {
                    legalMoves.add(new AttackingMove(this, this.piecePosition - 9, Board.getBoardMap().get(attack2)));
                }
            }
        }
        return legalMoves;
    }
}

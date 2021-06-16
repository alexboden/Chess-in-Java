package Pieces;

import General.Colour;
import General.PieceType;
import Board.Move;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    protected int piecePosition;
    protected Colour pieceColour;
    protected PieceType pieceType;

    public Piece(int piecePosition, Colour pieceColour) {
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
    }

    public abstract ArrayList<Move> calculateLegalMoves();

    public Colour getPieceColour() {
        return this.pieceColour;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public void setPiecePosition(int piecePosition) {
        this.piecePosition = piecePosition;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

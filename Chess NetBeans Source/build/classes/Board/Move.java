package Board;

import General.MoveType;
import Pieces.Piece;

public abstract class Move {

    protected Piece movedPiece;
    protected int destination;
    protected MoveType moveType;

    public Move(Piece movedPiece, int destination) {
        this.movedPiece = movedPiece;
        this.destination = destination;
    }

    public int getDestination() {
        return this.destination;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public MoveType getMoveType() {
        return this.moveType;
    }
}

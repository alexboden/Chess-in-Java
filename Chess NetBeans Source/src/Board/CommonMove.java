package Board;

import General.MoveType;
import Pieces.Piece;

public class CommonMove extends Move {

    public CommonMove(Piece movedPiece, int destination) {
        super(movedPiece, destination);
        this.moveType = MoveType.COMMON;
    }
}

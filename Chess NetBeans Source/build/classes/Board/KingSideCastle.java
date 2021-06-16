package Board;

import Pieces.Piece;
import General.MoveType;

public class KingSideCastle extends Move {

    public KingSideCastle(Piece movedPiece, int destination) {
        super(movedPiece, destination);
        this.moveType = MoveType.KINGSIDECASTLE;
    }
}

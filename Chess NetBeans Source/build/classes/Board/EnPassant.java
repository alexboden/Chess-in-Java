package Board;

import General.MoveType;
import Pieces.Piece;

public class EnPassant extends Move{
    public EnPassant(Piece movedPiece, int destination) {
        super(movedPiece, destination);
        this.moveType = MoveType.ENPASSANT;
    }
}

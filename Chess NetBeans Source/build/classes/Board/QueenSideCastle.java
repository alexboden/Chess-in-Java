package Board;

import Pieces.Piece;
import General.MoveType;

public class QueenSideCastle extends Move{
    public QueenSideCastle(Piece movedPiece, int destination){
        super(movedPiece, destination);
        this.moveType = MoveType.QUEENSIDECASTLE;
    }
}

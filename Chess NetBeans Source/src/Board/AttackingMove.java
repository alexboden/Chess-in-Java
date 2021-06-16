package Board;

import General.MoveType;
import Pieces.Piece;

public class AttackingMove extends Move {

    final Piece attackedPiece;

    public AttackingMove(Piece movedPiece, int destination, Piece attackedPiece) {
        super(movedPiece, destination);
        this.attackedPiece = attackedPiece;
        this.moveType = MoveType.ATTACKING;
    }
}

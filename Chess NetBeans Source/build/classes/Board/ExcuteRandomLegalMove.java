package Board;

import java.util.ArrayList;

public class ExcuteRandomLegalMove {
    public static void excuteRandomLegalMove() throws CloneNotSupportedException, InterruptedException {
        ArrayList<Move> legalMoves = LegalMoves.getAllMoves(Board.getKing(Turn.getColour()));
        int random = (int) ((Math.random() * legalMoves.size()) - 1);
        ExecuteMove.executeMove(legalMoves.get(random));
    }
}

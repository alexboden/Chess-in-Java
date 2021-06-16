package Board;

import General.Colour;
import General.PieceType;
import General.Utils;
import Graphics.BoardPanel;
import Graphics.ClickListener;
import static Graphics.ClickListener.setBothIcons;

import Graphics.Main;
import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;
import javax.swing.Icon;

public class ExecuteMove {    
   public static void executeMove(Move move) throws CloneNotSupportedException, InterruptedException {   
       Board.addToPastBoardMaps(LegalMoves.cloneBoardMap(Board.boardMap));
       Board.setMostRecentMove(move);
       Icon pieceIcon = BoardPanel.getButtons().get(move.getMovedPiece().getPiecePosition()).getIcon();
       setBothIcons(move.getMovedPiece().getPiecePosition(), null);
       setBothIcons(move.getDestination(), pieceIcon);
       ClickListener.clearPossibleMoveSquares();
       ClickListener.clearPossibleMoves();
       switch (move.getMoveType()) {
           case KINGSIDECASTLE:
               if(move.getMovedPiece().getPieceColour().equals(Colour.WHITE)){
                   King king = (King) Board.boardMap.get(60);
                   Rook rook = (Rook) Board.boardMap.get(63);
                   king.setHasMoved(true);
                   rook.setHasMoved(true);
                   king.setPiecePosition(62);
                   rook.setPiecePosition(61);
                   Board.boardMap.put(61, rook);
                   Board.boardMap.put(62, king);
                   Board.boardMap.remove(60);
                   Board.boardMap.remove(63);
                   System.out.println(Board.displayBoard());
                    setBothIcons(60, null);
                    setBothIcons(63, null);
                    setBothIcons(61, BoardPanel.WHITE_ROOK_ICON);
                    setBothIcons(62, BoardPanel.WHITE_KING_ICON);
                   Turn.changeTurn();
                   break;
               }
               else{
                   King king = (King) Board.boardMap.get(4);
                   Rook rook = (Rook) Board.boardMap.get(7);
                   king.setHasMoved(true);
                   rook.setHasMoved(true);
                   king.setPiecePosition(6);
                   rook.setPiecePosition(5);
                   Board.boardMap.put(5, rook);
                   Board.boardMap.put(6, king);
                   Board.boardMap.remove(4);
                   Board.boardMap.remove(7);
                   System.out.println(Board.displayBoard());
                    setBothIcons(4, null);
                    setBothIcons(7, null);
                    setBothIcons(5, BoardPanel.BLACK_ROOK_ICON);
                    setBothIcons(6, BoardPanel.BLACK_KING_ICON);
                   Turn.changeTurn();
                   break;
               }
                
           case QUEENSIDECASTLE:
               if(move.getMovedPiece().getPieceColour().equals(Colour.WHITE)){
                   King king = (King) Board.boardMap.get(60);
                   Rook rook = (Rook) Board.boardMap.get(56);
                   king.setHasMoved(true);
                   rook.setHasMoved(true);
                   king.setPiecePosition(58);
                   rook.setPiecePosition(59);
                   Board.boardMap.put(59, rook);
                   Board.boardMap.put(58, king);
                   Board.boardMap.remove(60);
                   Board.boardMap.remove(56);
                   System.out.println(Board.displayBoard());
                    setBothIcons(60, null);
                    setBothIcons(56, null);
                    setBothIcons(59, BoardPanel.WHITE_ROOK_ICON);
                    setBothIcons(58, BoardPanel.WHITE_KING_ICON);
                    Turn.changeTurn();
                   break;
               }
               else{
                   King king = (King) Board.boardMap.get(4);
                   Rook rook = (Rook) Board.boardMap.get(0);
                   king.setHasMoved(true);
                   rook.setHasMoved(true);
                   king.setPiecePosition(2);
                   rook.setPiecePosition(3);
                   Board.boardMap.put(3, rook);
                   Board.boardMap.put(2, king);
                   Board.boardMap.remove(4);
                   Board.boardMap.remove(0);
                   System.out.println(Board.displayBoard());
                   setBothIcons(4, null);
                   setBothIcons(0, null);
                   setBothIcons(3, BoardPanel.BLACK_ROOK_ICON);
                   setBothIcons(2, BoardPanel.BLACK_KING_ICON);
                   Turn.changeTurn();
               }   break;
               
            case ENPASSANT:
                if(move.getMovedPiece().getPieceColour().equals(Colour.WHITE)){
                    Board.boardMap.remove(move.getDestination() + 8);
                }
                else{
                    Board.boardMap.remove(move.getDestination() - 8);
                }
                Piece pawn = move.getMovedPiece();
                Board.boardMap.remove(pawn.getPiecePosition());
                pawn.setPiecePosition(move.getDestination());
                Board.boardMap.put(move.getDestination(), pawn);
                if (move.getMovedPiece().getPieceColour().equals(Colour.WHITE)) {
                    setBothIcons(move.getDestination() + 8, null);
                    setBothIcons(move.getMovedPiece().getPiecePosition(), null);
                    setBothIcons(move.getDestination(), BoardPanel.WHITE_PAWN_ICON);
                } else {
                    setBothIcons(move.getDestination() - 8, null);
                    setBothIcons(move.getMovedPiece().getPiecePosition(), null);
                    setBothIcons(move.getDestination(), BoardPanel.BLACK_PAWN_ICON);
                }
                Turn.changeTurn();
                break;
               
           default:
               Piece piece = move.getMovedPiece();
               int last = piece.getPiecePosition();
               if(Math.abs(last - move.getDestination()) == 16 && piece.getPieceType().equals(PieceType.PAWN)){
                   Board.setDoubleJumpByPawn(true);
               }
               else{
                   Board.setDoubleJumpByPawn(false);
               }
               piece.setPiecePosition(move.getDestination());
               Board.boardMap.put(piece.getPiecePosition(), piece);
               Board.boardMap.remove(last);
               System.out.println(Board.displayBoard());
               if(piece.getPieceType().equals(PieceType.KING)){
                   King king = (King) piece;
                   king.setHasMoved(true);
               }
               if(piece.getPieceType().equals(PieceType.ROOK)){
                   Rook rook = (Rook) piece;
                   rook.setHasMoved(true);
               }
               if(piece.getPieceType().equals(PieceType.PAWN) && Utils.EIGHTH_ROW[move.getDestination()]){
                   Main.addPromotionPanel(Colour.BLACK, move);
               }
               else if(piece.getPieceType().equals(PieceType.PAWN) && Utils.FIRST_ROW[move.getDestination()]){
                   Main.addPromotionPanel(Colour.WHITE, move);
               }
               else{
                 Turn.changeTurn();
               }               
               break;
       }
   }   
}

package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMove {

    private ChessBoard board;
    private ChessPosition myPosition;
    private Collection<ChessMove> moves = new ArrayList<ChessMove>();

    public PawnMove(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public boolean onBoard(ChessPosition possiblePosition) {
        int row = possiblePosition.getRow();
        int col = possiblePosition.getColumn();
        if (row < 1 || row > 8 || col < 1 || col > 8) {
            return false;
        }
        return true;
    }

    public void checkAddPromote(ChessPosition possiblePosition, ChessGame.TeamColor team) {
        int row = possiblePosition.getRow();
        if ((team == ChessGame.TeamColor.WHITE && row == 8) || (team == ChessGame.TeamColor.BLACK && row == 1)) {
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.KNIGHT));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.QUEEN));
        } else {
            moves.add(new ChessMove(myPosition, possiblePosition, null));
        }
    }

    public Collection<ChessMove> getMoves() {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();
        ChessPosition possiblePosition;

        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col);
        } else {
            possiblePosition = new ChessPosition(row-1, col);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
            checkAddPromote(possiblePosition, team);
            if (team == ChessGame.TeamColor.WHITE && row == 2) {
                possiblePosition = new ChessPosition(row+2, col);
                if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
                    checkAddPromote(possiblePosition, team);
                }
            } else if (team == ChessGame.TeamColor.BLACK && row == 7) {
                possiblePosition = new ChessPosition(row-2, col);
                if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
                    checkAddPromote(possiblePosition, team);
                }
            }
        }

        //diagonal capture right
        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col+1);
        } else {
            possiblePosition = new ChessPosition(row-1, col+1);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) != null && board.getPiece(possiblePosition).getTeamColor() != team) {
            checkAddPromote(possiblePosition, team);
        }

        //diagonal capture left
        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col-1);
        } else {
            possiblePosition = new ChessPosition(row-1, col-1);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) != null && board.getPiece(possiblePosition).getTeamColor() != team) {
            checkAddPromote(possiblePosition, team);
        }

        return moves;
    }
}

package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMove {

    private ChessBoard board;
    private ChessPosition myPosition;
    private Collection<ChessMove> moves = new ArrayList<ChessMove>();

    public boolean checkAddPosition(int row, int col, ChessGame.TeamColor team) {
        ChessPosition possiblePosition = new ChessPosition(row, col);
        if (row < 1 || row > 8 || col < 1 || col > 8) {
            return false;
        } else if (board.getPiece(possiblePosition) == null) {
            moves.add(new ChessMove(myPosition, possiblePosition, null));
            return true;
        } else if (board.getPiece(possiblePosition).getTeamColor() != team) {
            moves.add(new ChessMove(myPosition, possiblePosition, null));
            return false;
        }
        return false;
    }

    public RookMove(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getMoves() {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();

        //up
        for (int i = row+1; i <= 8; i++) {
            boolean validMove = checkAddPosition(i, col, team);
            if (!validMove) {break;}
        }

        //down
        for (int i = row-1; i >= 1; i--) {
            boolean validMove = checkAddPosition(i, col, team);
            if (!validMove) {break;}
        }

        //right
        for (int i = col+1; i <= 8; i++) {
            boolean validMove = checkAddPosition(row, i, team);
            if (!validMove) {break;}
        }

        //left
        for (int i = col-1; i >= 1; i--) {
            boolean validMove = checkAddPosition(row, i, team);
            if (!validMove) {break;}
        }

        return moves;
    }
}

package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMove {

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

    public KnightMove(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
    }

    public Collection<ChessMove> getMoves() {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();

        //up 2 right 1
        checkAddPosition(row+2, col+1, team);

        //up 2 left 1
        checkAddPosition(row+2, col-1, team);

        //down 2 right 1
        checkAddPosition(row-2, col+1, team);

        //down 2 left 1
        checkAddPosition(row-2, col-1, team);

        //right 2 up 1
        checkAddPosition(row+1, col+2, team);

        //right 2 down 1
        checkAddPosition(row-1, col+2, team);

        //left 2 up 1
        checkAddPosition(row+1, col-2, team);

        //left 2 down 1
        checkAddPosition(row-1, col-2, team);

        return moves;
    }
}

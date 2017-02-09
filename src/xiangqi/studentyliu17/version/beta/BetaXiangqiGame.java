package xiangqi.studentyliu17.version.beta;

import xiangqi.common.*;
import xiangqi.studentyliu17.version.XiangqiPieceImpl;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Beta version of Xiangqi game
 *
 * @version Jan 28, 2016
 */
public class BetaXiangqiGame implements XiangqiGame {
    private final int BOARD_WIDTH  = 5;
    private final int BOARD_HEIGHT = 5;
    private XiangqiColor     currentPlayer;
    private XiangqiPiece[][] board;
    
    /**
     * <p>
     * Make a move in the game. The XiangqiGame instance needs to keep track of the
     * player on move.
     * </p><p>
     * NOTE: If the attempted move is illegal, this method returns a
     * MoveResult.ILLEGAL and ignores the move. It is up to the client to decide what
     * to do.
     * </p>
     *
     * @param source      the coordinate where the piece moving starts
     * @param destination the coordinate where the piece moving ends
     *
     * @return the move result
     */
    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        if (destination.getRank() > 1 && destination.getFile() > 1) return MoveResult.ILLEGAL;
        if (source.getRank() == 2 && source.getFile() == 1) return MoveResult.ILLEGAL;
    
        movePiece(source, destination);
        switchPlayer();
        
        return MoveResult.OK;
    }
    
    private void movePiece(XiangqiCoordinate source, XiangqiCoordinate destination) {
        int sourceFile = getFileRespectToRed(source, currentPlayer);
        int sourceRank = getRankRespectToRed(source, currentPlayer);
    
        int destFile = getFileRespectToRed(destination, currentPlayer);
        int destRank = getRankRespectToRed(destination, currentPlayer);
    
        board[destRank - 1][destFile - 1] = board[sourceRank - 1][sourceFile - 1];
        board[sourceRank - 1][sourceFile - 1] = XiangqiPieceImpl.makePiece(NONE, XiangqiColor.NONE);
    }
    
    private void switchPlayer() {
        currentPlayer = currentPlayer == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
    }
    
    /**
     * This method is called to obtain additional information about a move that has just been
     * attempted or made. It must return a message about illegal moves when a client attempts to
     * make
     * such a move.
     * If a valid move is made, the implementation is free to return either an empty string or some
     * other string that describes the board situation.
     *
     * @return
     */
    @Override
    public String getMoveMessage() {
        return "Invalid movement";
    }
    
    /**
     * Method used for querying the board.
     *
     * @param where  the coordinate to access
     * @param aspect the player from whom the request is made. This is needed
     *               in order to determine which location the <code>where</code> parameter
     *               references
     *
     * @return the piece at the specified coordinate. If the coordinate is empty,
     * this returns a piece with the type of XiangqiPieceType.NONE, and a color of
     * XiangqiColor.NONE.
     */
    @Override
    public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = getFileRespectToRed(where, aspect);
        int rank = getRankRespectToRed(where, aspect);
        
        return board[rank - 1][file - 1];
    }
    
    /**
     * In some games, it may be possible to performe some initialization before the
     * game begins. The default method does nothing. This can be overridden by the
     * instances that require initialization.
     *
     * @param args an array of objects that are needed for initialization
     */
    @Override
    public void initialize(Object... args) {
        board = new XiangqiPieceImpl[5][5];
        currentPlayer = XiangqiColor.RED;
        
        setupRedPieces();
        setupBlackPieces();
        setupEmptySpaces();
    }
    
    private void setupRedPieces() {
        board[0][0] = XiangqiPieceImpl.makePiece(CHARIOT, RED);
        board[0][1] = XiangqiPieceImpl.makePiece(ADVISOR, RED);
        board[0][2] = XiangqiPieceImpl.makePiece(GENERAL, RED);
        board[0][3] = XiangqiPieceImpl.makePiece(ADVISOR, RED);
        board[0][4] = XiangqiPieceImpl.makePiece(CHARIOT, RED);
        board[1][2] = XiangqiPieceImpl.makePiece(SOLDIER, RED);
    }
    
    private void setupBlackPieces() {
        board[4][0] = XiangqiPieceImpl.makePiece(CHARIOT, BLACK);
        board[4][1] = XiangqiPieceImpl.makePiece(ADVISOR, BLACK);
        board[4][2] = XiangqiPieceImpl.makePiece(GENERAL, BLACK);
        board[4][3] = XiangqiPieceImpl.makePiece(ADVISOR, BLACK);
        board[4][4] = XiangqiPieceImpl.makePiece(CHARIOT, BLACK);
        board[3][2] = XiangqiPieceImpl.makePiece(SOLDIER, BLACK);
    }
    
    private void setupEmptySpaces() {
        for (int rank = 2; rank < 5; rank++) {
            for (int file = 1; file < 6; file++) {
                if (file != 3)
                    board[rank - 1][file - 1] = XiangqiPieceImpl.makePiece(NONE, XiangqiColor.NONE);
            }
        }
    }
    
    private int getRankRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int rank = where.getRank();
        if (aspect == XiangqiColor.RED) return rank;
        else return BOARD_HEIGHT + 1 - rank;
    }
    
    private int getFileRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = where.getFile();
        if (aspect == BLACK) return file;
        else return BOARD_WIDTH + 1 - file;
    }
}

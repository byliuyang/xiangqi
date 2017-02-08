package xiangqi.studentyliu17.version.beta;

import xiangqi.common.*;
import xiangqi.studentyliu17.version.XiangqiPieceImpl;

/**
 * Beta version of Xiangqi game
 *
 * @version Jan 28, 2016
 */
public class BetaXiangqiGame implements XiangqiGame {
    private final int BOARD_WIDTH = 5;
    private final int BOARD_HEIGHT = 5;
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
        return MoveResult.OK;
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
        
        XiangqiPieceType pieceType = XiangqiPieceType.NONE;
        XiangqiColor color = XiangqiColor.NONE;
        
        switch (file) {
            case 1:
            case 5:
                if(rank == 1 || rank == 5) {
                    pieceType = XiangqiPieceType.CHARIOT;
                    if (rank == 1) color = XiangqiColor.RED;
                    else color = XiangqiColor.BLACK;
                }
                break;
            case 2:
            case 4:
                if(rank == 1 || rank == 5)  {
                    pieceType = XiangqiPieceType.ADVISOR;
                    if (rank == 1) color = XiangqiColor.RED;
                    else color = XiangqiColor.BLACK;
                }
                break;
            case 3:
    
                if(rank == 1 || rank == 5) {
                    pieceType = XiangqiPieceType.GENERAL;
                    if (rank == 1) color = XiangqiColor.RED;
                    else color = XiangqiColor.BLACK;
                } else if(rank == 2 || rank == 4) {
                    pieceType = XiangqiPieceType.SOLDIER;
                    if (rank == 2) color = XiangqiColor.RED;
                    else color = XiangqiColor.BLACK;
                }
            default:
                break;
        }
        
        return XiangqiPieceImpl.makePiece(pieceType, color);
    }
    
    private int getRankRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int rank = where.getRank();
        if(aspect == XiangqiColor.RED)
            return rank;
        else
            return BOARD_HEIGHT + 1 - rank;
    }
    
    private int getFileRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = where.getFile();
        if(aspect == XiangqiColor.BLACK)
            return file;
        else
            return BOARD_WIDTH + 1 - file;
    }
}

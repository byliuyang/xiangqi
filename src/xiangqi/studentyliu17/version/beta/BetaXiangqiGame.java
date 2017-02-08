package xiangqi.studentyliu17.version.beta;

import xiangqi.common.*;
import xiangqi.studentyliu17.version.XiangqiPieceImpl;

/**
 * Beta version of Xiangqi game
 *
 * @version Jan 28, 2016
 */
public class BetaXiangqiGame implements XiangqiGame {
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
        int file = where.getFile();
        
        switch (file) {
            case 1:
            case 5:
                return XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, aspect);
            case 2:
            case 4:
                return XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, aspect);
            case 3:
                int rank = where.getRank();
                if(rank == 1)
                    return XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, aspect);
                else if(rank == 2)
                    return XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, aspect);
            default:
                return XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
        }
    }
}

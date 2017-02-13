package xiangqi.studentyliu17.version.gamma;

import xiangqi.common.*;
import xiangqi.studentyliu17.version.XiangqiPieceImpl;

/***************************************************************************************************
 * Gamma Xiàngqí is the first version that uses the standard board, including the river and the
 * Generals’ palaces.
 *
 * This version utilizes all of the pieces in the standard game, except for the Horse and the
 * Cannon. Each piece in this game moves as in the standard game.
 *
 * There is a move limit in this game of 25 complete moves. If, at the end of the 25th move, and
 * there is no winner, the game results in a draw.
 *
 * @author Yang Liu
 * @version Feb 11, 2016
 **************************************************************************************************/
public class GammaXiangqiGame implements XiangqiGame {
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
        return null;
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
        return null;
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
        int rank = where.getRank();
        int file = where.getFile();
        if((rank == 4 || rank == 7) && file % 2 != 0) return XiangqiPieceImpl.makePiece
                (XiangqiPieceType.SOLDIER, rank == 4 ? XiangqiColor.RED : XiangqiColor.BLACK);
        return XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
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
        
    }
}

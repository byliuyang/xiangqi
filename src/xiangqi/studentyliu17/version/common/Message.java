package xiangqi.studentyliu17.version.common;

/**
 * This class defines collection of message
 *
 * @version Feb 21, 2017
 */
public interface Message {
    public static final String GET_PIECE_INVALID = "Cannot get piece from invalid board coordinate";
    public static final String GENERAL_IN_CHECK  = "General is now in check";
    public static final String PLAYER_WIN        = "Player %s wins!";
    public static final String GAME_IS_DRAW  = "Game is draw!";
    public static final String INVALID_MOVE = "Move is invalid";
}

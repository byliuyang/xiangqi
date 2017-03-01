package xiangqi.studentyliu17.version.common;

/**
 * This class defines collection of message
 *
 * @version Feb 21, 2017
 */
public interface Message {
    String GET_PIECE_INVALID = "Cannot get piece from invalid board coordinate";
    String GENERAL_IN_CHECK  = "General is now in check";
    String PLAYER_WIN        = "Player %s wins!";
    String GAME_IS_DRAW  = "Game is draw!";
    String INVALID_MOVE = "Move is invalid";
}

package xiangqi.studentyliu17.version.common;

/**
 * The interface specify ruleSets
 *
 * @version Feb 20, 2017
 */
public interface RuleSet {
    /**
     * Check whether the game is draw based on the current game state
     *
     * @param gameState The current game state
     *
     * @return true if the game result is draw, false otherwise
     */
    boolean isDraw(XiangqiGameState gameState);
    
    boolean allowPerpetualCheck();
    int numRepetitions();
}

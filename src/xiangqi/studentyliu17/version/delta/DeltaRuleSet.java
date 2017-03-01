package xiangqi.studentyliu17.version.delta;

import xiangqi.studentyliu17.version.common.RuleSet;
import xiangqi.studentyliu17.version.common.XiangqiGameState;

/**
 * Rule set of delta xiangqi game
 *
 * @version Feb 28, 2017
 */
public class DeltaRuleSet implements RuleSet {
    /**
     * Check whether the game is draw based on the current game state
     *
     * @param gameState The current game state
     *
     * @return true if the game result is draw, false otherwise
     */
    @Override
    public boolean isDraw(XiangqiGameState gameState) {
        return false;
    }
    
    /**
     * Check whether game allow perpetual check
     *
     * @return true if no perpetual check allowed, false otherwise
     */
    @Override
    public boolean noPerpetualCheck() { return true; }
    
    /**
     * Get the maximum number of repetitions of perpetual check allowed
     *
     * @return the maximum number of repetitions of perpetual check allowed
     */
    public int numRepetitions() { return 3; }
}

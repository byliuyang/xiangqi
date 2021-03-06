package xiangqi.studentyliu17.version.gamma;

import xiangqi.studentyliu17.version.common.RuleSet;
import xiangqi.studentyliu17.version.common.XiangqiGameState;

/**
 * This class specify rule set for gamma xiangqi game
 *
 * @version Feb 20, 2017
 */
public class GammaRuleSet implements RuleSet {
    /**
     * Check whether the game is draw based on the current game state
     *
     * @param gameState The current game state
     *
     * @return true if the game result is draw, false otherwise
     */
    @Override
    public boolean isDraw(XiangqiGameState gameState) {
        return gameState.getTurns() >= 25;
    }
    
    /**
     * Check whether game allow perpetual check
     *
     * @return true if no perpetual check allowed, false otherwise
     */
    @Override
    public boolean noPerpetualCheck() { return false; }
    
    /**
     * Get the maximum number of repetitions of perpetual check allowed
     *
     * @return the maximum number of repetitions of perpetual check allowed
     */
    public int numRepetitions() { return 0; }
    
}

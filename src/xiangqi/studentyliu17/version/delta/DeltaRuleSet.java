package xiangqi.studentyliu17.version.delta;

import xiangqi.studentyliu17.version.common.RuleSet;
import xiangqi.studentyliu17.version.common.XiangqiGameState;

/**
 * Rule set of delta xiangqi game
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
    
    @Override
    public boolean allowPerpetualCheck() {
        return true;
    }
    
    @Override
    public int numRepetitions() {
        return 3;
    }
}

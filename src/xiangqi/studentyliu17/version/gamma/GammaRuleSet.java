package xiangqi.studentyliu17.version.gamma;

import xiangqi.studentyliu17.RuleSet;
import xiangqi.studentyliu17.XiangqiGameState;

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
        return false;
    }
}

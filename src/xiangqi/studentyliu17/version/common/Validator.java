package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiColor;

/**
 * Xiangqi validator
 * 
 * @version Jan 28, 2017
 */
@FunctionalInterface
public interface Validator {
    /**
     * Validate a move from c1 to c2
     *
     * @param c1 The source coordinate
     * @param c2 The destination coordinate
     * @param state The game state
     * @param currentPlayer The current player
     *
     * @return true if a move from c1 to c2 is valid, false otherwise
     */
    boolean validate(CoordinateImpl c1, CoordinateImpl c2, XiangqiGameState state, XiangqiColor
            currentPlayer);
}

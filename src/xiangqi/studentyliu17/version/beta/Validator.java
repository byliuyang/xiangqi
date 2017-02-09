package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiColor;

/**
 * Xiangqi validator
 */
@FunctionalInterface
public interface Validator {
    boolean validate(CoordinateImpl c1, CoordinateImpl c2, int numPiecesInBetween, XiangqiColor
            sourceColor, XiangqiColor destColor);
}

package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiPieceType;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by harryliu on 2/8/17.
 */
public class ValidatorFactory {
    private static BiPredicate<CoordinateImpl, CoordinateImpl> orthogonalValidator =
            (CoordinateImpl c1, CoordinateImpl c2) -> c2.isOrthogonal(c1);
    private static BiPredicate<CoordinateImpl, CoordinateImpl> diagonalValidator   =
            (CoordinateImpl c1, CoordinateImpl c2) -> c2.isDiagonal(c1);
    private static BiPredicate<CoordinateImpl, CoordinateImpl> adjacentValidator   =
            (CoordinateImpl c1, CoordinateImpl c2) -> c2.distanceTo(c1) == 1;
    
    private static BiPredicate<CoordinateImpl, CoordinateImpl> differentCoordinateValidator =
            (CoordinateImpl c1, CoordinateImpl c2) -> !c2.equals(c1);
    
    public static List<BiPredicate<CoordinateImpl, CoordinateImpl>> makeValidators
            (XiangqiPieceType pieceType) {
        
        List<BiPredicate<CoordinateImpl, CoordinateImpl>> validators = new LinkedList<>();
        switch (pieceType) {
            case CHARIOT:
                validators.add(differentCoordinateValidator);
                validators.add(orthogonalValidator);
                break;
            default:
                System.out.println("Not yet implemented!");
        }
        return validators;
    }
    
    private static BiPredicate<CoordinateImpl, CoordinateImpl> makeRangeValidator(int fromRank,
                                                                                  int toRank, int
                                                                                          fromFile, int toFile) {
        return (CoordinateImpl c1, CoordinateImpl c2) -> c2.isInRange(fromRank, toRank, fromFile,
                                                                      toFile);
    }
}

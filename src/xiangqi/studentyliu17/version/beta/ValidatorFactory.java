package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiPieceType;

import java.util.LinkedList;
import java.util.List;

/**
 * Validator factory
 */
public class ValidatorFactory {
    private static Validator orthogonalValidator = (CoordinateImpl c1, CoordinateImpl c2, int
            numberPiecesInBetween) -> c2.isOrthogonal(c1);
    private static Validator diagonalValidator   = (CoordinateImpl c1, CoordinateImpl c2, int
            numberPiecesInBetween) -> c2.isDiagonal(c1);
    private static Validator adjacentValidator   = (CoordinateImpl c1, CoordinateImpl c2, int
            numberPiecesInBetween) -> c2.distanceTo(c1) == 1;
    
    private static Validator differentCoordinateValidator = (CoordinateImpl c1, CoordinateImpl
            c2, int numberPiecesInBetween) -> !c2.equals(c1);
    
    private static Validator jumpOverNoPieceValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                         int numberPiecesInBetween) ->
            numberPiecesInBetween == 0;
    
    public static List<Validator> makeValidators(XiangqiPieceType pieceType) {
        
        List<Validator> validators = new LinkedList<>();
        switch (pieceType) {
            case CHARIOT:
                validators.add(differentCoordinateValidator);
                validators.add(orthogonalValidator);
                validators.add(jumpOverNoPieceValidator);
                break;
            default:
                System.out.println("Not yet implemented!");
        }
        return validators;
    }
    
    private static Validator makeRangeValidator(int fromRank, int toRank, int fromFile, int
            toFile) {
        return (CoordinateImpl c1, CoordinateImpl c2, int numberPiecesInBetween) -> c2.isInRange
                (fromRank, toRank, fromFile, toFile);
    }
}

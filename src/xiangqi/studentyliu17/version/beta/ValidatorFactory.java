package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPieceType;

import java.util.LinkedList;
import java.util.List;

/**
 * Validator factory
 */
public class ValidatorFactory {
    private static Validator orthogonalValidator = (CoordinateImpl c1, CoordinateImpl c2, int
            numPiecesInBetween, XiangqiColor sourceColor, XiangqiColor destColor) ->
            c2.isOrthogonal(c1);
    private static Validator diagonalValidator   = (CoordinateImpl c1, CoordinateImpl c2, int
            numPiecesInBetween, XiangqiColor sourceColor, XiangqiColor destColor) ->
            c2.isDiagonal(c1);
    private static Validator adjacentValidator   = (CoordinateImpl c1, CoordinateImpl c2, int
            numPiecesInBetween, XiangqiColor sourceColor, XiangqiColor destColor) ->
            c2.distanceTo(c1) == 1 || (c2.isDiagonal(c1) && c2.distanceTo(c1) == 2);
    
    private static Validator differentCoordinateValidator = (CoordinateImpl c1, CoordinateImpl
            c2, int numPiecesInBetween, XiangqiColor sourceColor, XiangqiColor destColor) ->
            !c2.equals(c1);
    
    private static Validator differentColorValidator = (CoordinateImpl c1, CoordinateImpl c2, int
            numPiecesInBetween, XiangqiColor sourceColor, XiangqiColor destColor) -> sourceColor
                                                                                     != destColor;
    
    private static Validator jumpOverNoPieceValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                         int numPiecesInBetween, XiangqiColor
                                                                 sourceColor, XiangqiColor
                                                                 destColor) -> numPiecesInBetween
                                                                               == 0;
    
    public static List<Validator> makeValidators(XiangqiPieceType pieceType) {
        
        List<Validator> validators = new LinkedList<>();
        switch (pieceType) {
            case CHARIOT:
                validators.add(differentColorValidator);
                validators.add(differentCoordinateValidator);
                validators.add(orthogonalValidator);
                validators.add(jumpOverNoPieceValidator);
                break;
            case ADVISOR:
                validators.add(differentColorValidator);
                validators.add(differentCoordinateValidator);
                validators.add(diagonalValidator);
                validators.add(adjacentValidator);
                break;
            case GENERAL:
                validators.add(differentColorValidator);
                validators.add(differentCoordinateValidator);
                validators.add(orthogonalValidator);
                validators.add(makeRangeValidator(1, 1, 2, 4));
                validators.add(adjacentValidator);
                break;
            default:
                System.out.println("Not yet implemented!");
        }
        return validators;
    }
    
    private static Validator makeRangeValidator(int fromRank, int toRank, int fromFile, int
            toFile) {
        return (CoordinateImpl c1, CoordinateImpl c2, int numPiecesInBetween, XiangqiColor
                sourceColor, XiangqiColor destColor) -> c2.isInRange(fromRank, toRank, fromFile,
                                                                     toFile);
    }
}

package xiangqi.studentyliu17;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPieceType;

import java.util.LinkedList;
import java.util.List;

/**
 * Validator factory
 *
 * @version Jan 28, 2017
 */
public class ValidatorFactory {
    private static int       GAMMA_RANK_OF_RIVER = 5;
    private static Validator orthogonalValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.isOrthogonal(c1);
    private static Validator diagonalValidator   = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.isDiagonal(c1);
    private static Validator adjacentValidator   = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.distanceTo(c1)
                                                                              == 1 ||
                                                                              (c2.isDiagonal(c1)
                                                                               && c2.distanceTo(c1) == 2);
    
    private static Validator differentCoordinateValidator = (CoordinateImpl c1, CoordinateImpl
            c2, XiangqiGameState state, XiangqiColor currentPlayer) -> !c2.equals(c1);
    
    private static Validator differentColorValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                        XiangqiGameState state, XiangqiColor
                                                                currentPlayer) ->
            state.getPieceAt(c1, currentPlayer).getColor() != state.getPieceAt(c2, currentPlayer).getColor();
    
    private static Validator jumpOverNoPieceValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                         XiangqiGameState state, XiangqiColor
                                                                 currentPlayer) ->
            c2.isOrthogonal(c1) && state.numOrthogonalPiecesInBetween(c1, c2, currentPlayer) == 0;
    
    private static Validator verticalValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                  XiangqiGameState state, XiangqiColor
                                                          currentPlayer) -> c2.isVertical(c1);
    
    private static Validator moveForwardValidator                     = (CoordinateImpl c1,
                                                                         CoordinateImpl c2,
                                                                         XiangqiGameState state,
                                                                         XiangqiColor
                                                                                 currentPlayer)
            -> c2.isInFrontOf(c1);
    private static Validator moveForwardCrossRiverHorizontalValidator = (CoordinateImpl c1,
                                                                         CoordinateImpl c2,
                                                                         XiangqiGameState state,
                                                                         XiangqiColor currentPlayer)
            -> c2.isInFrontOf(c1) || (c1.getRank() > GAMMA_RANK_OF_RIVER && c2.isHorizontal(c1));
    private static Validator sameCoordinateValidator                  = (CoordinateImpl c1,
                                                                         CoordinateImpl c2,
                                                                         XiangqiGameState state,
                                                                         XiangqiColor player) ->
            c1.equals(c2);
    
    /**
     * Creation method for xiangqi game validators
     *
     * @param pieceType The XiangqiPieceType to generate validator for
     * @param version   The XiangqiGameVersion
     *
     * @return list of validators for the given XiangqiPieceType
     */
    public static List<Validator> makeValidators(XiangqiPieceType pieceType, XiangqiGameVersion
            version) {
        
        switch (version) {
            case BETA_XQ:
                return makeBetaValidators(pieceType);
            case GAMMA_XQ:
                return makeGammaValidators(pieceType);
            default:
                System.out.println("Not yet implemented!");
                return null;
        }
    }
    
    /**
     * Creation method for beta xiangqi game validators
     *
     * @param pieceType The XiangqiPieceType to generate validator for
     *
     * @return list of validators for the given XiangqiPieceType
     */
    private static List<Validator> makeBetaValidators(XiangqiPieceType pieceType) {
        
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
            case SOLDIER:
                validators.add(differentColorValidator);
                validators.add(differentCoordinateValidator);
                validators.add(verticalValidator);
                validators.add(adjacentValidator);
                validators.add(moveForwardValidator);
                break;
            default:
                validators.add(differentColorValidator);
                validators.add(sameCoordinateValidator);
                System.out.println("Not yet implemented!");
        }
        return validators;
    }
    
    /**
     * Creation method for gamma xiangqi game validators
     *
     * @param pieceType The XiangqiPieceType to generate validator for
     *
     * @return list of validators for the given XiangqiPieceType
     */
    private static List<Validator> makeGammaValidators(XiangqiPieceType pieceType) {
        
        List<Validator> validators = new LinkedList<>();
        switch (pieceType) {
            case SOLDIER:
                validators.add(differentColorValidator);
                validators.add(differentCoordinateValidator);
                validators.add(orthogonalValidator);
                validators.add(adjacentValidator);
                validators.add(moveForwardCrossRiverHorizontalValidator);
                break;
            case CHARIOT:
                validators.add(differentCoordinateValidator);
                validators.add(differentColorValidator);
                validators.add(orthogonalValidator);
                validators.add(jumpOverNoPieceValidator);
                break;
            default:
                validators.add(differentColorValidator);
                validators.add(sameCoordinateValidator);
                System.out.println("Not yet implemented!");
        }
        return validators;
    }
    
    private static Validator makeRangeValidator(int fromRank, int toRank, int fromFile, int
            toFile) {
        return (CoordinateImpl c1, CoordinateImpl c2, XiangqiGameState state, XiangqiColor
                currentPlayer) -> c2.isInRange(fromRank, toRank, fromFile, toFile);
    }
}

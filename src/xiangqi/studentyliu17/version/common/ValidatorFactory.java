package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiColor;

/**
 * Validator factory
 *
 * @version Jan 28, 2017
 */
public class ValidatorFactory {
    public static Validator orthogonalValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.isOrthogonal(c1);
    public static Validator diagonalValidator   = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.isDiagonal(c1);
    public static Validator adjacentValidator   = (CoordinateImpl c1, CoordinateImpl c2,
                                                    XiangqiGameState state, XiangqiColor
                                                            currentPlayer) -> c2.distanceTo(c1)
                                                                              == 1 ||
                                                                              (c2.isDiagonal(c1)
                                                                               && c2.distanceTo(c1) == 2);
    public static Validator adjacentTwoRanksValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                          XiangqiGameState state, XiangqiColor
                                                                  currentPlayer) -> c2.isDiagonal
            (c1) ? c2.distanceTo(c1) == 4 : c2.distanceTo(c1) == 2;
    
    public static Validator differentCoordinateValidator = (CoordinateImpl c1, CoordinateImpl
            c2, XiangqiGameState state, XiangqiColor currentPlayer) -> !c2.equals(c1);
    
    public static Validator differentColorValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                        XiangqiGameState state, XiangqiColor
                                                                currentPlayer) ->
            state.getPieceAt(c1, currentPlayer).getColor() != state.getPieceAt(c2, currentPlayer).getColor();
    
    public static Validator jumpOverNoPieceValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                         XiangqiGameState state, XiangqiColor
                                                                 currentPlayer) ->
            c2.isOrthogonal(c1) && state.numOrthogonalPiecesInBetween(c1, c2, currentPlayer) == 0;
    
    public static Validator verticalValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                  XiangqiGameState state, XiangqiColor
                                                          currentPlayer) -> c2.isVertical(c1);
    
    public static Validator moveForwardValidator                     = (CoordinateImpl c1,
                                                                         CoordinateImpl c2,
                                                                         XiangqiGameState state,
                                                                         XiangqiColor
                                                                                 currentPlayer)
            -> c2.isInFrontOf(c1);
    
    public static Validator jumpOverNoPieceDiagonallyValidator            = (CoordinateImpl c1,
                                                                         CoordinateImpl c2,
                                                                         XiangqiGameState state,
                                                                         XiangqiColor player) ->
            c2.isDiagonal(c1) && state.numDiagonalPiecesInBetween(c1, c2, player) == 0;
    public static Validator inGeneralPalaceValidator = makeRangeValidator(1, 3,4,6);
    public static Validator sameLocationValidator = (CoordinateImpl c1, CoordinateImpl c2,
                                                     XiangqiGameState state, XiangqiColor player)
            -> c2.equals(c1);
    
    /**
     * Creation method for moveForwardCrossRiverHorizontalValidator
     * 
     * @param rankOfRiver The rank of river
     * 
     * @return moveForwardCrossRiverHorizontalValidator
     */
    public static Validator moveForwardCrossRiverHorizontalValidator(int rankOfRiver) {
       return  (CoordinateImpl c1, CoordinateImpl c2, XiangqiGameState state, XiangqiColor
          currentPlayer) -> c2.isInFrontOf(c1) || (c1.getRank() > rankOfRiver && c2.isHorizontal(c1));
    }
    
    /**
     * Creation method for notCrossingRiverValidator
     * 
     * @param rankOfRiver The rank of river
     * 
     * @return notCrossingRiverValidator
     */
    public static Validator makeNotCrossingRiverValidator(int rankOfRiver) {
        return (CoordinateImpl c1, CoordinateImpl c2, XiangqiGameState state, XiangqiColor
                currentPlayer) -> c2.getRank() <= rankOfRiver;
    }
    
    /**
     * Creation method for rangeValidator
     * 
     * @param fromRank The lower rank
     * @param toRank The higher rank
     * @param fromFile The lower file
     * @param toFile The higher file
     * 
     * @return rangeValidator
     */
    public static Validator makeRangeValidator(int fromRank, int toRank, int fromFile, int
            toFile) {
        return (CoordinateImpl c1, CoordinateImpl c2, XiangqiGameState state, XiangqiColor
                currentPlayer) -> c2.isInRange(fromRank, toRank, fromFile, toFile);
    }
}

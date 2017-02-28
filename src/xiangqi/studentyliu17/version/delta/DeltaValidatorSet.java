package xiangqi.studentyliu17.version.delta;

import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.common.Validator;
import xiangqi.studentyliu17.version.common.ValidatorSet;

import java.util.LinkedList;
import java.util.List;

import static xiangqi.common.XiangqiPieceType.*;
import static xiangqi.studentyliu17.version.common.ValidatorFactory.*;

/**
 * Validator set for delta xiangqi game
 *
 * @version Feb 25, 2017
 */
public class DeltaValidatorSet extends ValidatorSet {
    private final int DELTA_RANK_OF_RIVER = 5;
    /**
     * This method create and put pieces on the board
     */
    @Override
    public void setupValidators() {
        addValidators(CHARIOT, makeDeltaValidators(CHARIOT));
        addValidators(HORSE, makeDeltaValidators(HORSE));
        addValidators(ELEPHANT, makeDeltaValidators(ELEPHANT));
        addValidators(ADVISOR, makeDeltaValidators(ADVISOR));
        addValidators(GENERAL, makeDeltaValidators(GENERAL));
        addValidators(CANNON, makeDeltaValidators(CANNON));
        addValidators(SOLDIER, makeDeltaValidators(SOLDIER));
    }
    
    private List<Validator> makeDeltaValidators(XiangqiPieceType pieceType) {
        List<Validator> validators = new LinkedList<>();
        switch (pieceType){
            case CHARIOT:
                validators.add(differentCoordinateValidator);
                validators.add(differentColorValidator);
                validators.add(orthogonalValidator);
                validators.add(jumpOverNoPieceValidator);
                break;
            case HORSE:
                validators.add(differentCoordinateValidator);
                validators.add(differentColorValidator);
                validators.add(crossTwoGridValidator);
                validators.add(noOrthogonalPieceInMoveDirectionValidator);
                break;
            case ELEPHANT:
                validators.add(differentColorValidator);
                validators.add(diagonalValidator);
                validators.add(adjacentTwoRanksValidator);
                validators.add(jumpOverNoPieceDiagonallyValidator);
                validators.add(makeNotCrossingRiverValidator(DELTA_RANK_OF_RIVER));
                break;
            case ADVISOR:
                validators.add(diagonalValidator);
                validators.add(adjacentValidator);
                validators.add(jumpOverNoPieceDiagonallyValidator);
                validators.add(inGeneralPalaceValidator);
                break;
            case GENERAL:
                validators.add(differentColorValidator);
                validators.add(orthogonalValidator);
                validators.add(adjacentValidator);
                validators.add(inGeneralPalaceValidator);
                break;
            case SOLDIER:
                validators.add(differentColorValidator);
                validators.add(orthogonalValidator);
                validators.add(adjacentValidator);
                validators.add(moveForwardCrossRiverHorizontalValidator(DELTA_RANK_OF_RIVER));
                break;
            default:
                validators.add(sameLocationValidator);
                validators.add(differentColorValidator);
        }
        
        return validators;
    }
}

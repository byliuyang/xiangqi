package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.common.Validator;
import xiangqi.studentyliu17.version.common.ValidatorSet;

import java.util.LinkedList;
import java.util.List;

import static xiangqi.common.XiangqiPieceType.*;
import static xiangqi.studentyliu17.version.common.ValidatorFactory.*;

/**
 * Validator set for beta xiangqi game
 *
 * @version Feb 20, 2017
 */
public class BetaValidatorSet extends ValidatorSet {
    /**
     * This method create and put pieces on the board
     */
    @Override
    public void setupValidators() {
        addValidators(CHARIOT, makeBetaValidators(CHARIOT));
        addValidators(ADVISOR, makeBetaValidators(ADVISOR));
        addValidators(GENERAL, makeBetaValidators(GENERAL));
        addValidators(SOLDIER, makeBetaValidators(SOLDIER));
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
                validators.add(jumpOverNoPieceOrthogonallyValidator);
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
        }
        return validators;
    }
}
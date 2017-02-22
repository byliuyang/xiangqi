package xiangqi.studentyliu17.version.gamma;

import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.common.Validator;
import xiangqi.studentyliu17.version.common.ValidatorSet;

import java.util.LinkedList;
import java.util.List;

import static xiangqi.common.XiangqiPieceType.*;
import static xiangqi.studentyliu17.version.common.ValidatorFactory.*;

/**
 * Validator set for gamma xiangqi game
 *
 * @version Feb 20, 2017
 */
public class GammaValidatorSet extends ValidatorSet {
    private static int       GAMMA_RANK_OF_RIVER = 5;
    
    /**
     * This method create and put pieces on the board
     */
    @Override
    public void setupValidators() {
        addValidators(SOLDIER, makeGammaValidators(SOLDIER));
        addValidators(CHARIOT, makeGammaValidators(CHARIOT));
        addValidators(ELEPHANT, makeGammaValidators(ELEPHANT));
        addValidators(ADVISOR, makeGammaValidators(ADVISOR));
        addValidators(GENERAL, makeGammaValidators(GENERAL));
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
                validators.add(orthogonalValidator);
                validators.add(adjacentValidator);
                validators.add(moveForwardCrossRiverHorizontalValidator(GAMMA_RANK_OF_RIVER));
                break;
            case CHARIOT:
                validators.add(differentCoordinateValidator);
                validators.add(differentColorValidator);
                validators.add(orthogonalValidator);
                validators.add(jumpOverNoPieceValidator);
                break;
            case ELEPHANT:
                validators.add(differentColorValidator);
                validators.add(diagonalValidator);
                validators.add(adjacentTwoRanksValidator);
                validators.add(jumpOverNoPieceDiagonallyValidator);
                validators.add(makeNotCrossingRiverValidator(5));
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
        }
        return validators;
    }
}

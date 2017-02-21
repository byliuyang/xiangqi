package xiangqi.studentyliu17.version.gamma;

import xiangqi.studentyliu17.version.common.ValidatorFactory;
import xiangqi.studentyliu17.version.common.ValidatorSet;

import static xiangqi.common.XiangqiGameVersion.GAMMA_XQ;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Validator set for gamma xiangqi game
 *
 * @version Feb 20, 2017
 */
public class GammaValidatorSet extends ValidatorSet {
    @Override
    public void setupValidators() {
        addValidators(SOLDIER, ValidatorFactory.makeValidators(SOLDIER, GAMMA_XQ));
        addValidators(CHARIOT, ValidatorFactory.makeValidators(CHARIOT, GAMMA_XQ));
        addValidators(ELEPHANT, ValidatorFactory.makeValidators(ELEPHANT, GAMMA_XQ));
        addValidators(ADVISOR, ValidatorFactory.makeValidators(ADVISOR, GAMMA_XQ));
        addValidators(GENERAL, ValidatorFactory.makeValidators(GENERAL, GAMMA_XQ));
    }
}

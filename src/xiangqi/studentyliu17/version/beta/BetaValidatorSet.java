package xiangqi.studentyliu17.version.beta;

import static xiangqi.common.XiangqiGameVersion.BETA_XQ;
import static xiangqi.common.XiangqiPieceType.*;

import xiangqi.studentyliu17.version.common.ValidatorFactory;
import xiangqi.studentyliu17.version.common.ValidatorSet;

/**
 * Validator set for beta xiangqi game
 *
 * @version Feb 20, 2017
 */
public class BetaValidatorSet extends ValidatorSet {
    @Override
    public void setupValidators() {
        addValidators(CHARIOT, ValidatorFactory.makeValidators(CHARIOT, BETA_XQ));
        addValidators(ADVISOR, ValidatorFactory.makeValidators(ADVISOR, BETA_XQ));
        addValidators(GENERAL, ValidatorFactory.makeValidators(GENERAL, BETA_XQ));
        addValidators(SOLDIER, ValidatorFactory.makeValidators(SOLDIER, BETA_XQ));
    }
}
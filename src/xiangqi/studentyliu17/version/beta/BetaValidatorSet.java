package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.Validator;
import xiangqi.studentyliu17.ValidatorFactory;
import xiangqi.studentyliu17.ValidatorSet;

import java.util.Hashtable;
import java.util.List;

import static xiangqi.common.XiangqiGameVersion.BETA_XQ;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Validator set for beta xiangqi game
 *
 * @version Feb 20, 2017
 */
public class BetaValidatorSet extends ValidatorSet {
    @Override
    public void initialize() {
        Hashtable<XiangqiPieceType, List<Validator>> validators = new Hashtable<>();
        validators.put(CHARIOT, ValidatorFactory.makeValidators(CHARIOT, BETA_XQ));
        validators.put(ADVISOR, ValidatorFactory.makeValidators(ADVISOR, BETA_XQ));
        validators.put(GENERAL, ValidatorFactory.makeValidators(GENERAL, BETA_XQ));
        validators.put(SOLDIER, ValidatorFactory.makeValidators(SOLDIER, BETA_XQ));
        setValidators(validators);
    }
}
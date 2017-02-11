package xiangqi.studentyliu17.version.gamma;

import org.junit.Before;
import org.junit.Test;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.studentyliu17.version.XiangqiGameFactory;

import static junit.framework.TestCase.assertNotNull;

/**
 * Test cases for gamma Xiangqi game
 * @version Feb 11, 2016
 */
public class GammaXiangqiTestCases {
    private XiangqiGame game;
    @Before
    public void setup() {
        game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
    }
    
    @Test // 1
    public void createGammaXiangqiGame() {
        assertNotNull(game);
    }
}

package xiangqi.studentyliu17.version.beta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.studentyliu17.version.XiangqiGameFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for beta Xiangqi game
 * @version Feb 8, 2016
 */
public class BetaXiangqiTestCases {
    private XiangqiGame game;
    @BeforeEach
    void setUp() {
        this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
    }
    
    @Test
    void factoryProducesBetaXiangqiGame() {
        assertNotNull(this.game);
    }
}

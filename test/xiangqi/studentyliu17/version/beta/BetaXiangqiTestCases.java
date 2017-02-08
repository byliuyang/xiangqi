package xiangqi.studentyliu17.version.beta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.studentyliu17.TestCoordinate;
import xiangqi.studentyliu17.version.XiangqiGameFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
    @Test // 1
    void factoryProducesBetaXiangqiGame() {
        assertNotNull(this.game);
    }
    
    @Test // 2
    void redChariotValidFirstMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test // 3
    void blackChariotValidSecondMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test
    void chariotTryToMoveToInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1),
                                                            makeCoordinate(2,
                                                                                           2)));
        assertTrue(game.getMoveMessage().length() > 1);
    }
    
    private XiangqiCoordinate makeCoordinate(int rank, int file) {
        return new TestCoordinate(rank, file);
    }
}

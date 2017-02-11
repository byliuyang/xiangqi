package xiangqi.studentyliu17.version.gamma;

import org.junit.Before;
import org.junit.Test;
import xiangqi.common.*;
import xiangqi.studentyliu17.TestCoordinate;
import xiangqi.studentyliu17.version.XiangqiGameFactory;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test cases for gamma Xiangqi game
 *
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
    
    @Test // 2
    public void getPieceAtEmptyLocation() throws Exception {
        for (int rank = 2; rank <= 9; rank++) {
            for (int file = 1; file <= 9; file++) {
                if ((rank == 4 || rank == 7) && file % 2 != 0) continue;
                assertPiece(rank, file, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
            }
        }
        
        assertNoHorse(1);
        assertNoHorse(10);
    }
    
    private void assertNoHorse(int rank) {
        assertPiece(rank, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(rank, 8, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    private void assertPiece(int rank, int file, XiangqiColor aspect, XiangqiPieceType
            expectedPieceType, XiangqiColor expectedColor) {
        XiangqiCoordinate location = makeCoordinate(rank, file);
        XiangqiPiece piece = game.getPieceAt(location, aspect);
        assertNotNull(piece);
        assertEquals(expectedColor, piece.getColor());
        assertEquals(expectedPieceType, piece.getPieceType());
    }
    
    private XiangqiCoordinate makeCoordinate(int rank, int file) {
        return TestCoordinate.makeCoordinate(rank, file);
    }
}

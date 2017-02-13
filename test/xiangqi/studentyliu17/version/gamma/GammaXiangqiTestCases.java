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
    public void getPieceAtEmptyLocation() {
        for (int rank = 2; rank <= 9; rank++) {
            for (int file = 1; file <= 9; file++) {
                if ((rank == 4 || rank == 7) && file % 2 != 0) continue;
                assertPiece(rank, file, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
            }
        }
        
        assertNoHorse(1);
        assertNoHorse(10);
    }
    
    @Test // 3
    public void hasSoilders() {
        assertHasSoldiers(4, XiangqiColor.RED);
        assertHasSoldiers(7, XiangqiColor.BLACK);
    }
    
    @Test // 4
    public void redHasChariots() {
        assertHasChariots(1, XiangqiColor.RED);
    }
    
    @Test // 5
    public void blackHasChariots() {
        assertHasChariots(10, XiangqiColor.BLACK);
    }
    
    @Test // 6
    public void redHasElephants() {
        assertHasElephants(1, XiangqiColor.BLACK);
    }
    
    private void assertHasElephants(int rank, XiangqiColor color) {
        assertPiece(rank, 3, XiangqiColor.RED, XiangqiPieceType.CHARIOT, color);
        assertPiece(rank, 7, XiangqiColor.RED, XiangqiPieceType.CHARIOT, color);
    }
    
    private void assertHasChariots(int rank, XiangqiColor color) {
        assertPiece(rank, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, color);
        assertPiece(rank, 9, XiangqiColor.RED, XiangqiPieceType.CHARIOT, color);
    }
    
    private void assertNoHorse(int rank) {
        assertPiece(rank, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(rank, 8, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    private void assertHasSoldiers(int rank, XiangqiColor color) {
        for (int file = 1; file <= 9; file += 2)
            assertPiece(rank, file, XiangqiColor.RED, XiangqiPieceType.SOLDIER, color);
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

package xiangqi.studentyliu17.version.gamma;

import org.junit.Before;
import org.junit.Test;
import xiangqi.common.*;
import xiangqi.studentyliu17.TestCoordinate;
import xiangqi.XiangqiGameFactory;

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
        assertHasElephants(1, XiangqiColor.RED);
    }
    
    @Test // 7
    public void blackHasElephants() {
        assertHasElephants(10, XiangqiColor.BLACK);
    }
    
    @Test // 8
    public void redHasAdvisors(){
        assertHasAdvisors(1, XiangqiColor.RED);
    }
    
    @Test // 9
    public void blackHasAdvisors() {
        assertHasAdvisors(10, XiangqiColor.BLACK);
    }
    
    @Test // 10
    public void redHasGeneral(){
        assertPiece(1, 5, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test // 11
    public void blackHasGeneral(){
        assertPiece(10, 5, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.BLACK);
    }
    
    @Test // 12
    public void makeMoveFromInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(0, 1), makeCoordinate(1, 1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(11, 1), makeCoordinate(1,
                                                                                            1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 0), makeCoordinate(1,
                                                                                            1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 10), makeCoordinate(1,
                                                                                            1)));
    }
    
    @Test // 13
    public void makeMoveToInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 2), makeCoordinate(0,
                                                                                            1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 2),makeCoordinate(11,
                                                                                           1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 2), makeCoordinate(1,
                                                                                            0)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 2),makeCoordinate(1,
                                                                                           10)));
    }
    
    @Test // 14
    public void redMoveSoldierForwardOneStep() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(5, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test // 15
    public void redMoveSoldierForwardTwoSteps() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(6, 3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(6, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 16
    public void redMoveSoldierLeftwardOneStep() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(4,
                                                                                            2)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(4, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 17
    public void redMoveSoldierRightwardOneStep() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(4,
                                                                                            4)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(4, 4, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 18
    public void redMoveSoldierBackwardOneStep() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(3,
                                                                                            3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 19
    public void redMoveSoldierDiagonallyOneStep() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 2)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(5, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 20
    public void redMoveSoldierLeftwardOneStepAfterCrossedRiver() {
        redSoldierCrossTheRiver();
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 3), makeCoordinate(6,
                                                                                       2)));
        assertPiece(6, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(6, 2, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test // 21
    public void redMoveSoldierRightwardOneStepAfterCrossedRiver() {
        redSoldierCrossTheRiver();
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 3), makeCoordinate(6,
                                                                                       4)));
        assertPiece(6, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(6, 4, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test // 22
    public void redMoveChariotForwardOneStep() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2,
                                                                                       1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 22
    public void redMoveChariotForwardTwoSteps() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3,
                                                                                       1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 23
    public void redMoveChariotDiagonallyOneStep() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2,
                                                                                       2)));
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test // 24
    public void redMoveChariotRightStep() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2,
                                                                                            1)));
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2,
                                                                                       1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
    
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2,
                                                                                       5)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(2, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    }
    
    @Test // 22
    public void redMoveChariotCaptureRedElephantSteps() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1,
                                                                                       3)));
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.ELEPHANT, XiangqiColor.RED);
    }
    
    private void redSoldierCrossTheRiver() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5,
                                                                                       3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(5, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5,
                                                                                       1)));
        assertPiece(4, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(5, 1, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(6,
                                                                                       3)));
        assertPiece(5, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(6, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5,
                                                                                       3)));
        assertPiece(4, 3, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(5, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
    }
    
    private void assertHasAdvisors(int rank, XiangqiColor color) {
        assertPiece(rank, 4, XiangqiColor.RED, XiangqiPieceType.ADVISOR, color);
        assertPiece(rank, 6, XiangqiColor.RED, XiangqiPieceType.ADVISOR, color);
    }
    
    private void assertHasElephants(int rank, XiangqiColor color) {
        assertPiece(rank, 3, XiangqiColor.RED, XiangqiPieceType.ELEPHANT, color);
        assertPiece(rank, 7, XiangqiColor.RED, XiangqiPieceType.ELEPHANT, color);
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

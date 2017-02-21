package xiangqi.studentyliu17.version.beta;

import org.junit.Before;
import org.junit.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;
import xiangqi.studentyliu17.TestCoordinate;

import static junit.framework.TestCase.*;

/**
 * Test cases for beta Xiangqi game
 *
 * @version Feb 8, 2016
 */
public class BetaXiangqiTestCases {
    private XiangqiGame game;
    
    
    @Before()
    public void setUp() {
        this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
    }
    
    @Test
    // 1
    public void factoryProducesBetaXiangqiGame() {
        assertNotNull(this.game);
    }
    
    @Test
    // 2
    public void getPieceAtReturnsNoneNone() {
        for (int rank = 2; rank < 5; rank++) {
            for (int file = 1; file < 6; file++) {
                if (file != 3)
                    assertPiece(rank, file, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor
                            .NONE);
            }
        }
    }
    
    @Test
    // 3
    public void getPieceAtReturnsRedChariot() {
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        
        assertPiece(5, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(5, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    }
    
    @Test
    // 4
    public void getPieceAtReturnsRedAdvisor() {
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 4, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        
        assertPiece(5, 2, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(5, 4, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
    // 5
    public void getPieceAtReturnRedGeneral() {
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
        assertPiece(5, 3, XiangqiColor.BLACK, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test
    // 6
    public void getPieceAtReturnsRedSoldier() {
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(4, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test
    // 7
    public void getPieceAtReturnsBlackChariot() {
        assertPiece(5, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(5, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
    }
    
    @Test
    // 8
    public void getPieceAtReturnsBlackAdvisor() {
        assertPiece(5, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(5, 4, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        
        assertPiece(1, 2, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
    }
    
    @Test
    // 9
    public void getPieceAtReturnsBlackGeneral() {
        assertPiece(5, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.BLACK);
        assertPiece(1, 3, XiangqiColor.BLACK, XiangqiPieceType.GENERAL, XiangqiColor.BLACK);
    }
    
    @Test
    // 10
    public void getPieceAtReturnsBlackSoldier() {
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        assertPiece(2, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
    }
    
    @Test // 11
    public void makeInvalidMoves() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(0, 1), makeCoordinate(1, 1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(10, 1), makeCoordinate(1,
                                                                                            1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 0), makeCoordinate(1,
                                                                                            1)));
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 6), makeCoordinate(1,
                                                                                            1)));
    }
    
    @Test
    // 12
    public void redChariotValidFirstMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test
    // 13
    public void blackChariotValidSecondMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test
    // 14
    public void chariotTryToMoveToInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 3)));
        assertTrue(game.getMoveMessage().length() > 1);
    }
    
    @Test
    // 15
    public void redChariotTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 16
    public void blackChariotTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5)));
        assertPiece(3, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 5, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 17
    public void redAdvisorTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 18
    public void redAdvisorTryToMoveToInValidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(2, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
    // 19
    public void redChariotTryToJumpOverAPiece() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
        assertPiece(2, 5, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 1), makeCoordinate(5, 1)));
        assertPiece(5, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(4, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertTrue(game.getMoveMessage().length() > 1);
    }
    
    @Test
    // 20
    public void redChariotTryToCaptureRedAdvisor() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    }
    
    @Test
    // 21
    public void redAdvisorTryToCaptureRedSoldier() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 3)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
    // 22
    public void redAdvisorTryToMoveTwoSpace() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 1), makeCoordinate(4, 3)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
    // 23
    public void redGeneralTryToMoveDiagonal() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(2, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test
    // 24
    public void redGeneralTryToMoveOutOfFortress() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test
    // 23
    public void redGeneralValidMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2)));
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 25
    public void redGeneralTryToCaptureRedPiece() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test
    // 26
    public void redSoldierValidMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 27
    public void redSoldierMoveHorizontally() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(2, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        assertPiece(2, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test
    // 28
    public void redSoldierMoveForward() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 3), makeCoordinate(2, 3)));
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertTrue(game.getMoveMessage().length() > 1);
    }
    
    @Test
    // 29
    public void redSoldierCaptureBlackSoldier() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 3), makeCoordinate(4, 3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
    // 30
    public void tryMoveToOriginalLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(2, 3)));
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test
    // 31
    public void blackIsUnderCheckmate() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(5, 5)));
        assertPiece(5, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 5, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        assertPiece(2, 3, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 3)));
        assertPiece(2, 3, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(5, 1)));
        assertPiece(5, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(1, 4)));
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(2, 3, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.RED_WINS, game.makeMove(makeCoordinate(3, 3), makeCoordinate(4,
                                                                                             3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(3, 3, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
    }
    
    @Test
    // 32
    public void gameIsDraw() {
        for (int i = 0; i < 4; i++) {
            assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
            assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
            assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
            
            assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
            assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
            assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
            
            assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1)));
            assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
            assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
            
            assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1)));
            assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
            assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        }
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        
        assertEquals(MoveResult.DRAW, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        
    }
    
    private XiangqiCoordinate makeCoordinate(int rank, int file) {
        return new TestCoordinate(rank, file);
    }
    
    private void assertPiece(int rank, int file, XiangqiColor aspect, XiangqiPieceType
            expectedPieceType, XiangqiColor expectedColor) {
        final XiangqiPiece p = game.getPieceAt(makeCoordinate(rank, file), aspect);
        assertEquals(expectedPieceType, p.getPieceType());
        assertEquals(expectedColor, p.getColor());
    }
}

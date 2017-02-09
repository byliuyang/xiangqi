package xiangqi.studentyliu17.version.beta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xiangqi.common.*;
import xiangqi.studentyliu17.TestCoordinate;
import xiangqi.studentyliu17.version.XiangqiGameFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for beta Xiangqi game
 *
 * @version Feb 8, 2016
 */
public class BetaXiangqiTestCases {
    private XiangqiGame game;
    
    @BeforeEach
    void setUp() {
        this.game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
    }
    
    @Test
        // 1
    void factoryProducesBetaXiangqiGame() {
        assertNotNull(this.game);
    }
    
    @Test
        // 2
    void getPieceAtReturnsNoneNone() {
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
    void getPieceAtReturnsRedChariot() {
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        
        assertPiece(5, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(5, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    }
    
    @Test
        // 4
    void getPieceAtReturnsRedAdvisor() {
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 4, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        
        assertPiece(5, 2, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(5, 4, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
        // 5
    void getPieceAtReturnRedGeneral() {
        assertPiece(1, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.RED);
        assertPiece(5, 3, XiangqiColor.BLACK, XiangqiPieceType.GENERAL, XiangqiColor.RED);
    }
    
    @Test
        // 6
    void getPieceAtReturnsRedSoldier() {
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(4, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
    }
    
    @Test
        // 7
    void getPieceAtReturnsBlackChariot() {
        assertPiece(5, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(5, 5, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
    }
    
    @Test
        // 8
    void getPieceAtReturnsBlackAdvisor() {
        assertPiece(5, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(5, 4, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        
        assertPiece(1, 2, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
    }
    
    @Test
        // 9
    void getPieceAtReturnsBlackGeneral() {
        assertPiece(5, 3, XiangqiColor.RED, XiangqiPieceType.GENERAL, XiangqiColor.BLACK);
        assertPiece(1, 3, XiangqiColor.BLACK, XiangqiPieceType.GENERAL, XiangqiColor.BLACK);
    }
    
    @Test
        // 10
    void getPieceAtReturnsBlackSoldier() {
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        assertPiece(2, 3, XiangqiColor.BLACK, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
    }
    
    @Test
        // 11
    void redChariotValidFirstMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test
        // 12
    void blackChariotValidSecondMove() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
    }
    
    @Test
        // 13
    void chariotTryToMoveToInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 2)));
        assertTrue(game.getMoveMessage().length() > 1);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 3)));
        assertTrue(game.getMoveMessage().length() > 1);
    }
    
    @Test
        // 14
    void redChariotTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
        // 15
    void blackChariotTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5)));
        assertPiece(3, 5, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 5, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
        // 16
    void redAdvisorTryToMoveToValidLocation() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    }
    
    @Test
        // 17
    void redAdvisorTryToMoveToInValidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 2)));
        assertPiece(2, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
        // 18
    void redChariotTryToJumpOverAPiece() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
        assertPiece(3, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
        assertPiece(2, 5, XiangqiColor.BLACK, XiangqiPieceType.ADVISOR, XiangqiColor.BLACK);
        assertPiece(1, 4, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
        
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 1), makeCoordinate(5, 1)));
    }
    
    @Test
        // 19
    void redChariotTryToCaptureRedAdvisor() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 1, XiangqiColor.RED, XiangqiPieceType.CHARIOT, XiangqiColor.RED);
    }
    
    @Test
        // 20
    void redAdvisorTryToCaptureRedSoldier() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2,
                                                                                            3)));
        assertPiece(2, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
        // 21
    void redAdvisorTryToMoveTwoSpace() {
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2,
                                                                                            1)));
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
        assertPiece(1, 2, XiangqiColor.RED, XiangqiPieceType.NONE, XiangqiColor.NONE);
    
        assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2,
                                                                                       1)));
        assertPiece(2, 1, XiangqiColor.BLACK, XiangqiPieceType.CHARIOT, XiangqiColor.BLACK);
        assertPiece(1, 1, XiangqiColor.BLACK, XiangqiPieceType.NONE, XiangqiColor.NONE);
    
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 1), makeCoordinate(4,
                                                                                       3)));
        assertPiece(4, 3, XiangqiColor.RED, XiangqiPieceType.SOLDIER, XiangqiColor.BLACK);
        assertPiece(2, 1, XiangqiColor.RED, XiangqiPieceType.ADVISOR, XiangqiColor.RED);
    }
    
    @Test
        // 16
    void tryMoveFromInvalidLocation() {
        assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 1)));
        assertTrue(game.getMoveMessage().length() > 1);
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

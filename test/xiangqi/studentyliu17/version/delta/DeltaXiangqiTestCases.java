package xiangqi.studentyliu17.version.delta;

import org.junit.Before;
import org.junit.Test;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.TestCoordinate;
import xiangqi.studentyliu17.version.common.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletionException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static xiangqi.common.MoveResult.*;
import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Test cases for delta xiangqi game
 *
 * @version Feb 25, 2017
 */
public class DeltaXiangqiTestCases {
    private XiangqiGame game;
    
    private static XiangqiCoordinate c11 = makeCoordinate(1, 1),
            c12 = makeCoordinate(1, 2), c13 = makeCoordinate(1, 3),
            c14 = makeCoordinate(1, 4), c15 = makeCoordinate(1, 5),
            c16 = makeCoordinate(1, 6), c17 = makeCoordinate(1, 7),
            c18 = makeCoordinate(1, 8), c19 = makeCoordinate(1, 9),
            c21 = makeCoordinate(2, 1), c22 = makeCoordinate(2, 2),
            c23 = makeCoordinate(2, 3), c24 = makeCoordinate(2, 4),
            c25 = makeCoordinate(2, 5), c26 = makeCoordinate(2, 6),
            c27 = makeCoordinate(2, 7), c28 = makeCoordinate(2, 8),
            c29 = makeCoordinate(2, 9), c31 = makeCoordinate(3, 1),
            c32 = makeCoordinate(3, 2), c33 = makeCoordinate(3, 3),
            c34 = makeCoordinate(3, 4), c35 = makeCoordinate(3, 5),
            c36 = makeCoordinate(3, 6), c37 = makeCoordinate(3, 7),
            c38 = makeCoordinate(3, 8), c39 = makeCoordinate(3, 9),
            c41 = makeCoordinate(4, 1), c42 = makeCoordinate(4, 2),
            c43 = makeCoordinate(4, 3), c44 = makeCoordinate(4, 4),
            c45 = makeCoordinate(4, 5), c46 = makeCoordinate(4, 6),
            c47 = makeCoordinate(4, 7), c48 = makeCoordinate(4, 8),
            c49 = makeCoordinate(4, 9), c51 = makeCoordinate(5, 1),
            c52 = makeCoordinate(5, 2), c53 = makeCoordinate(5, 3),
            c54 = makeCoordinate(5, 4), c55 = makeCoordinate(5, 5),
            c56 = makeCoordinate(5, 6), c57 = makeCoordinate(5, 7),
            c58 = makeCoordinate(5, 8), c59 = makeCoordinate(5, 9),
            c61 = makeCoordinate(6, 1), c62 = makeCoordinate(6, 2),
            c63 = makeCoordinate(6, 3), c64 = makeCoordinate(6, 4),
            c65 = makeCoordinate(6, 5), c66 = makeCoordinate(6, 6),
            c67 = makeCoordinate(6, 7), c68 = makeCoordinate(6, 8),
            c69 = makeCoordinate(6, 9), c71 = makeCoordinate(7, 1),
            c72 = makeCoordinate(7, 2), c73 = makeCoordinate(7, 3),
            c74 = makeCoordinate(7, 4), c75 = makeCoordinate(7, 5),
            c76 = makeCoordinate(7, 6), c77 = makeCoordinate(7, 7),
            c78 = makeCoordinate(7, 8), c79 = makeCoordinate(7, 9),
            c81 = makeCoordinate(8, 1), c82 = makeCoordinate(8, 2),
            c83 = makeCoordinate(8, 3), c84 = makeCoordinate(8, 4),
            c85 = makeCoordinate(8, 5), c86 = makeCoordinate(8, 6),
            c87 = makeCoordinate(8, 7), c88 = makeCoordinate(8, 8),
            c89 = makeCoordinate(8, 9), c91 = makeCoordinate(9, 1),
            c92 = makeCoordinate(9, 2), c93 = makeCoordinate(9, 3),
            c94 = makeCoordinate(9, 4), c95 = makeCoordinate(9, 5),
            c96 = makeCoordinate(9, 6), c97 = makeCoordinate(9, 7),
            c98 = makeCoordinate(9, 8), c99 = makeCoordinate(9, 9),
            c101 = makeCoordinate(10, 1), c102 = makeCoordinate(10, 2),
            c103 = makeCoordinate(10, 3), c104 = makeCoordinate(10, 4),
            c105 = makeCoordinate(10, 5), c106 = makeCoordinate(10, 6),
            c107 = makeCoordinate(10, 7), c108 = makeCoordinate(10, 8),
            c109 = makeCoordinate(10, 9);
    
    private static XiangqiPiece noPiece = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE),
            redChariot = makePiece(CHARIOT, RED),
            redHorse = makePiece(HORSE, RED),
            redElephant = makePiece(ELEPHANT, RED),
            redAdvior = makePiece(ADVISOR, RED),
            redGeneral = makePiece(GENERAL, RED),
            redCannon = makePiece(CANNON, RED),
            redSoldier = makePiece(SOLDIER, RED),
            blackChariot = makePiece(CHARIOT, BLACK),
            blackHorse = makePiece(HORSE, BLACK),
            blackElephant = makePiece(ELEPHANT, BLACK),
            blackAdvisor = makePiece(ADVISOR, BLACK),
            blackGeneral = makePiece(GENERAL, BLACK),
            blackCannon = makePiece(CANNON, BLACK),
            blackSoldier = makePiece(SOLDIER, BLACK);
    
    private static int DELTA_BOARD_WIDTH = 9, DELTA_BOARD_HEIGHT = 10;
    
    @Before
    public void setup() {
        game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.DELTA_XQ);
    }
    
    @Test // 1
    public void factoryProducesDeltaXiangqiGame () {
        assertNotNull(game);
    }
    
    @Test(expected = CompletionException.class)
    public void invalidCoordinateToGetPieceAt() {
        game.getPieceAt(makeCoordinate(-5, -3), RED);
    }
    
    @Test
    public void correctInitialPositions() {
        assertEquals(redChariot, game.getPieceAt(c11, RED));
        assertEquals(redHorse, game.getPieceAt(c12, RED));
        assertEquals(redElephant, game.getPieceAt(c13, RED));
        assertEquals(redAdvior, game.getPieceAt(c14, RED));
        assertEquals(redGeneral, game.getPieceAt(c15, RED));
        assertEquals(redAdvior, game.getPieceAt(c16, RED));
        assertEquals(redElephant, game.getPieceAt(c17, RED));
        assertEquals(redHorse, game.getPieceAt(c18, RED));
        assertEquals(redChariot, game.getPieceAt(c19, RED));
        assertEquals(redCannon, game.getPieceAt(c32, RED));
        assertEquals(redCannon, game.getPieceAt(c38, RED));
        assertEquals(redSoldier, game.getPieceAt(c41, RED));
        assertEquals(redSoldier, game.getPieceAt(c43, RED));
        assertEquals(redSoldier, game.getPieceAt(c45, RED));
        assertEquals(redSoldier, game.getPieceAt(c47, RED));
        assertEquals(redSoldier, game.getPieceAt(c49, RED));
    
        assertEquals(blackChariot, game.getPieceAt(c11, BLACK));
        assertEquals(blackHorse, game.getPieceAt(c12, BLACK));
        assertEquals(blackElephant, game.getPieceAt(c13, BLACK));
        assertEquals(blackAdvisor, game.getPieceAt(c14, BLACK));
        assertEquals(blackGeneral, game.getPieceAt(c15, BLACK));
        assertEquals(blackAdvisor, game.getPieceAt(c16, BLACK));
        assertEquals(blackElephant, game.getPieceAt(c17, BLACK));
        assertEquals(blackHorse, game.getPieceAt(c18, BLACK));
        assertEquals(blackChariot, game.getPieceAt(c19, BLACK));
        assertEquals(blackCannon, game.getPieceAt(c32, BLACK));
        assertEquals(blackCannon, game.getPieceAt(c38, BLACK));
        assertEquals(blackSoldier, game.getPieceAt(c41, BLACK));
        assertEquals(blackSoldier, game.getPieceAt(c43, BLACK));
        assertEquals(blackSoldier, game.getPieceAt(c45, BLACK));
        assertEquals(blackSoldier, game.getPieceAt(c47, BLACK));
        assertEquals(blackSoldier, game.getPieceAt(c49, BLACK));
    }
    
    @Test
    public void queryAnEmptyLocation() {
        assertEquals(noPiece, game.getPieceAt(c22, RED));
    }
    
    @Test
    public void makeMoveWithInvalidCoordinates() {
        assertEquals(ILLEGAL, game.makeMove(makeCoordinate(0, 3), c14));
        assertEquals(ILLEGAL, game.makeMove(c11, makeCoordinate(1, 11)));
    }
    
    @Test
    public void attemptToMoveOpponentPiece() {
        assertEquals(ILLEGAL, game.makeMove(c109, c99));
    }
    
    @Test
    public void attemptToCaptureOwnPiece()
    {
        assertEquals(ILLEGAL, game.makeMove(c11, c12));
    }
    
    @Test
    public void ensureMessageOnIllegalMove()
    {
        game.makeMove(c11, c13);
        assertTrue(game.getMoveMessage().length() > 5);		// Minimum of 6 characters seems reasonable
    }
    
    @Test
    public void makeValidChariotMove(){
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(redChariot, game.getPieceAt(c21, RED));
        assertEquals(noPiece, game.getPieceAt(c11, RED));
    }
    
    @Test
    public void tryToMoveChariotDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c19, c28));
    }
    
    @Test
    public void tryToMoveChariotJumpOverAPiece() {
        assertEquals(ILLEGAL, game.makeMove(c11, c71));
    }
    
    @Test
    public void makeValidMoveForEachPlayer() {
        game.makeMove(c11, c31);
        assertEquals(redChariot, game.getPieceAt(c31, RED));
        assertEquals(OK, game.makeMove(c19, c39));
        assertEquals(blackChariot, game.getPieceAt(c39, BLACK));
    }
    
    @Test
    public void validHorseMove() {
        assertEquals(OK, game.makeMove(c12, c33));
        assertEquals(noPiece, game.getPieceAt(c12, RED));
    }
    
    @Test
    public void tryToMoveHorseOrthogonally() {
        assertEquals(ILLEGAL, game.makeMove(c12, c22));
    }
    
    @Test
    public void tryToMoveHorseDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c12, c23));
    }
    
    @Test
    public void tryToMoveHorseBlockByElephant() {
        assertEquals(ILLEGAL, game.makeMove(c12, c24));
    }
    
    @Test
    public void tryToMoveHorseBlockBySoldier() {
        assertEquals(OK, game.makeMove(c12, c33));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(ILLEGAL, game.makeMove(c33, c54));
    }
    
    @Test
    public void validElephantMove() {
        assertEquals(OK, game.makeMove(c13, c35));
        assertEquals(noPiece, game.getPieceAt(c13, RED));
        assertEquals(redElephant, game.getPieceAt(c35, RED));
    }
    
    @Test
    public void tryToMoveElephantOrthogonally() {
        assertEquals(ILLEGAL, game.makeMove(c13, c33));
        assertEquals(redElephant, game.getPieceAt(c13, RED));
        assertEquals(noPiece, game.getPieceAt(c33, RED));
    }
    
    @Test
    public void tryToMoveElephantDiagonallyOneStep() {
        assertEquals(ILLEGAL, game.makeMove(c13, c24));
        assertEquals(redElephant, game.getPieceAt(c13, RED));
        assertEquals(noPiece, game.getPieceAt(c24, RED));
    }
    
    @Test
    public void tryToMoveElephantCrossRiver() {
        assertEquals(OK, game.makeMove(c13, c35));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c35, c53));
        assertEquals(OK, game.makeMove(c21, c24));
        assertEquals(ILLEGAL, game.makeMove(c53, c75));
    }
    
    @Test
    public void tryToMoveElephantWhenBlockingElephantEye() {
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c21, c24));
        assertEquals(OK, game.makeMove(c21, c22));
        assertEquals(ILLEGAL, game.makeMove(c13, c35));
    }
    
    @Test
    public void validAdvisorMove() {
        assertEquals(OK, game.makeMove(c14, c25));
        assertEquals(noPiece, game.getPieceAt(c14, RED));
        assertEquals(redAdvior, game.getPieceAt(c25, RED));
    }
    
    @Test
    public void tryToMoveAdvisorOrthogonally() {
        assertEquals(ILLEGAL, game.makeMove(c14, c24));
    }
    
    @Test
    public void tryToMoveAdvisorTwoStepDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c14, c36));
    }
    
    @Test
    public void tryToMoveAdvisorOutOfPalace() {
        assertEquals(ILLEGAL, game.makeMove(c14, c23));
    }
    
    @Test
    public void validGeneralMove() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(noPiece, game.getPieceAt(c15, RED));
        assertEquals(redGeneral, game.getPieceAt(c25, RED));
    }
    
    @Test
    public void tryToMoveGeneralDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c15, c24));
    }
    
    @Test
    public void tryToMovGeneralOrthogonally() {
        assertEquals(ILLEGAL, game.makeMove(c15, c35));
    }
    
    @Test
    public void tryToMoveGeneralOutOfPalace() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(OK, game.makeMove(c21, c22));
        assertEquals(ILLEGAL, game.makeMove(c26, c27));
    }
    
    @Test
    public void soldierValidMove() {
        assertEquals(OK, game.makeMove(c41, c51));
        assertEquals(noPiece, game.getPieceAt(c41, RED));
        assertEquals(redSoldier, game.getPieceAt(c51, RED));
    }
    
    @Test
    public void tryToMoveSoldierDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c41, c52));
    }
    
    @Test
    public void tryToMoveSoldierTwoStepsForward() {
        assertEquals(ILLEGAL, game.makeMove(c41, c61));
    }
    
    @Test
    public void tryToMoveSoldierHorizontally() {
        assertEquals(ILLEGAL, game.makeMove(c41, c42));
    }
    
    @Test
    public void tryToMoveSoldierBackward() {
        assertEquals(ILLEGAL, game.makeMove(c41, c31));
    }
    
    @Test
    public void soldierMoveHorizontallyCrossedRiver() {
        assertEquals(OK, game.makeMove(c41, c51));
        assertEquals(OK, game.makeMove(c41, c51));
        assertEquals(OK, game.makeMove(c51, c61));
        assertEquals(OK, game.makeMove(c51, c61));
        assertEquals(OK, game.makeMove(c61, c62));
        assertEquals(noPiece, game.getPieceAt(c61, RED));
        assertEquals(redSoldier, game.getPieceAt(c62, RED));
    }
    
    @Test
    public void cannonValidMoveOrthogonally() {
        assertEquals(OK, game.makeMove(c32, c31));
        assertEquals(OK, game.makeMove(c32, c42));
        assertEquals(noPiece, game.getPieceAt(c32, RED));
        assertEquals(redCannon, game.getPieceAt(c31, RED));
        assertEquals(noPiece, game.getPieceAt(c32, BLACK));
        assertEquals(blackCannon, game.getPieceAt(c42, BLACK));
    }
    
    @Test
    public void cannonJumpNoPiece() {
        assertEquals(ILLEGAL, game.makeMove(c32, c82));
    }
    
    @Test
    public void cannonValidMoveJumpOverOnePiece() {
        assertEquals(OK, game.makeMove(c32, c102));
    }
    
    @Test
    public void cannonTryMoveDiagonally() {
        assertEquals(ILLEGAL, game.makeMove(c32, c23));
    }
    
    @Test
    public void cannonTryMoveJumpOverTwoPiece() {
        assertEquals(OK, game.makeMove(c32, c33));
        assertEquals(OK, game.makeMove(c32, c33));
        assertEquals(ILLEGAL, game.makeMove(c33, c103));
    }
    
    @Test
    public void completeGameRedWin() {
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c32, c22));
        assertEquals(OK, game.makeMove(c21, c22));
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c22, c23));
        assertEquals(OK, game.makeMove(c25, c24));
        assertEquals(OK, game.makeMove(c23, c24));
        assertEquals(OK, game.makeMove(c19, c29));
        assertEquals(OK, game.makeMove(c24, c25));
        assertEquals(OK, game.makeMove(c29, c25));
        assertEquals(OK, game.makeMove(c25, c24));
        assertEquals(OK, game.makeMove(c38, c28));
        assertEquals(RED_WINS, game.makeMove(c24, c26));
    }
    
    @Test
    public void redInCheckByFlyingGeneral() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c14, c25));
        assertEquals(OK, game.makeMove(c25, c35));
        assertEquals(OK, game.makeMove(c15, c14));
        assertEquals(ILLEGAL, game.makeMove(c35, c36));
    }
    
    @Test
    public void redInCheckByMoveAwayAnPiece() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c14, c25));
        assertEquals(OK, game.makeMove(c32, c36));
        assertEquals(OK, game.makeMove(c15, c14));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(ILLEGAL, game.makeMove(c36, c32));
    }
    
    @Test
    public void redMustProtectGeneralWhenInCheck() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(OK, game.makeMove(c21, c24));
        assertEquals(ILLEGAL, game.makeMove(c11, c21));
    }
    
    @Test
    public void blackInStalemate() {
        Queue<XiangqiCoordinate> redCoordinates = new LinkedList<>(),
                blackCoordinates = new LinkedList<>();
        Queue<XiangqiPiece> redPieces = new LinkedList<>(),
                blackPieces = new LinkedList<>();
        
        blackCoordinates.add(c106);
        blackPieces.add(blackGeneral);
        redCoordinates.add(c15);
        redPieces.add(redGeneral);
        redCoordinates.add(c76);
        redPieces.add(redSoldier);
        
        XiangqiGame game = makeXiangqiGame(redCoordinates, redPieces, blackCoordinates, blackPieces);
        
        assertNotNull(game);
        assertEquals(blackGeneral, game.getPieceAt(c14, BLACK));
        assertEquals(redGeneral, game.getPieceAt(c15, RED));
        assertEquals(redSoldier, game.getPieceAt(c76, RED));
        
        assertEquals(RED_WINS, game.makeMove(c76, c86));
    }
    
    @Test
    public void redUnderPerpetualAttack() {
        assertEquals(OK, game.makeMove(c15, c25));
        assertEquals(OK, game.makeMove(c11, c21));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(OK, game.makeMove(c21, c24));
        assertEquals(OK, game.makeMove(c26, c25));
        assertEquals(OK, game.makeMove(c24, c21));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(OK, game.makeMove(c21, c24));
        assertEquals(OK, game.makeMove(c26, c25));
        assertEquals(OK, game.makeMove(c24, c21));
        assertEquals(OK, game.makeMove(c25, c26));
        assertEquals(RED_WINS, game.makeMove(c21, c24));
    }
    
    // Helpers
    
    private static XiangqiCoordinate makeCoordinate(int rank, int file) {
        return TestCoordinate.makeCoordinate(rank, file);
    }
    
    private static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color) {
        return new DeltaTestPiece(pieceType, color);
    }
    
    private static XiangqiGame makeXiangqiGame(Queue<XiangqiCoordinate> redCoordinates,
                                               Queue<XiangqiPiece> redPieces,
                                               Queue<XiangqiCoordinate> blackCoordinates,
                                               Queue<XiangqiPiece> blackPieces) {
        PiecesInitializer initializer = new PiecesInitializer() {
            @Override
            public void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
                while (!redCoordinates.isEmpty()) pieces.put(redCoordinates.poll(), redPieces.poll());
            }
        
            @Override
            public void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
                while (!blackCoordinates.isEmpty()) pieces.put(blackCoordinates.poll(), blackPieces.poll());
            }
        };
        BoardState boardState = BoardState.makeBoardState(DELTA_BOARD_WIDTH, DELTA_BOARD_HEIGHT, initializer);
        ValidatorSet validatorSet = new DeltaValidatorSet();
        RuleSet ruleSet = new DeltaRuleSet();
        XiangqiGameState gameState = XiangqiGameState.makeGameState(boardState, ruleSet);
        validatorSet.initialize();
        return XiangqiGameImpl.makeXiangqiGame(ruleSet, validatorSet, gameState);
    }
}

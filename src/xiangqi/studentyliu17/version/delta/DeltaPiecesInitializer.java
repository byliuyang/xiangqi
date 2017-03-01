package xiangqi.studentyliu17.version.delta;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.common.CoordinateImpl;
import xiangqi.studentyliu17.version.common.PiecesInitializer;
import xiangqi.studentyliu17.version.common.XiangqiPieceImpl;

import java.util.HashMap;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Piece initializer for delta xiangqi game
 *
 * @version Feb 25, 2017
 */
public class DeltaPiecesInitializer implements PiecesInitializer {
    private static XiangqiCoordinate c11 = makeCoordinate(1, 1),
            c12 = makeCoordinate(1, 2), c13 = makeCoordinate(1, 3),
            c14 = makeCoordinate(1, 4), c15 = makeCoordinate(1, 5),
            c16 = makeCoordinate(1, 6), c17 = makeCoordinate(1, 7),
            c18 = makeCoordinate(1, 8), c19 = makeCoordinate(1, 9),
            c32 = makeCoordinate(3, 2), c38 = makeCoordinate(3, 8),
            c41 = makeCoordinate(4, 1), c43 = makeCoordinate(4, 3),
            c45 = makeCoordinate(4, 5), c47 = makeCoordinate(4, 7),
            c49 = makeCoordinate(4, 9), c71 = makeCoordinate(7, 1),
            c73 = makeCoordinate(7, 3), c75 = makeCoordinate(7, 5),
            c77 = makeCoordinate(7, 7), c79 = makeCoordinate(7, 9),
            c82 = makeCoordinate(8, 2), c88 = makeCoordinate(8, 8),
            c101 = makeCoordinate(10, 1), c102 = makeCoordinate(10, 2),
            c103 = makeCoordinate(10, 3), c104 = makeCoordinate(10, 4),
            c105 = makeCoordinate(10, 5), c106 = makeCoordinate(10, 6),
            c107 = makeCoordinate(10, 7), c108 = makeCoordinate(10, 8),
            c109 = makeCoordinate(10, 9);
            // The predefine coordinates of pieces
    
    private static XiangqiPiece redChariot = makePiece(CHARIOT, RED),
            redHorse = makePiece(HORSE, RED),
            redElephant = makePiece(ELEPHANT, RED),
            redAdvisor = makePiece(ADVISOR, RED),
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
            // The red and black Xiangqi pieces
    
    /**
     * This method create player red's pieces and put them on to board
     *
     * @param pieces The board
     */
    @Override
    public void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        pieces.put(c11, redChariot);
        pieces.put(c12, redHorse);
        pieces.put(c13, redElephant);
        pieces.put(c14, redAdvisor);
        pieces.put(c15, redGeneral);
        pieces.put(c16, redAdvisor);
        pieces.put(c17, redElephant);
        pieces.put(c18, redHorse);
        pieces.put(c19, redChariot);
        pieces.put(c32, redCannon);
        pieces.put(c38, redCannon);
        pieces.put(c41, redSoldier);
        pieces.put(c43, redSoldier);
        pieces.put(c45, redSoldier);
        pieces.put(c47, redSoldier);
        pieces.put(c49, redSoldier);
    }
    
    /**
     * This method create player black's pieces and put them on to board
     *
     * @param pieces The board
     */
    @Override
    public void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        pieces.put(c101, blackChariot);
        pieces.put(c102, blackHorse);
        pieces.put(c103, blackElephant);
        pieces.put(c104, blackAdvisor);
        pieces.put(c105, blackGeneral);
        pieces.put(c106, blackAdvisor);
        pieces.put(c107, blackElephant);
        pieces.put(c108, blackHorse);
        pieces.put(c109, blackChariot);
        pieces.put(c82, blackCannon);
        pieces.put(c88, blackCannon);
        pieces.put(c71, blackSoldier);
        pieces.put(c73, blackSoldier);
        pieces.put(c75, blackSoldier);
        pieces.put(c77, blackSoldier);
        pieces.put(c79, blackSoldier);
    }
    
    /**
     * Creation method for coordinate
     *
     * @param rank The rank of the coordinate
     * @param file The file of the coordinate
     *
     * @return a XiangqiCoordinate with given rank and file
     */
    private static XiangqiCoordinate makeCoordinate(int rank, int file) {
        return CoordinateImpl.makeCoordinate(rank, file);
    }
    
    /**
     * Creation method for xiangqi piece
     *
     * @param pieceType The type of xiangqi piece
     * @param color The color of the xiangqi piece
     *
     * @return a XiangqiPiece with given piece type and color
     */
    private static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color) {
        return XiangqiPieceImpl.makePiece(pieceType, color);
    }
}

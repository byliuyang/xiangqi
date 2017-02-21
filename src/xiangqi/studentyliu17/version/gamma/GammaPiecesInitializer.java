package xiangqi.studentyliu17.version.gamma;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentyliu17.CoordinateImpl;
import xiangqi.studentyliu17.PiecesInitializer;
import xiangqi.studentyliu17.XiangqiPieceImpl;

import java.util.HashMap;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * This class initialize pieces for gamma xiangqi game
 *
 * @version Feb 20, 2017
 */
public class GammaPiecesInitializer implements PiecesInitializer {
    @Override
    public void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        pieces.put(CoordinateImpl.makeCoordinate(1, 1), XiangqiPieceImpl.makePiece
                (CHARIOT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 9), XiangqiPieceImpl.makePiece
                (CHARIOT, RED));
        setupSoldiers(pieces, RED);
        pieces.put(CoordinateImpl.makeCoordinate(1, 3), XiangqiPieceImpl.makePiece
                (ELEPHANT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 7), XiangqiPieceImpl.makePiece
                (ELEPHANT, RED));
    }
    
    @Override
    public void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        pieces.put(CoordinateImpl.makeCoordinate(10, 1), XiangqiPieceImpl.makePiece
                (CHARIOT, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(10, 9), XiangqiPieceImpl.makePiece
                (CHARIOT, BLACK));
        setupSoldiers(pieces, BLACK);
    }
    
    private void setupSoldiers(HashMap<XiangqiCoordinate, XiangqiPiece> pieces, XiangqiColor
            color) {
        for (int i = 1; i <= 10; i += 2)
            pieces.put(CoordinateImpl.makeCoordinate(color == RED ? 4 : 7, i), XiangqiPieceImpl
                    .makePiece(SOLDIER, color));
    }
}

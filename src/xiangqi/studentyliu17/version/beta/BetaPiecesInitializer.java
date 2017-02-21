package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentyliu17.version.common.CoordinateImpl;
import xiangqi.studentyliu17.version.common.PiecesInitializer;
import xiangqi.studentyliu17.version.common.XiangqiPieceImpl;

import java.util.HashMap;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * The class initialize beta xiangqi pieces
 *
 * @version Feb 20, 2017
 */
public class BetaPiecesInitializer implements PiecesInitializer {
    @Override
    public void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        
        pieces.put(CoordinateImpl.makeCoordinate(1, 1), XiangqiPieceImpl.makePiece(CHARIOT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 2), XiangqiPieceImpl.makePiece(ADVISOR, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 3), XiangqiPieceImpl.makePiece(GENERAL, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 4), XiangqiPieceImpl.makePiece(ADVISOR, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 5), XiangqiPieceImpl.makePiece(CHARIOT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(2, 3), XiangqiPieceImpl.makePiece(SOLDIER, RED));
    }
    
    @Override
    public void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces) {
        pieces.put(CoordinateImpl.makeCoordinate(5, 1), XiangqiPieceImpl.makePiece(CHARIOT, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 2), XiangqiPieceImpl.makePiece(ADVISOR, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 3), XiangqiPieceImpl.makePiece(GENERAL, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 4), XiangqiPieceImpl.makePiece(ADVISOR, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 5), XiangqiPieceImpl.makePiece(CHARIOT, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(4, 3), XiangqiPieceImpl.makePiece(SOLDIER, BLACK));
    }
}

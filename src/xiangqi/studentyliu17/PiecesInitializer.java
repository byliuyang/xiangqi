package xiangqi.studentyliu17;

import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;

import java.util.HashMap;

/**
 * Created by harryliu on 2/20/17.
 */
public interface PiecesInitializer {
     void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces);
     void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces);
}

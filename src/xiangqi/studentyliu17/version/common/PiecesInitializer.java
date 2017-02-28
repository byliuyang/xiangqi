package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;

import java.util.HashMap;

/**
 * This class initialize xiangqi pieces
 *
 * @version Feb 28, 2017
 */
public interface PiecesInitializer {
	/**
	 * This method create player red's pieces and put them on to board
	 *  
	 * @param pieces The board
	 */
     void setupRedPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces);
     
     /**
 	 * This method create player black's pieces and put them on to board
 	 *  
 	 * @param pieces The board
 	 */
     void setupBlackPieces(HashMap<XiangqiCoordinate, XiangqiPiece> pieces);
}

package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;

import java.util.HashMap;

/**
 * Created by harryliu on 2/20/17.
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

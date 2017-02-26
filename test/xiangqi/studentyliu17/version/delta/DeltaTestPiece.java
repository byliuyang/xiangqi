package xiangqi.studentyliu17.version.delta;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * Mock xiangqi piece for delta xiangqi game
 *
 * @version Feb 25, 2017
 */
public class DeltaTestPiece implements XiangqiPiece{
    private final XiangqiColor color;
    private final XiangqiPieceType pieceType;
    
    public DeltaTestPiece(XiangqiPieceType pieceType, XiangqiColor color) {
        this.pieceType = pieceType;
        this.color = color;
    }
    
    /**
     * @return the color of the piece
     */
    @Override
    public XiangqiColor getColor() {
        return color;
    }
    
    /**
     * @return the piece type
     */
    @Override
    public XiangqiPieceType getPieceType() {
        return pieceType;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XiangqiPiece)) return false;
        XiangqiPiece other = (XiangqiPiece) obj;
        return other.getColor() == color && other.getPieceType() == pieceType;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", color, pieceType);
    }
}

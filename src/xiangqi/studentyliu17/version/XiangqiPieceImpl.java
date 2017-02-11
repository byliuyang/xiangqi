package xiangqi.studentyliu17.version;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * This class implements XiangqiPiece interface and provides factory methods
 * @version Feb 11, 2016
 */
public class XiangqiPieceImpl implements XiangqiPiece {
    private final XiangqiColor color;
    private final XiangqiPieceType pieceType;
    
    public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color) {
        return new XiangqiPieceImpl(pieceType, color);
    }
    
    public XiangqiPieceImpl(XiangqiPieceType pieceType, XiangqiColor color) {
        this.color = color;
        this.pieceType = pieceType;
    }
    
    /**
     * @return the color of the piece
     */
    @Override
    public XiangqiColor getColor() {
        return this.color;
    }
    
    /**
     * @return the piece type
     */
    @Override
    public XiangqiPieceType getPieceType() {
        return this.pieceType;
    }
}

package xiangqi.studentyliu17;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * This class implements XiangqiPiece interface and provides factory methods
 * 
 * @version Jan 28, 2017
 */
public class XiangqiPieceImpl implements XiangqiPiece {
    private final XiangqiColor color; // The color of xiangqi piece
    private final XiangqiPieceType pieceType; // The type of xiangqi piece
    
    /**
     * Creation method for XiangqiPiece
     * 
     * @param pieceType The type of xiangqi piece
     * @param color The color of the xiangqi piece
     * 
     * @return XiangqiPiece with given type and color
     */
    public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color) {
        return new XiangqiPieceImpl(pieceType, color);
    }
    
    private XiangqiPieceImpl(XiangqiPieceType pieceType, XiangqiColor color) {
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
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XiangqiPiece)) return false;
        XiangqiPiece piece = (XiangqiPiece) obj;
        return piece.getColor() == color && piece.getPieceType() == pieceType;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", color, pieceType);
    }
}

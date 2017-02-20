package xiangqi.studentyliu17;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.beta.CoordinateImpl;

import java.util.*;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Board state for Xiangqi game
 * 
 * @version Jan 28, 2017
 */
public class BoardState {
    public static final  XiangqiColor DEFAULT_COORD_COLOR = XiangqiColor.RED; // Default color of xiangqiboard for storage
    private static final int          BOARD_WIDTH         = 5; // Width of xiangqi board
    private static final int          BOARD_HEIGHT        = 5; // Height of xiangqi board
    private HashMap<XiangqiCoordinate, XiangqiPiece> pieces; // Keep list of Xiangqi coordinate and XiangqiPiece pairs
    private List<XiangqiCoordinate> coordinates; // Keep list of all possible xiangqi coordinates
    
    /**
     * Creation method for Xuangqi board state
     * 
     * @return a Xiangqi BoardState
     */
    public static BoardState makeBoardState() {
        BoardState boardState = new BoardState();
        boardState.initialize();
        return boardState;
    }
    
    /**
     * Convert xiangqi coordinate to target player's perspective
     * 
     * @param coordinate The source coordinate
     * @param fromColor The player perspective for source coordinate 
     * @param toColor The player perspective for target coordinate
     * 
     * @return XiangqiCoordinate for in target player's perspective 
     */
    public static XiangqiCoordinate makeCoordinate(XiangqiCoordinate coordinate, XiangqiColor
            fromColor, XiangqiColor toColor) {
        int rank = coordinate.getRank();
        int file = coordinate.getFile();
        return fromColor == toColor ? CoordinateImpl.makeCoordinate(rank, file) : CoordinateImpl
                .makeCoordinate(BOARD_HEIGHT + 1 - rank, BOARD_WIDTH + 1 - file);
    }
    
    private void initialize() {
        pieces = new HashMap<>();
        setupRedPieces();
        setupBlackPieces();
    
        coordinates = new LinkedList<>();
        for (int rank = 1; rank <= BOARD_HEIGHT; rank++) {
            for (int file = 1; file <= BOARD_WIDTH;file++)
                coordinates.add(CoordinateImpl.makeCoordinate(rank, file));
        }
    }
    
    /**
     * Get the piece at given location
     * 
     * @param where The given location
     * @param aspect The player's perspective of given location
     * 
     * @return the piece at given location
     */
    public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = getFileRespectToRed(where, aspect);
        int rank = getRankRespectToRed(where, aspect);
        
        XiangqiCoordinate coordinate = CoordinateImpl.makeCoordinate(rank, file);
        XiangqiPiece piece = pieces.get(coordinate);
        return piece == null ? XiangqiPieceImpl.makePiece(NONE, XiangqiColor.NONE) : piece;
    }
    
    /**
     * Get the number of pieces are located in between two orthogonal locations
     * 
     * @param source The starting location
     * @param dest The ending location
     * @param currentPlayer The player's perspective of locations
     * 
     * @return the number of pieces are located in between two orthogonal locations
     */
    public int numOrthogonalPiecesInBetween(XiangqiCoordinate source, XiangqiCoordinate dest,
                                            XiangqiColor currentPlayer) {
        int sourceFile = getFileRespectToRed(source, currentPlayer);
        int sourceRank = getRankRespectToRed(source, currentPlayer);
        
        int destFile = getFileRespectToRed(dest, currentPlayer);
        int destRank = getRankRespectToRed(dest, currentPlayer);
        
        int smallerRank = Math.min(sourceRank, destRank);
        int greaterRank = Math.max(sourceRank, destRank);
        int smallerFile = Math.min(sourceFile, destFile);
        int greaterFile = Math.max(sourceFile, destFile);
        
        int numInBetween = 0;
        if (sourceRank == destRank) {
            for (int file = smallerFile + 1; file < greaterFile; file++) {
                if (getPieceAt(CoordinateImpl.makeCoordinate(sourceRank, file),
                               DEFAULT_COORD_COLOR).getPieceType() != XiangqiPieceType.NONE)
                    numInBetween++;
            }
        } else if (sourceFile == destFile) {
            for (int rank = smallerRank + 1; rank < greaterRank; rank++) {
                if (getPieceAt(CoordinateImpl.makeCoordinate(rank, sourceFile),
                               DEFAULT_COORD_COLOR).getPieceType() != XiangqiPieceType.NONE)
                    numInBetween++;
            }
        }
        return numInBetween;
    }
    
    private int getRankRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int rank = where.getRank();
        if (aspect == RED) return rank;
        else return BOARD_HEIGHT + 1 - rank;
    }
    
    private int getFileRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = where.getFile();
        if (aspect == RED) return file;
        else return BOARD_WIDTH + 1 - file;
    }
    
    private void setupRedPieces() {
        pieces.put(CoordinateImpl.makeCoordinate(1, 1), XiangqiPieceImpl.makePiece(CHARIOT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 2), XiangqiPieceImpl.makePiece(ADVISOR, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 3), XiangqiPieceImpl.makePiece(GENERAL, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 4), XiangqiPieceImpl.makePiece(ADVISOR, RED));
        pieces.put(CoordinateImpl.makeCoordinate(1, 5), XiangqiPieceImpl.makePiece(CHARIOT, RED));
        pieces.put(CoordinateImpl.makeCoordinate(2, 3), XiangqiPieceImpl.makePiece(SOLDIER, RED));
    }
    
    private void setupBlackPieces() {
        pieces.put(CoordinateImpl.makeCoordinate(5, 1), XiangqiPieceImpl.makePiece(CHARIOT, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 2), XiangqiPieceImpl.makePiece(ADVISOR, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 3), XiangqiPieceImpl.makePiece(GENERAL, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 4), XiangqiPieceImpl.makePiece(ADVISOR, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(5, 5), XiangqiPieceImpl.makePiece(CHARIOT, BLACK));
        pieces.put(CoordinateImpl.makeCoordinate(4, 3), XiangqiPieceImpl.makePiece(SOLDIER, BLACK));
    }
    
   /**
    * Move a piece from source location to target location
    * 
    * @param source The source location of the piece
    * @param destination The target location of the piece
    * @param aspect The player's perspective of the locations
    */
    public void movePiece(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor
            aspect) {
        XiangqiCoordinate sourceCoord = makeCoordinate(source, aspect, DEFAULT_COORD_COLOR);
        pieces.put(makeCoordinate(destination, aspect, DEFAULT_COORD_COLOR), pieces.get
                (sourceCoord));
        pieces.remove(sourceCoord);
    }
    
    /**
     * Put a piece into a target location
     * 
     * @param piece The piece to put
     * @param destination The target location on the board
     * @param aspect The player's perspective of target location
     */
    public void putPiece(XiangqiPiece piece, XiangqiCoordinate destination, XiangqiColor aspect) {
        
        XiangqiCoordinate covertedDestination = makeCoordinate(destination, aspect,
                                                               DEFAULT_COORD_COLOR);
        pieces.put(covertedDestination, piece);
    }
    
    /**
     * Get all possible xiangqi coordinates
     * 
     * @return all possible xiangqi coordinates
     */
    public List<XiangqiCoordinate> allPossibleCoordinates() {
        return coordinates;
    }
    
    /**
     * Get general location for target player
     * 
     * @param color The player who own the general
     * 
     * @return general location for target player
     */
    public XiangqiCoordinate getGeneralLocation(XiangqiColor color) {
        for(Map.Entry<XiangqiCoordinate, XiangqiPiece> entry: coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            if(piece.getColor() == color && piece.getPieceType() == GENERAL) {
                XiangqiCoordinate coordinate  = entry.getKey();
                return makeCoordinate(coordinate, DEFAULT_COORD_COLOR, color);
            }
        }
        
        return null;
    }
    
    /**
     * Get all coordinates and pieces on the board
     * 
     * @return all coordinates and pieces on the board
     */
    public List<Map.Entry<XiangqiCoordinate, XiangqiPiece>> coordinatesAndPieces() {
        List<Map.Entry<XiangqiCoordinate, XiangqiPiece>> entries = new LinkedList<>();
        pieces.entrySet().forEach(entries::add);
        return entries;
    }
}

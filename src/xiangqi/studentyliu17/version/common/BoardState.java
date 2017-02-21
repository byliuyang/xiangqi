package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.GENERAL;
import static xiangqi.common.XiangqiPieceType.NONE;

/**
 * Board state for Xiangqi game
 *
 * @version Jan 28, 2017
 */
public class BoardState {
    public static final XiangqiColor DEFAULT_COORD_COLOR = XiangqiColor.RED; // Default color of
    // xiangqiboard for storage
    private final int                                      boardWidth; // Width of xiangqi board
    private final int                                      boardHeight; // Height of xiangqi board
    private       HashMap<XiangqiCoordinate, XiangqiPiece> pieces; // Keep list of Xiangqi
    // coordinate and XiangqiPiece pairs
    private       List<XiangqiCoordinate>                  coordinates; // Keep list of all
    // possible xiangqi coordinates
    private PiecesInitializer initializer; // Initializer for setting up xiangqi pieces on the board
    
    
    private BoardState(int boardWidth, int boardHeight, PiecesInitializer
                       initializer) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.initializer = initializer;
    }
    
    public static BoardState makeBoardState(int boardWidth, int boardHeight, PiecesInitializer
            initializer) {
        BoardState boardState = new BoardState(boardWidth, boardHeight,  initializer);
        boardState.initialize();
        return boardState;
    }
    
    /**
     * Convert xiangqi coordinate to target player's perspective
     *
     * @param coordinate The source coordinate
     * @param fromColor  The player perspective for source coordinate
     * @param toColor    The player perspective for target coordinate
     *
     * @return XiangqiCoordinate for in target player's perspective
     */
    public XiangqiCoordinate makeCoordinate(XiangqiCoordinate coordinate, XiangqiColor fromColor,
                                            XiangqiColor toColor) {
        int rank = coordinate.getRank();
        int file = coordinate.getFile();
        return fromColor == toColor ? CoordinateImpl.makeCoordinate(rank, file) : CoordinateImpl
                .makeCoordinate(boardHeight + 1 - rank, boardWidth + 1 - file);
    }
    
    private void initialize() {
        pieces = new HashMap<>();
        setupPieces();
        
        coordinates = new LinkedList<>();
        for (int rank = 1; rank <= boardHeight; rank++) {
            for (int file = 1; file <= boardWidth; file++)
                coordinates.add(CoordinateImpl.makeCoordinate(rank, file));
        }
    }
    
    /**
     * Get the piece at given location
     *
     * @param where  The given location
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
     * Get the number of pieces are located in between two diagonal locations
     *
     * @param source        The starting location
     * @param dest          The ending location
     * @param player The player's perspective of locations
     *
     * @return the number of pieces are located in between two diagonal locations
     */
    public int numDiagonalPiecesInBetween(XiangqiCoordinate source, XiangqiCoordinate dest,
                                          XiangqiColor player) {
        int sourceFile = getFileRespectToRed(source, player);
        int sourceRank = getRankRespectToRed(source, player);
    
        int destFile = getFileRespectToRed(dest, player);
        int destRank = getRankRespectToRed(dest, player);
    
        int smallerRank = Math.min(sourceRank, destRank);
        int greaterRank = Math.max(sourceRank, destRank);
        int smallerFile = Math.min(sourceFile, destFile);
        int greaterFile = Math.max(sourceFile, destFile);
    
        int numInBetween = 0;
        for(int rank = smallerRank + 1; rank < greaterRank; rank++)
            for (int file = smallerFile + 1; file < greaterFile; file++)
                if(rank == file &&
                   getPieceAt(CoordinateImpl.makeCoordinate(rank, file),
                              DEFAULT_COORD_COLOR).getPieceType() != XiangqiPieceType.NONE)
                    numInBetween++;
        return numInBetween;
    }
    
    /**
     * Get the number of pieces are located in between two orthogonal locations
     *
     * @param source        The starting location
     * @param dest          The ending location
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
        else return boardHeight + 1 - rank;
    }
    
    private int getFileRespectToRed(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = where.getFile();
        if (aspect == RED) return file;
        else return boardWidth + 1 - file;
    }
    
    public void setupPieces() {
        initializer.setupRedPieces(pieces);
        initializer.setupBlackPieces(pieces);
    }
    
    /**
     * Move a piece from source location to target location
     *
     * @param source      The source location of the piece
     * @param destination The target location of the piece
     * @param aspect      The player's perspective of the locations
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
     * @param piece       The piece to put
     * @param destination The target location on the board
     * @param aspect      The player's perspective of target location
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
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            if (piece.getColor() == color && piece.getPieceType() == GENERAL) {
                XiangqiCoordinate coordinate = entry.getKey();
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

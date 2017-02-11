package xiangqi.studentyliu17.version.beta;

import xiangqi.common.*;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentyliu17.version.XiangqiPieceImpl;

import java.util.Hashtable;
import java.util.List;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Beta version of Xiangqi game
 *
 * @version Jan 28, 2016
 */
public class BetaXiangqiGame implements XiangqiGame {
    private final int BOARD_WIDTH  = 5;
    private final int BOARD_HEIGHT = 5;
    private XiangqiColor                                 currentPlayer;
    private XiangqiPiece[][]                             board;
    private Hashtable<XiangqiPieceType, List<Validator>> validators;
    private int turns;
    
    /**
     * <p>
     * Make a move in the game. The XiangqiGame instance needs to keep track of the
     * player on move.
     * </p><p>
     * NOTE: If the attempted move is illegal, this method returns a
     * MoveResult.ILLEGAL and ignores the move. It is up to the client to decide what
     * to do.
     * </p>
     *
     * @param source      the coordinate where the piece moving starts
     * @param destination the coordinate where the piece moving ends
     *
     * @return the move result
     */
    @Override
    public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
        if (!isValidMove(source, destination, currentPlayer)) return MoveResult.ILLEGAL;
        
        movePiece(source, destination, currentPlayer);
        if(currentPlayer == BLACK) turns++;
        switchPlayer();
        
        if (isGeneralCheckmated(RED)) return MoveResult.BLACK_WINS;
        else if (isGeneralCheckmated(BLACK)) return MoveResult.RED_WINS;
        else if(isDraw()) return MoveResult.DRAW;
        
        return MoveResult.OK;
    }
    
    private boolean isDraw() {
        return turns >= 10;
    }
    
    private void movePiece(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor
            player) {
        int sourceFile = getFileRespectToRed(source, player);
        int sourceRank = getRankRespectToRed(source, player);
        
        int destFile = getFileRespectToRed(destination, player);
        int destRank = getRankRespectToRed(destination, player);
        
        board[destRank - 1][destFile - 1] = board[sourceRank - 1][sourceFile - 1];
        board[sourceRank - 1][sourceFile - 1] = null;
    }
    
    private void putPiece(XiangqiPiece piece, XiangqiCoordinate destination, XiangqiColor
            player) {
        
        int destFile = getFileRespectToRed(destination, player);
        int destRank = getRankRespectToRed(destination, player);
        
        board[destRank - 1][destFile - 1] = piece;
    }
    
    private void switchPlayer() {
        currentPlayer = currentPlayer == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
    }
    
    /**
     * This method is called to obtain additional information about a move that has just been
     * attempted or made. It must return a message about illegal moves when a client attempts to
     * make
     * such a move.
     * If a valid move is made, the implementation is free to return either an empty string or some
     * other string that describes the board situation.
     *
     * @return
     */
    @Override
    public String getMoveMessage() {
        return "Invalid movement";
    }
    
    /**
     * Method used for querying the board.
     *
     * @param where  the coordinate to access
     * @param aspect the player from whom the request is made. This is needed
     *               in order to determine which location the <code>where</code> parameter
     *               references
     *
     * @return the piece at the specified coordinate. If the coordinate is empty,
     * this returns a piece with the type of XiangqiPieceType.NONE, and a color of
     * XiangqiColor.NONE.
     */
    @Override
    public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
        int file = getFileRespectToRed(where, aspect);
        int rank = getRankRespectToRed(where, aspect);
        
        XiangqiPiece piece = board[rank - 1][file - 1];
        return piece == null ? XiangqiPieceImpl.makePiece(NONE, XiangqiColor.NONE) : piece;
    }
    
    /**
     * In some games, it may be possible to performe some initialization before the
     * game begins. The default method does nothing. This can be overridden by the
     * instances that require initialization.
     *
     * @param args an array of objects that are needed for initialization
     */
    @Override
    public void initialize(Object... args) {
        board = new XiangqiPieceImpl[5][5];
        currentPlayer = XiangqiColor.RED;
        turns = 0;
        
        setupRedPieces();
        setupBlackPieces();
        setupValidators();
    }
    
    private void setupRedPieces() {
        board[0][0] = XiangqiPieceImpl.makePiece(CHARIOT, RED);
        board[0][1] = XiangqiPieceImpl.makePiece(ADVISOR, RED);
        board[0][2] = XiangqiPieceImpl.makePiece(GENERAL, RED);
        board[0][3] = XiangqiPieceImpl.makePiece(ADVISOR, RED);
        board[0][4] = XiangqiPieceImpl.makePiece(CHARIOT, RED);
        board[1][2] = XiangqiPieceImpl.makePiece(SOLDIER, RED);
    }
    
    private void setupBlackPieces() {
        board[4][0] = XiangqiPieceImpl.makePiece(CHARIOT, BLACK);
        board[4][1] = XiangqiPieceImpl.makePiece(ADVISOR, BLACK);
        board[4][2] = XiangqiPieceImpl.makePiece(GENERAL, BLACK);
        board[4][3] = XiangqiPieceImpl.makePiece(ADVISOR, BLACK);
        board[4][4] = XiangqiPieceImpl.makePiece(CHARIOT, BLACK);
        board[3][2] = XiangqiPieceImpl.makePiece(SOLDIER, BLACK);
    }
    
    private void setupValidators() {
        validators = new Hashtable<>();
        validators.put(CHARIOT, ValidatorFactory.makeValidators(CHARIOT));
        validators.put(ADVISOR, ValidatorFactory.makeValidators(ADVISOR));
        validators.put(GENERAL, ValidatorFactory.makeValidators(GENERAL));
        validators.put(SOLDIER, ValidatorFactory.makeValidators(SOLDIER));
    }
    
    private boolean isValidMove(XiangqiCoordinate xiangqiSource, XiangqiCoordinate xiangqiDest,
                                XiangqiColor player) {
        CoordinateImpl source = CoordinateImpl.makeCoordinate(xiangqiSource.getRank(),
                                                              xiangqiSource.getFile());
        CoordinateImpl dest = CoordinateImpl.makeCoordinate(xiangqiDest.getRank(), xiangqiDest
                .getFile());
        
        XiangqiPiece sourcePiece = getPieceAt(source, player);
        if (sourcePiece.getColor() != player) return false;
        XiangqiPieceType sourcePieceType = sourcePiece.getPieceType();
        
        if (!validators.containsKey(sourcePieceType)) return true;
        List<Validator> pieceValidators = validators.get(sourcePieceType);
        
        int numPiecesInBetween = getNumPiecesInBetween(source, dest, player);
        
        XiangqiColor sourcePieceColor = sourcePiece.getColor();
        
        XiangqiPiece destPiece = getPieceAt(dest, player);
        XiangqiColor destPieceColor = destPiece.getColor();
        
        return pieceValidators.stream().allMatch((Validator validator) -> validator.validate
                (source, dest, numPiecesInBetween, sourcePieceColor, destPieceColor));
    }
    
    private int getNumPiecesInBetween(CoordinateImpl source, CoordinateImpl dest, XiangqiColor
            player) {
        int sourceFile = getFileRespectToRed(source, player);
        int sourceRank = getRankRespectToRed(source, player);
        
        int destFile = getFileRespectToRed(dest, player);
        int destRank = getRankRespectToRed(dest, player);
        
        int smallerRank = Math.min(sourceRank, destRank);
        int greaterRank = Math.max(sourceRank, destRank);
        int smallerFile = Math.min(sourceFile, destFile);
        int greaterFile = Math.max(sourceFile, destFile);
        
        int numInBetween = 0;
        if (sourceRank == destRank) {
            for (int file = smallerFile + 1; file < greaterFile; file++) {
                if (board[sourceRank - 1][file - 1] != null) numInBetween++;
            }
        } else if (sourceFile == destFile) {
            for (int rank = smallerRank + 1; rank < greaterRank; rank++) {
                if (board[rank - 1][sourceFile - 1] != null) numInBetween++;
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
    
    private boolean isGeneralUnderAttack(XiangqiColor color, CoordinateImpl generalLocation) {
        
        for (int rank = 1; rank <= BOARD_HEIGHT; rank++) {
            for (int file = 1; file <= BOARD_WIDTH; file++) {
                XiangqiPiece piece = board[rank - 1][file - 1];
                if (piece != null && piece.getColor() != color) {
                    CoordinateImpl pieceLocation = CoordinateImpl.makeCoordinate(rank, file, RED,
                                                                                 piece.getColor(),
                                                                                 BOARD_WIDTH,
                                                                                 BOARD_HEIGHT);
                    if (canAttack(pieceLocation, generalLocation, piece, color)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean canAttack(CoordinateImpl from, CoordinateImpl to, XiangqiPiece  piece,
                              XiangqiColor generalColor) {
        XiangqiColor player = piece.getColor();
        CoordinateImpl generalLocRespectToPiece = CoordinateImpl.makeCoordinate
                (to.getRank(), to.getFile(), generalColor , player, BOARD_WIDTH, BOARD_HEIGHT);
        return isValidMove(from, generalLocRespectToPiece, player);
    }
    
    private CoordinateImpl getGeneralLocation(XiangqiColor color) {
        for (int rank = 1; rank <= BOARD_HEIGHT; rank++) {
            for (int file = 1; file <= BOARD_WIDTH; file++) {
                XiangqiPiece piece = board[rank - 1][file - 1];
                if (piece != null && piece.getColor() == color && piece.getPieceType() == GENERAL)
                    return CoordinateImpl.makeCoordinate(rank, file, RED, color,
                                                         BOARD_WIDTH, BOARD_HEIGHT);
            }
        }
        
        return null;
    }
    
    private boolean isGeneralCheckmated(XiangqiColor color) {
        CoordinateImpl generalLocation = getGeneralLocation(color);
        if (generalLocation == null) return false;
    
        return isGeneralUnderAttack(color, generalLocation) && !canGeneralMoveOutOfCheck(color, generalLocation) &&
               !checkCanBeBlocked(color, generalLocation);
    }
    
    private boolean canGeneralMoveOutOfCheck(XiangqiColor color, CoordinateImpl generalLocation) {
        for (int rank = 1; rank <= BOARD_HEIGHT; rank++) {
            for (int file = 1; file <= BOARD_WIDTH; file++) {
                CoordinateImpl newLocation = CoordinateImpl.makeCoordinate(rank, file, RED,
                                                                           color, BOARD_WIDTH,
                                                                           BOARD_HEIGHT);
                if (isValidMove(generalLocation, newLocation, color)) {
                    XiangqiPiece oldPiece = getPieceAt(newLocation, color);
                    if(oldPiece.getPieceType() == XiangqiPieceType.NONE) oldPiece = null;
                    movePiece(generalLocation, newLocation, color);
                    boolean isUnderAttack = isGeneralUnderAttack(color, newLocation);
                    movePiece(newLocation, generalLocation, color);
                    putPiece(oldPiece, newLocation, color);
                    
                    if (!isUnderAttack) return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean checkCanBeBlocked(XiangqiColor color, CoordinateImpl generalLocation) {
        for (int rank = 1; rank <= BOARD_HEIGHT; rank++) {
            for (int file = 1; file <= BOARD_WIDTH; file++) {
                XiangqiPiece piece = board[rank - 1][file - 1];
                
                if (piece != null && piece.getColor() == color) {
                    XiangqiColor player = piece.getColor();
                    CoordinateImpl pieceLocation = CoordinateImpl.makeCoordinate(rank, file, RED,
                                                                                 player
                            , BOARD_WIDTH, BOARD_HEIGHT);
                    for (int newRank = 1; newRank <= BOARD_HEIGHT; newRank++) {
                        for (int newFile = 1; newFile <= BOARD_WIDTH; newFile++) {
                            CoordinateImpl newLocation = CoordinateImpl.makeCoordinate(newRank,
                                                                                       newFile,
                                                                                     RED,
                                                                                       player
                                    , BOARD_WIDTH, BOARD_HEIGHT);
                            if(isValidMove(pieceLocation, newLocation, player)) {
                                XiangqiPiece oldPiece = getPieceAt(newLocation, player);
                                if(oldPiece.getPieceType() == XiangqiPieceType.NONE) oldPiece =
                                        null;
                                movePiece(pieceLocation, newLocation, player);
                                boolean isUnderAttack = isGeneralUnderAttack(color, generalLocation);
                                movePiece(newLocation, pieceLocation, player);
                                putPiece(oldPiece, newLocation, player);
                                
                                if (!isUnderAttack) return true;
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
}

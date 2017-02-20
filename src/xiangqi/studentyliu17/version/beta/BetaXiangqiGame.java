package xiangqi.studentyliu17.version.beta;

import xiangqi.studentyliu17.BoardState;
import xiangqi.studentyliu17.XiangqiGameState;
import xiangqi.common.*;
import xiangqi.common.XiangqiPieceType;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static xiangqi.studentyliu17.BoardState.DEFAULT_COORD_COLOR;
import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.*;

/**
 * Beta version of Xiangqi game
 *
 * @version Jan 28, 2017
 */
public class BetaXiangqiGame implements XiangqiGame {
    private XiangqiGameState                             gameState; // Keep the game state
    private Hashtable<XiangqiPieceType, List<Validator>> validators; // Keep collection of
    // validators
    private String moveMessage; // Keep current move message
    
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
        if (!isValidMove(source, destination, gameState.getCurrentPlayer())) {
            moveMessage = "Invalid move";
            return MoveResult.ILLEGAL;
        }
        
        gameState.movePiece(source, destination, gameState.getCurrentPlayer());
        gameState.nextTurn();
        
        XiangqiColor currentPlayer = gameState.getCurrentPlayer();
        XiangqiColor otherPlayer = currentPlayer == RED ? BLACK : RED;
        if (isGeneralCheckmated(currentPlayer)) {
            moveMessage = "General will be under checkmate";
            return MoveResult.ILLEGAL;
        }
        else if (isGeneralCheckmated(otherPlayer)) {
            moveMessage = "Win";
            return win(currentPlayer);
        }
        else if (isDraw()) {
            moveMessage = "Draw";
            return MoveResult.DRAW;
        }
        
        gameState.switchPlayer();
    
        moveMessage = null;
        return MoveResult.OK;
    }
    
    /**
     * Convert winner's XiangqiColor to corresponding MoveResult
     *
     * @param currentPlayer The winner's XiangqiColor
     *
     * @return RED_WINS if currentPlayer is equals to RED, BLACK_WINS otherwise
     */
    private MoveResult win(XiangqiColor currentPlayer) {
        return currentPlayer == RED ? MoveResult.RED_WINS : MoveResult.BLACK_WINS;
    }
    
    private boolean isDraw() {
        return gameState.getTurns() >= 10;
    }
    
    /**
     * This method is called to obtain additional information about a move that has just been
     * attempted or made. It must return a message about illegal moves when a client attempts to
     * make
     * such a move.
     * If a valid move is made, the implementation is free to return either an empty string or some
     * other string that describes the board situation.
     *
     */
    @Override
    public String getMoveMessage() {
        return moveMessage;
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
        gameState = XiangqiGameState.makeGameState();
        setupValidators();
    }
    
    /**
     * Set up list of validators
     */
    private void setupValidators() {
        validators = new Hashtable<>();
        validators.put(CHARIOT, ValidatorFactory.makeValidators(CHARIOT));
        validators.put(ADVISOR, ValidatorFactory.makeValidators(ADVISOR));
        validators.put(GENERAL, ValidatorFactory.makeValidators(GENERAL));
        validators.put(SOLDIER, ValidatorFactory.makeValidators(SOLDIER));
    }
    
    /**
     * Check whether the given is valid
     * @param xiangqiSource The 
     * @param xiangqiDest
     * @param player
     * @return
     */
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
        
        return pieceValidators.stream().allMatch((Validator validator) -> validator.validate
                (source, dest, gameState, player));
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
        return gameState.getPieceAt(where, aspect);
    }
    
    /**
     * Check whether general for given player is under attack by any opponent's piece
     * @param color The given player
     * @param generalLocation The location of given player's general
     * @return true if the general is under attack, false otherwise
     */
    private boolean isGeneralUnderAttack(XiangqiColor color, XiangqiCoordinate generalLocation) {
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : gameState.coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            if (piece.getColor() != color) {
                XiangqiCoordinate coordinate = entry.getKey();
                XiangqiCoordinate pieceLocation = BoardState.makeCoordinate(coordinate,
                                                                            DEFAULT_COORD_COLOR,
                                                                            piece.getColor());
                if (canAttack(pieceLocation, generalLocation, piece, color))
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean canAttack(XiangqiCoordinate from, XiangqiCoordinate to, XiangqiPiece piece,
                              XiangqiColor generalColor) {
        XiangqiColor player = piece.getColor();
        XiangqiCoordinate generalLocRespectToPiece = BoardState.makeCoordinate(to, generalColor,
                                                                               player);
        return isValidMove(from, generalLocRespectToPiece, player);
    }
    
    private boolean isGeneralCheckmated(XiangqiColor color) {
        XiangqiCoordinate generalLocation = gameState.getGeneralLocation(color);
        if (generalLocation == null) return false;
        
        return isGeneralUnderAttack(color, generalLocation) &&
               !canGeneralMoveOutOfCheck(color, generalLocation) &&
               !checkCanBeBlocked(color, generalLocation);
    }
    
    private boolean isGeneralUnderAttack(XiangqiCoordinate source, XiangqiCoordinate destination,
                                         XiangqiCoordinate generalLocation, XiangqiColor player,
                                         XiangqiColor generalColor) {
        XiangqiPiece swapPiece = getPieceAt(destination, player);
        gameState.movePiece(source, destination, player);
        boolean isUnderAttack = isGeneralUnderAttack(generalColor, generalLocation);
        gameState.movePiece(destination, source, player);
        if (swapPiece.getPieceType() != XiangqiPieceType.NONE)
            gameState.putPiece(swapPiece, destination, player);
        return isUnderAttack;
    }
    
    private boolean canGeneralMoveOutOfCheck(XiangqiColor color, XiangqiCoordinate
            generalLocation) {
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : gameState.coordinatesAndPieces()) {
            XiangqiCoordinate newLocation = BoardState.makeCoordinate(entry.getKey(), RED, color);
            if (isValidMove(generalLocation, newLocation, color)) {
                if (!isGeneralUnderAttack(generalLocation, newLocation, newLocation, color, color))
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean checkCanBeBlockedByPiece(XiangqiPiece piece, XiangqiCoordinate pieceLocation,
                                             XiangqiColor player, XiangqiColor generalColor,
                                             XiangqiCoordinate generalLocation) {
        for (XiangqiCoordinate newLocation : gameState.allPossibleCoordinates()) {
            XiangqiCoordinate newLocationAspect = BoardState.makeCoordinate(newLocation,
                                                                            DEFAULT_COORD_COLOR,
                                                                            player);
            if (isValidMove(pieceLocation, newLocationAspect, player)) {
                if (!isGeneralUnderAttack(pieceLocation, newLocationAspect, generalLocation,
                                          piece.getColor(), generalColor))
                    return true;
            }
        }
        return false;
    }
    
    private boolean checkCanBeBlocked(XiangqiColor color, XiangqiCoordinate generalLocation) {
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : gameState.coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            XiangqiColor player = piece.getColor();
            if (player == color) {
                XiangqiCoordinate pieceLocation = BoardState.makeCoordinate(entry.getKey(),
                                                                            DEFAULT_COORD_COLOR,
                                                                            player);
                
                if (checkCanBeBlockedByPiece(piece, pieceLocation, player, color, generalLocation))
                    return true;
            }
        }
        
        return false;
    }
}

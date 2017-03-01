package xiangqi.studentyliu17.version.common;

import xiangqi.common.*;

import java.util.List;
import java.util.Map;

import static xiangqi.common.XiangqiColor.BLACK;
import static xiangqi.common.XiangqiColor.RED;
import static xiangqi.common.XiangqiPieceType.GENERAL;
import static xiangqi.studentyliu17.version.common.BoardState.DEFAULT_COORD_COLOR;

/**
 * The class implement a xiangqiGame
 *
 * @version Feb 20, 2017
 */
public class XiangqiGameImpl implements XiangqiGame {
    private XiangqiGameState gameState; // Keep the game state
    private ValidatorSet     validatorSet; // Keep collection of validators
    private String           moveMessage; // Keep current move message
    private RuleSet          ruleSet; // Keep the rule set
    
    /**
     * Constructor of XiangqiGameImpl
     *
     * @param ruleSet The rule set of the game
     * @param validatorSet The validators of the game
     * @param gameState The state of the game
     */
    private XiangqiGameImpl(RuleSet ruleSet, ValidatorSet validatorSet, XiangqiGameState
            gameState) {
        this.validatorSet = validatorSet;
        this.ruleSet = ruleSet;
        this.gameState = gameState;
    }
    
    /**
     * Creation method for XiangqiGameImpl
     *
     * @param ruleSet The rule set of the game
     * @param validatorSet The validators of the game
     * @param gameState The state of the game
     *
     * @return a instance of XiangqiGame
     */
    public static XiangqiGameImpl makeXiangqiGame(RuleSet ruleSet, ValidatorSet validatorSet,
                                                  XiangqiGameState gameState) {
        XiangqiGameImpl game = new XiangqiGameImpl(ruleSet, validatorSet, gameState);
        game.initialize();
        return game;
    }
    
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
        XiangqiColor currentPlayer = gameState.getCurrentPlayer();
        if (!isValidMove(source, destination, currentPlayer)) {
            moveMessage = Message.INVALID_MOVE;
            return MoveResult.ILLEGAL;
        }
        XiangqiColor otherPlayer = currentPlayer == RED ? BLACK : RED;
        XiangqiCoordinate currentPlayerGeneralLoc = gameState.getGeneralLocation(currentPlayer);
        if (isGeneralUnderAttack(source, destination, currentPlayerGeneralLoc.equals(source) ?
                                                      destination :
                                                      currentPlayerGeneralLoc, currentPlayer, currentPlayer)) {
            moveMessage = Message.GENERAL_IN_CHECK;
            return MoveResult.ILLEGAL;
        }
        gameState.movePiece(source, destination, currentPlayer);
        gameState.nextTurn();
        if (isGeneralCheckmatedOrStalemated(otherPlayer)) {
            moveMessage = String.format(Message.PLAYER_WIN, currentPlayer);
            return win(currentPlayer);
        } else if (ruleSet.isDraw(gameState)) {
            moveMessage = Message.GAME_IS_DRAW;
            return MoveResult.DRAW;
        } else if(ruleSet.noPerpetualCheck() &&
                  isGeneralUnderAttack(otherPlayer, gameState.getGeneralLocation(otherPlayer)) &&
                  gameState.isPerpetualCheck())
            return win(otherPlayer);
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
    
    /**
     * This method is called to obtain additional information about a move that has just been
     * attempted or made. It must return a message about illegal moves when a client attempts to
     * make
     * such a move.
     * If a valid move is made, the implementation is free to return either an empty string or some
     * other string that describes the board situation.
     */
    @Override
    public String getMoveMessage() {
        return moveMessage;
    }
    
    /**
     * Check whether the given is valid
     *
     * @param xiangqiSource The source coordinate
     * @param xiangqiDest   The destination coordinate
     * @param player        The player's perspective of the coordinates
     *
     * @return true is the move is valid, false otherwise
     */
    private boolean isValidMove(XiangqiCoordinate xiangqiSource, XiangqiCoordinate xiangqiDest,
                                XiangqiColor player) {
        if(!gameState.isOnBoard(xiangqiSource) || !gameState.isOnBoard(xiangqiDest)) return false;
        CoordinateImpl source = CoordinateImpl.makeCoordinate(xiangqiSource.getRank(),
                                                              xiangqiSource.getFile());
        CoordinateImpl dest = CoordinateImpl.makeCoordinate(xiangqiDest.getRank(), xiangqiDest
                .getFile());
        XiangqiPiece sourcePiece = getPieceAt(source, player);
        if (sourcePiece.getColor() != player) return false;
        XiangqiPieceType sourcePieceType = sourcePiece.getPieceType();
        if (!validatorSet.containsKey(sourcePieceType)) return true;
        List<Validator> pieceValidators = validatorSet.get(sourcePieceType);
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
     *
     * @param color           The given player
     * @param generalLocation The location of given player's general
     *
     * @return true if the general is under attack, false otherwise
     */
    private boolean isGeneralUnderAttack(XiangqiColor color, XiangqiCoordinate generalLocation) {
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : gameState.coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            if (piece.getColor() != color) {
                XiangqiCoordinate coordinate = entry.getKey();
                XiangqiCoordinate pieceLocation = gameState.makeCoordinate(coordinate,
                                                                           DEFAULT_COORD_COLOR,
                                                                           piece.getColor());
                if (canAttack(pieceLocation, generalLocation, piece, color)) return true;
            }
        }
        
        return false;
    }
    
    /**
     * Check whether the given opponent piece can attack the general of the given player
     *
     * @param from The the coordinate of the opponent's piece
     * @param to The the coordinate of general
     * @param piece The opponent's piece
     * @param generalColor The given player
     *
     * @return true if the general of given player is under attack after the move
     */
    private boolean canAttack(XiangqiCoordinate from, XiangqiCoordinate to, XiangqiPiece piece,
                              XiangqiColor generalColor) {
        XiangqiColor player = piece.getColor();
        XiangqiCoordinate generalLocRespectToPiece = gameState.makeCoordinate(to, generalColor,
                                                                              player);
        return isValidMove(from, generalLocRespectToPiece, player);
    }
    
    /**
     * Check whether the general of given is checkmated or statlemated
     * @param color The given player
     *
     * @return true if the general of given is checkmated or statlemated, false otherwise
     */
    private boolean isGeneralCheckmatedOrStalemated(XiangqiColor color) {
        XiangqiCoordinate generalLocation = gameState.getGeneralLocation(color);
        return generalLocation == null || (!canGeneralMoveOutOfCheck(color, generalLocation) &&
                                          !checkCanBeBlocked(color, generalLocation));
    }
    
    /**
     * Check whether the general of given player is still under attack after making a move
     *
     * @param source The source coordinate of the move
     * @param destination The destination coordinate of the move
     * @param generalLocation The location of the general
     * @param player The opponent
     * @param generalColor The given player
     *
     * @return true if the general of given player is still under attack after making a move,
     * false otherwise
     */
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
    
    /**
     * Check the whether the general can move out of check
     *
     * @param color The owner of the general
     * @param generalLocation The coordinate of the general
     *
     * @return true if the general can move out of check, false otherwise
     */
    private boolean canGeneralMoveOutOfCheck(XiangqiColor color, XiangqiCoordinate
            generalLocation) {
        for (XiangqiCoordinate coordinate : gameState.allPossibleCoordinates()) {
            XiangqiCoordinate newLocation = gameState.makeCoordinate(coordinate, RED, color);
            if (isValidMove(generalLocation, newLocation, color)) {
                if (!isGeneralUnderAttack(generalLocation, newLocation, newLocation, color, color))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Check whether the check can be blocked by the given piece
     * @param piece The player's piece
     * @param pieceLocation The coordinate of the piece
     * @param player The player
     * @param generalColor The color of general
     * @param generalLocation The coordinate of the general
     *
     * @return true if the check can be blocked by the given piece, false otherwise
     */
    private boolean checkCanBeBlockedByPiece(XiangqiPiece piece, XiangqiCoordinate pieceLocation,
                                             XiangqiColor player, XiangqiColor generalColor,
                                             XiangqiCoordinate generalLocation) {
        for (XiangqiCoordinate newLocation : gameState.allPossibleCoordinates()) {
            XiangqiCoordinate newLocationAspect = gameState.makeCoordinate(newLocation,
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
    
    /**
     * Check whether the check can be block be any of the player's pieces
     * @param color The player
     * @param generalLocation The coordinate of general
     *
     * @return true if the check can be block be any of the player's pieces, false otherwise
     */
    private boolean checkCanBeBlocked(XiangqiColor color, XiangqiCoordinate generalLocation) {
        for (Map.Entry<XiangqiCoordinate, XiangqiPiece> entry : gameState.coordinatesAndPieces()) {
            XiangqiPiece piece = entry.getValue();
            XiangqiColor player = piece.getColor();
            if (player == color) {
                if (piece.getPieceType() == GENERAL) continue;
                XiangqiCoordinate pieceLocation = gameState.makeCoordinate(entry.getKey(),
                                                                           DEFAULT_COORD_COLOR,
                                                                           player);
                
                if (checkCanBeBlockedByPiece(piece, pieceLocation, player, color, generalLocation))
                    return true;
            }
        }
        return false;
    }
}

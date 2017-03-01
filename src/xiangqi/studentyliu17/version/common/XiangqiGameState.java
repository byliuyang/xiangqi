package xiangqi.studentyliu17.version.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import static xiangqi.common.XiangqiColor.BLACK;

/**
 * The class keeps the state of the game
 *
 * @version Jan 28, 2017
 */
public class XiangqiGameState {
    private Stack<BoardState> boardStates; // Keep track of all the board states
    private BoardState        boardState; // Keep the current board state
    private XiangqiColor      currentPlayer; // Keep track of the current player
    private int               turns; // Keep track of the number of turns passed
    private RuleSet ruleSet; // Keep track of the rule set
    private static final int NUM_MOVES_BETWEEN_CHECKS = 4; // The number of move between two
    // consecutive checks
    
    /**
     * Constructor for XiangqiGameState
     *
     * @param boardState The boardState
     */
    private XiangqiGameState(BoardState boardState, RuleSet ruleSet) {
        this.boardState  = boardState;
        this.ruleSet = ruleSet;
    }
    
    /**
     * Creation method of GameState
     *
     * @param boardState The boardState
     * 
     * @return a instance of XiangqiGameState
     */
    public static XiangqiGameState makeGameState(BoardState boardState, RuleSet ruleSet) {
        XiangqiGameState gameState = new XiangqiGameState(boardState, ruleSet);
        gameState.initialize();
        return gameState;
    }
    
    /**
     * Initialize the game state
     */
    private void initialize() {
        currentPlayer = XiangqiColor.RED;
        turns = 0;
        boardStates = new Stack<>();
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
        return boardState.getPieceAt(where, aspect);
    }
    
    /**
     * Get the number of pieces are located in between two orthogonal locations
     * 
     * @param source The starting location
     * @param dest The ending location
     * @param player The player's perspective of locations
     * 
     * @return the number of pieces are located in between two orthogonal locations
     */
    public int numOrthogonalPiecesInBetween(XiangqiCoordinate source, XiangqiCoordinate dest,
                                            XiangqiColor player) {
        return boardState.numOrthogonalPiecesInBetween(source, dest, player);
    }
    
    /**
     * Switch control to the other player
     */
    public void switchPlayer() {
        currentPlayer = currentPlayer == XiangqiColor.RED ? XiangqiColor.BLACK : XiangqiColor.RED;
        boardStates.push(BoardState.copyBoardState(boardState));
    }
    
    /**
     * Increment turns passed
     */
    public void nextTurn() {
        if (currentPlayer == BLACK) turns++;
    }
    
    /**
     * Get current player
     * @return XiangqiColor of current player
     */
    public XiangqiColor getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Get turns passed
     * @return turns passed
     */
    public int getTurns() {
        return turns;
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
        boardState.movePiece(source, destination, aspect);
    }
    
    /**
     * Put a piece into a target location
     * 
     * @param piece The piece to put
     * @param coordinate The target location on the board
     * @param aspect The player's perspective of target location
     */
    public void putPiece(XiangqiPiece piece, XiangqiCoordinate coordinate, XiangqiColor aspect) {
        boardState.putPiece(piece, coordinate, aspect);
    }
    
    /**
     * Get general location for target player
     * 
     * @param color The player who own the general
     * 
     * @return general location for target player
     */
    public XiangqiCoordinate getGeneralLocation(XiangqiColor color) {
        return boardState.getGeneralLocation(color);
    }
    
    /**
     * Get all coordinates and pieces on the board
     * 
     * @return all coordinates and pieces on the board
     */
    public List<Map.Entry<XiangqiCoordinate, XiangqiPiece>> coordinatesAndPieces() {
        return boardState.coordinatesAndPieces();
    }
    
    /**
     * Get all possible xiangqi coordinates
     * 
     * @return all possible xiangqi coordinates
     */
    public List<XiangqiCoordinate> allPossibleCoordinates() {
        return boardState.allPossibleCoordinates();
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
        return boardState.makeCoordinate(coordinate, fromColor, toColor);
    }
    
    /**
     * Get the number of pieces are located in between two diagonal locations
     * 
     * @param source The starting location
     * @param dest The ending location
     * @param player The player's perspective of locations
     * 
     * @return the number of pieces are located in between two diagonal locations
     */
    public int numDiagonalPiecesInBetween(XiangqiCoordinate source, XiangqiCoordinate dest,
                                          XiangqiColor player) {
        return boardState.numDiagonalPiecesInBetween(source, dest, player);
    }
    
    /**
     * Check whether there is no piece at the given coordinate
     *
     * @param coordinate The given coordinate
     * @param aspect The player's perspective of the coordinate
     *
     * @return true if there is no piece at the given coordinate, false otherwise
     */
    public boolean noPieceAt(CoordinateImpl coordinate, XiangqiColor aspect) {
        return boardState.noPieceAt(coordinate, aspect);
    }
    
    /**
     * Check whether the given coordinate is on the board
     *
     * @param coordinate The given coordinate
     *
     * @return true if the coordinate is on the board, false otherwise
     */
    public boolean isOnBoard(XiangqiCoordinate coordinate) {
        return boardState.isOnBoard(coordinate);
    }
    
    /**
     * Check whether the current player is doing perpetual check to the opponent
     *
     * @return true if the current player is doing perpetual check to the opponent, false otherwise
     */
    public boolean isPerpetualCheck() {
        int numRepetitions = ruleSet.numRepetitions() - 1;
        if(boardStates.size() < numRepetitions * NUM_MOVES_BETWEEN_CHECKS) return false;
        boolean sameBoardState = true;
        Stack<BoardState> tempBoardStates = new Stack<>();
        for (int i = 0; i < numRepetitions; i++){
            for(int j = 0; j < NUM_MOVES_BETWEEN_CHECKS; j++)
                tempBoardStates.push(boardStates.pop());
            if(!tempBoardStates.peek().hasSameConfiguration(boardState))
                sameBoardState = false;
        }
        while (!tempBoardStates.isEmpty())
            boardStates.push(tempBoardStates.pop());
        return sameBoardState;
    }
}

package xiangqi.studentyliu17.version.beta;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;

/**
 * Created by harryliu on 2/8/17.
 */
public class CoordinateImpl implements XiangqiCoordinate {
    private int rank;
    private int file;
    
    private CoordinateImpl(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }
    
    /**
     * Creation method for coordinates
     *
     * @param rank The rank of the coordinate
     * @param file The file of the coordinate
     *
     * @return The CoordinateImpl instance
     */
    public static CoordinateImpl makeCoordinate(int rank, int file) {
        return new CoordinateImpl(rank, file);
    }
    
    /**
     /**
     * Creation method for coordinates
     *
     * @param rank The rank of the coordinate
     * @param file The file of the coordinate
     * @param fromColor
     * @param toColor
     * @param boardWidth
     * @param boardHeight
     * @return The CoordinateImpl instance
     */
    public static CoordinateImpl makeCoordinate(int rank, int file, XiangqiColor fromColor,
                                                XiangqiColor toColor, int boardWidth, int
                                                        boardHeight) {
        
        return fromColor == toColor ? makeCoordinate(rank, file) : makeCoordinate(boardHeight + 1
                                                                                  - rank,
                                                                                  boardWidth + 1
                                                                                  - file);
    }
    
    /**
     * Check whether the other coordinate is orthogonal to this coordinate
     *
     * @param coordinate The other coordinate
     *
     * @return true if the other coordinate is orthogonal to this coordinate, false otherwise
     */
    public boolean isOrthogonal(CoordinateImpl coordinate) {
        return coordinate.rank == rank || coordinate.file == file;
    }
    
    /**
     * Check whether the other coordinate is diagonal to this coordinate
     *
     * @param coordinate The other coordinate
     *
     * @return true if the other coordinate is diagonal to this coordinate, false otherwise
     */
    public boolean isDiagonal(CoordinateImpl coordinate) {
        return Math.abs(coordinate.rank - rank) == Math.abs(coordinate.file - file);
    }
    
    /**
     * Check whether the other coordinate is vertical to this coordinate
     *
     * @param coordinate The other coordinate
     *
     * @return true if the other coordinate is vertical to this coordinate, false otherwise
     */
    public boolean isVertical(CoordinateImpl coordinate) {
        return coordinate.file == file;
    }
    
    /**
     * Return distance between this and the other coordinate
     *
     * @param coordinate The other coordinate
     *
     * @return distance between this and the other coordinate
     */
    public int distanceTo(CoordinateImpl coordinate) {
        return Math.abs(coordinate.rank - rank) + Math.abs(coordinate.file - file);
    }
    
    /**
     * Return true if this coordinate is in front of other coordinate
     *
     * @param coordinate The other coordinate
     *
     * @return true if this coordinate is in front of other coordinate, false otherwise
     */
    public boolean isInFrontOf(CoordinateImpl coordinate) {
        return rank > coordinate.getRank();
    }
    
    /**
     * Check whether rank and file is in the given range
     *
     * @param fromRank The lower bound of rank
     * @param toRank   The upper bound of rank
     * @param fromFile The lower bound of file
     * @param toFile   The upper bound of file
     *
     * @return
     */
    public boolean isInRange(int fromRank, int toRank, int fromFile, int toFile) {
        return fromRank <= rank && rank <= toRank && fromFile <= file && file <= toFile;
    }
    
    /**
     * The rank is the horizontal row number. It is an integer in the inclusive range of 1-10.
     * The rank is always from the specific player's point of view. So Red's rank 1 is
     * Black's rank 10.
     *
     * @return the rank of the location on the board
     */
    @Override
    public int getRank() {
        return rank;
    }
    
    /**
     * The file is the vertical column number. It is an integer in the inclusive range of 1-9.
     * The file is always from the specific player's point of view. So Red's left Cannon is on
     * Red's rank 2 and Black's rank 8.
     *
     * @return the file of the coordinate on the board
     */
    @Override
    public int getFile() {
        return file;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XiangqiCoordinate)) return false;
        XiangqiCoordinate coordinate = (XiangqiCoordinate) obj;
        return coordinate.getRank() == rank && coordinate.getFile() == file;
    }
}

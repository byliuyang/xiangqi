package xiangqi.studentyliu17;

import xiangqi.common.XiangqiCoordinate;

/**
 * Mock coordinate for testing purpose
 */
public class TestCoordinate implements XiangqiCoordinate{
    private final int rank;
    private final int file;
    
    public TestCoordinate(int rank, int file) {
        this.rank = rank;
        this.file = file;
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
    
    public static XiangqiCoordinate makeCoordinate(int rank, int file) {
        return new TestCoordinate(rank, file);
    }
    
    /**
     * Compare whether two coordinates are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XiangqiCoordinate)) return false;
        XiangqiCoordinate coordinate = (XiangqiCoordinate) obj;
        return coordinate.getRank() == rank && coordinate.getFile() == file;
    }
    
    @Override
    public String toString() {
        return String.format("(%s, %s)", rank, file);
    }
    
    /**
     * Get the hash code of the coordinate
     */
    @Override
    public int hashCode() {
        return rank + file;
    }
}

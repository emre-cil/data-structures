public class Tile {
    private int row;
    private int column;
    boolean isVisited = false;
    boolean isObstacle = false;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Move {

    GameField field;
    private int eastC = 1;
    private int southC = 1;
    private int westC = 1;
    private int northC = 1;

    public Move(GameField field) {
        this.field = field;
    }

    void go() throws FileNotFoundException {

        if (field.pl.getRow() == field.getFinishRow() && field.pl.getColumn() == field.getFinishColumn()) { // check if finish point
            System.out.println("Start Point = (" + field.startRow + "," + field.startColumn + ")");
            System.out.println("Finish Point = (" + field.getFinishRow() + "," + field.getFinishColumn() + ")");
            field.pathPrinter(field.listDirectionChanger(field.paths));
            field.AllPaths.add(field.listDirectionChanger(field.paths));
            field.AllPaths.add(field.fullPath);
            field.pathPrinter(field.fullPath);
            field.printOutput(1, field.fullPath);
        } else if (isValid(field.pl.getRow(), field.pl.getColumn() + 1) && eastC == 1) {  //check borders
            if (!field.tile[field.pl.getRow()][field.pl.getColumn() + 1].isObstacle && !field.tile[field.pl.getRow()][field.pl.getColumn() + 1].isVisited) {
                goEast();
                refreshCount();
            } else
                eastC = 0;
            go();
        } else if (isValid(field.pl.getRow() + 1, field.pl.getColumn()) && southC == 1) {  //check borders
            if (!field.tile[field.pl.getRow() + 1][field.pl.getColumn()].isObstacle && !field.tile[field.pl.getRow() + 1][field.pl.getColumn()].isVisited) {
                goSouth();
                refreshCount();
            } else
                southC = 0;
            go();
        } else if (isValid(field.pl.getRow() - 1, field.pl.getColumn()) && northC == 1) {  //check borders
            if (!field.tile[field.pl.getRow() - 1][field.pl.getColumn()].isObstacle && !field.tile[field.pl.getRow() - 1][field.pl.getColumn()].isVisited) {
                goNorth();
                refreshCount();
            } else
                northC = 0;
            go();
        } else if (isValid(field.pl.getRow(), field.pl.getColumn() - 1) && westC == 1) {  //check borders
            if (!field.tile[field.pl.getRow()][field.pl.getColumn() - 1].isObstacle && !field.tile[field.pl.getRow()][field.pl.getColumn() - 1].isVisited) {
                goWest();
                refreshCount();
            } else
                westC = 0;
            go();
        } else {
            field.paths.pop();      //go back
            field.pl.setRow(field.paths.get(0).getRow());
            field.pl.setColumn(field.paths.get(0).getColumn());
            field.fullPath.add(new Position(field.paths.get(0).getRow(), field.paths.get(0).getColumn()));
            refreshCount();
            go();
        }
    }

    void goNorth() {
        directions(field.pl.getRow() - 1, field.pl.getColumn());
    }

    void goSouth() {
        directions(field.pl.getRow() + 1, field.pl.getColumn());
    }

    void goWest() {
        directions(field.pl.getRow(), field.pl.getColumn() - 1);
    }

    void goEast() {
        directions(field.pl.getRow(), field.pl.getColumn() + 1);
    }

    void directions(int row, int column) {
        field.tile[row][column].isVisited = true;
        field.pl.setRow(row);
        field.pl.setColumn(column);
        field.paths.push(new Position(field.pl.getRow(), field.pl.getColumn()));    //adding position from start point
        field.fullPath.add(new Position(field.pl.getRow(), field.pl.getColumn()));
    }

    //checks borders
    boolean isValid(int row, int column) {
        return row >= 0 && row < field.tile.length && column >= 0 && column < field.tile[0].length;
    }

    void refreshCount() {
        eastC = 1;
        southC = 1;
        westC = 1;
        northC = 1;
    }
}

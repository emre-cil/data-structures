import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class GameField {
    final Tile[][] tile;
    private final int obstacleSize;
    private int finishRow;
    private int finishColumn;
    int startRow;
    int startColumn;
    Player pl;
    LinkedList<Position> paths = new LinkedList<>();
    LinkedList<Position> fullPath = new LinkedList<>();
    LinkedList<LinkedList<Position>> AllPaths = new LinkedList<>();

    public GameField(int rowSize, int columnSize, int obstacleSize) {

        this.obstacleSize = obstacleSize;
        tile = new Tile[rowSize][columnSize];
        createClearArea();//The order of the methods is important
        addObstacle();
        addFinishPoint();
        addPlayer();
    }

    void addPlayer() {
        startRow = (int) (Math.random() * tile.length);     //creates random x point
        startColumn = (int) (Math.random() * tile[0].length);  //creates random y point
        if (!tile[startRow][startColumn].isObstacle) {
            Player pl = new Player(startRow, startColumn);
            this.pl = pl;
            tile[startRow][startColumn].isVisited = true;
            paths.push(new Position(startRow, startColumn));
            fullPath.add(new Position(startRow, startColumn));
        }
    }

    void createClearArea() {
        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[0].length; j++) {
                tile[i][j] = new Tile();
            }
        }
    }

    void pathPrinter(LinkedList<Position> path) {
        for (Position i : path) {
            System.out.print("(" + i.getRow() + "," + i.getColumn() + ") ");
        }
        System.out.println();
    }

    void printOutput(int counter,LinkedList<Position> path) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("Turn" + counter + ".txt");
        for (Position i : path) {
            printWriter.print("(" + i.getRow() + "," + i.getColumn() + ") ");
        }
        printWriter.close();
    }

    //this method add finis point to area randomly
    void addFinishPoint() {
        finishRow = (int) (Math.random() * tile.length);
        finishColumn = (int) (Math.random() * tile[0].length);
    }

    //this method add obstacle to area randomly
    void addObstacle() {
        int counter = 0;
        while (counter < obstacleSize) {
            int row = (int) (Math.random() * tile.length);     //creates random x point
            int column = (int) (Math.random() * tile[0].length);  //creates random y point
            if (tile[row][column].isObstacle == false) {
                tile[row][column].isObstacle = true;
                counter++;
            }
        }
    }

    LinkedList<Position> listDirectionChanger(LinkedList<Position> path) {
        LinkedList<Position> path2 = new LinkedList<>();
        for (Position i : path) {
            path2.push(i);
        }
        return path2;
    }

    public Tile[][] getTile() {
        return tile;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public void setFinishRow(int finishRow) {
        this.finishRow = finishRow;
    }

    public int getFinishColumn() {
        return finishColumn;
    }

    public void setFinishColumn(int finishColumn) {
        this.finishColumn = finishColumn;
    }
}

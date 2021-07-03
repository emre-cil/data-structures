/*----------------------------------------------
 * @author Emre Cil
 * @since 15.09.2020
 * GitHub: https://github.com/Kheseyroon
 * MIT license: Copyright 2020 Emre Cil
 * to run -> javac Main.java
 *        -> java Main
 * ----------------------------------------------- */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("config.cfg");
        Scanner scn = new Scanner(file);
        int row = Integer.parseInt(scn.nextLine().substring(8));
        int column = Integer.parseInt(scn.nextLine().substring(7));
        int obstacleSize = Integer.parseInt(scn.nextLine().substring(18));
        GameField field = new GameField(row, column, obstacleSize);
        Move move = new Move(field);
        move.go();
        
    }

}

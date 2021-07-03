/*----------------------------------------------
* @author Emre Cil
* @since 15.01.2020
* GitHub: https://github.com/Kheseyroon
* MIT license: Copyright 2020 Emre Cil
* to run -> javac Main.java
*        -> java Main
* ----------------------------------------------- */

import java.io.File;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        SplayTree tree = new SplayTree();
        File file = new File("input.txt");
        Scanner input = new Scanner(file);
        StringBuilder text = new StringBuilder();
        StringBuilder text2 = new StringBuilder();

        while (input.hasNext() ) {
            text.append(input.next()).append(" ");
            text2.append(input.next()).append(" ");
        }
        String[] operations = text.toString().split(" ");
        String[] numbers = text2.toString().split(" ");


        for (int i = 0; i < operations.length; i++) {
            if (operations[i].toLowerCase().equals("insert")) {
                tree.insert(Integer.parseInt(numbers[i]));
                tree.fillArray();
                tree.addElementToArray(tree.getRoot(), 1);
                tree.printTree();

            } else if (operations[i].toLowerCase().equals("remove")) {
                tree.remove(tree.findNode(Integer.parseInt(numbers[i])));
                tree.fillArray();
                tree.addElementToArray(tree.getRoot(), 1);
                tree.printTree();
            } else if (operations[i].toLowerCase().equals("find")) {
                tree.findNode(Integer.parseInt(numbers[i]));
                tree.fillArray();
                tree.addElementToArray(tree.getRoot(), 1);
                tree.printTree();
            } else {
                System.out.println("wrong input...");
            }
        }
        tree.finish();
    }
}
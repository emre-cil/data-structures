/*----------------------------------------------
* @author Emre Cil
* @since 01.01.2020
* GitHub: https://github.com/Kheseyroon
* MIT license: Copyright 2020 Emre Cil
* to run -> javac Path.java
*        -> java Path input.txt output.txt {number that you want to find without brackets}
* ----------------------------------------------- */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Path {

    public static void main(String[] args) throws Exception {
        File inputFile = new File(args[0]); // file path
        Scanner input = new Scanner(inputFile);     //input
        String a = "0 " + input.nextLine();
        String inputArray[] = a.split(" ");     // split tree elements
        int length = inputArray.length;
        Node[] nodeArray = new Node[inputArray.length];
        for (int i = 0; i < length; i++) {
            if (!inputArray[i].equals("-")) {      // check's if there is empty node
                nodeArray[i] = new Node(inputArray[i]);
                nodeArray[i].index = i - 1;
            } else {
                nodeArray[i] = null;
            }

        }

        BinaryTree tree = new BinaryTree(args[1]);
        tree.x = Integer.parseInt(args[2]);

        tree.addElement(nodeArray);
        tree.storePaths(tree.root);

    }
}

class Node {
    String data;
    Node left, right;
    int index;

    Node(String item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    Node root;
    int x;
    PrintWriter printWriter;

    public BinaryTree(String Path) throws FileNotFoundException {
        File file = new File(Path);
        this.printWriter = new PrintWriter(file);
    }


    void storePaths(Node node) {
        Node path[] = new Node[75];
        findPaths(path, node, 0);
        printWriter.close();
    }

    /**
     * @param path   this array stores paths.
     * @param node   is node of tree.
     * @param length is length of path.
     *               this method find paths recursively.
     */
    void findPaths(Node path[], Node node, int length) {
        if (node == null)
            return;
        path[length] = node;
        length++;
        if (node.left == null && node.right == null) //checks if finis the path.
            outputPaths(path, length, x);
        else {
            findPaths(path, node.left, length);
            findPaths(path, node.right, length);
        }
    }

    /**
     * @param path   this array stores paths
     * @param length is length of path
     * @param x      is the parameter given by user
     *               this method gives output to txt file
     */
    void outputPaths(Node path[], int length, int x) {
        int total = 0;
        for (int i = 0; i < length; i++) {
            total += Integer.parseInt(path[i].data);
        }
        if (total == x) {
            for (int i = 0; i < length; i++) {
                if (i < length - 1)
                    printWriter.print("T[" + path[i].index + "]+");
                else
                    printWriter.print("T[" + path[i].index + "]");
            }
            printWriter.print("=" + x);
            printWriter.println();
        }
    }

    /**
     * @param nodes determines nodes array.
     *              -This method adds element to each node according binary Tree order.
     */
    void addElement(Node[] nodes) {
        if (nodes.length == 0)
            return;
        else {
            root = nodes[1];
        }
        for (int i = 1; i < nodes.length; i++) {
            if (2 * i >= nodes.length)
                break;
            if (nodes[i] == null)
                continue;
            else {
                if (nodes[2 * i] != null) {
                    nodes[i].left = nodes[2 * i];
                }
                if (nodes[2 * i + 1] != null) {
                    nodes[i].right = nodes[2 * i + 1];
                }
            }
        }
    }
}
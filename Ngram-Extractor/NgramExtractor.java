/*----------------------------------------------
* @author Emre Cil
* @since 09.01.2020
* GitHub: https://github.com/Kheseyroon
* MIT license: Copyright 2020 Emre Cil
* to run -> javac NgramExtractor.java
*        -> java Path input.txt output.txt {number of word find without brackets}
* ----------------------------------------------- */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;
public class NgramExtractor {

    public static void main(String[] args) throws Exception {
        String inputL = args[0];
        String outputL = args[1];
        String n = args[2];
        File file = new File(inputL);
        Scanner input = new Scanner(file);
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder text = new StringBuilder();
        while (input.hasNextLine()) {
            text.append(input.nextLine());
            text.append(" ");
        }
        text = new StringBuilder(deleter(text.toString()));
        String[] words = text.toString().split(" ");
        int count = splitter(words, Integer.parseInt(n), map);
        List<Entry<String, Integer>> list = sortMap(map);
        outputWriter(outputL, list, count);

    }

    /**
     * @param words     the words that we inputted.
     * @param inputSize nNgram's n size.
     * @param map       the map which we store the nGrams and Counts
     * @return counts
     * this function add words to map according to nGram order then returns count.
     */
    public static int splitter(String[] words, int inputSize, HashMap<String, Integer> map) {
        String textTemp;
        int count = 0;
        for (int i = 0; i < (words.length - (inputSize - 1)); i++) {
            textTemp = "";
            for (int j = 0; j < inputSize; j++) {
                if (j == 0)
                    textTemp = words[i].toLowerCase();
                else
                    textTemp += " " + words[i + j].toLowerCase();
            }
            if (map.containsKey(textTemp)) {
                map.replace(textTemp, map.get(textTemp) + 1);
            } else map.put(textTemp, 1);
            count++;
        }
        return count;
    }

    /**
     * this method deletes punctuation.
     * @param text given text by user
     * @return returns clear text.
     */
    public static String deleter(String text) {
        text = text.replaceAll("\\p{Punct}", "").replaceAll("\\s+", " ");
        return text;
    }

    /**
     * this method sorts hashmap according to descend order.
     * @param map the hashMap which we will sort.
     * @return returns sorted list.
     */
    public static List<Entry<String, Integer>> sortMap(HashMap<String, Integer> map) {
        Set<Entry<String, Integer>> entrySet = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<>(entrySet);
        list.sort(Entry.comparingByValue());
        Collections.reverse(list);
        return list;
    }

    /**
     * This method writes output  the given file
     * @param outputL output file location
     * @param list    is the sorted list
     * @param count   is count of token
     * @throws FileNotFoundException
     */
    public static void outputWriter(String outputL, List<Entry<String, Integer>> list, int count) throws FileNotFoundException {
        File file = new File(outputL);
        PrintWriter output = new PrintWriter(file);
        output.println("Total number of tokens: " + count + "\nngram,count,frequency");
        for (Entry<String, Integer> e : list) {
            output.println(e.getKey() + "," + e.getValue() + "," + ( e.getValue()) * 1.0 / count);
        }
        output.close();
    }
}

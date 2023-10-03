import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

abstract class LadderGame {

    ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();
    ArrayList<String> remOrderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    /*The method readDictionary reads a list of words from a file, putting all words of the same length into the same array.*/
    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            for (int i = 0; i <= longestWord; i++){
                orderedWords.add(new ArrayList());
            }

            for (String word: allWords){
                int lenOfWord = word.length();
                orderedWords.get(lenOfWord).add(word);
            }

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary.txt: " + ex);
        }
    }

    /*The method resetRemOrderedWords makes the list array remOrderedWords contain only the words of a given length which
     * will be used to make the word ladder.*/
    public void resetRemOrderedWords (int wordLength) {
        remOrderedWords.clear();
        remOrderedWords.addAll(orderedWords.get(wordLength));
    }

    public abstract void play(String start, String end);
}

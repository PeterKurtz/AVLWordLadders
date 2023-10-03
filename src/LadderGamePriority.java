import java.util.ArrayList;
import java.util.Collections;

class LadderGamePriority  extends LadderGame {
    public LadderGamePriority(String dictionaryFile) {
        super(dictionaryFile);
    }

    /*Makes a word ladder using and AVL tree.*/
    public void play(String start, String end) {

        start = start.toLowerCase();
        end = end.toLowerCase();

        int lettersToGo = start.length() - sameLetters(start, end);
        WordInfoPriority word1 = new WordInfoPriority(start, 0, lettersToGo);

        resetRemOrderedWords(start.length());

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        if (!remOrderedWords.contains(start) || !remOrderedWords.contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        ArrayList<Integer> workDone = new ArrayList<Integer>(Collections.nCopies(remOrderedWords.size(), -1));

        AVLTree<WordInfoPriority> ladder = new AVLTree<>();
        ladder.insert(word1);

        int indexOfStartWord = remOrderedWords.indexOf(word1.getWord());
        workDone.set(indexOfStartWord, word1.getPastWork());

        int enqueued = 0;

        System.out.println("Seeking A* solution from " + start + " -> " + end);

        while (true) {
            WordInfoPriority min = ladder.findMin();
            ladder.deleteMin();
            for (String wordOneAway : oneAway(min.getWord())) {

                int indexOfWord = remOrderedWords.indexOf(wordOneAway);

                if ((workDone.get(indexOfWord) <= min.getPastWork() + 1) && (workDone.get(indexOfWord) != -1)) {
                    continue;
                }

                else {
                    int oneAwayToEnd = wordOneAway.length() - sameLetters(wordOneAway, end);
                    WordInfoPriority nextWord = new WordInfoPriority(wordOneAway, min.getPastWork() + 1, oneAwayToEnd, min.getHistory() + " " + wordOneAway);
                    ladder.insert(nextWord);
                    enqueued += 1;
                    if (nextWord.getWord().equals(end)) {
                        System.out.println(" [" + nextWord.getHistory() + "] total enqueues " + enqueued);
                        return;
                    }
                    workDone.set(indexOfWord, nextWord.getPastWork());
                }

            }
        }

    }

    /*Finds the number of letters that are in common for two words.*/
    private int sameLetters(String startWord, String nextWord) {
        int lenOfWord = startWord.length();
        int diffNum = 0;
        for (int i = 0; i < lenOfWord; i++) {
            if (startWord.charAt(i) == nextWord.charAt(i)) {
                diffNum += 1;
            }
        }
        return diffNum;
    }

    /*Finds all one away words from the given word.*/
    private ArrayList<String> oneAway(String word) {
        ArrayList<String> words = new ArrayList<>();

        int lenOfWord = word.length();

        int numOfWords = orderedWords.get(lenOfWord).size();
        int isOneAway;

        for (int i = 0; i < numOfWords; i++) {
            String wordInDict = orderedWords.get(lenOfWord).get(i);
            isOneAway = sameLetters(word, wordInDict);
            if (isOneAway == lenOfWord - 1) {
                words.add(wordInDict);
            }
        }
        return words;
    }
}
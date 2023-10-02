import java.util.ArrayList;

class LadderGameExhaustive extends LadderGame{

    private ArrayList<String> remOrderedWords = new ArrayList<>();

    public LadderGameExhaustive(String dictionaryFile) {
        super(dictionaryFile);
    }

    /*The method play makes the word ladder if it exists and if not then it will print that it does not exist.*/
    public void play(String start, String end) {

        int lenOfWord = start.length();

        resetRemOrderedWords(lenOfWord);

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        start = start.toLowerCase();
        end = end.toLowerCase();

        System.out.println("Seeking exhaustive solution from " + start + " -> " + end);

        if (!remOrderedWords.contains(start) || !remOrderedWords.contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        Queue wordInfoQueue = new Queue();
        WordInfo startObject = new WordInfo(start, 0);

        int totalEnqueued = 0;

        wordInfoQueue.enqueue(startObject);

        WordInfo firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();

        //if wordInfoQueue.isEmpty() is false then the Queue wordInfoQueue does not have any more ladders to check and the word ladder is impossible.
        while (!wordInfoQueue.isEmpty()) {
            String word = firstInQueue.getWord();
            ArrayList<String> oneAwayWords = oneAway(word, true);

            //This for loop inputs each one away word into the queue with the assistance of the method enqueueWord.
            for (String wordInArray:oneAwayWords) {
                enqueueWord(wordInArray, wordInfoQueue, firstInQueue);
                totalEnqueued += 1;

                //This occurs if a word ladder was successfully made. It will end the method.
                if (wordInArray.equals(end)) {
                    WordInfo endWordInfo = (WordInfo) wordInfoQueue.getLastValue();
                    System.out.println(" [" + endWordInfo.getHistory() + "] total enqueues " + totalEnqueued);
                    return;
                }
            }

            //This will take out the queue that was used to find more word ladders.
            wordInfoQueue.dequeue();

            if (!wordInfoQueue.isEmpty()) {
                firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();
            }
            else{
                System.out.println(start + " -> " + end + " : No ladder was found");
            }
        }
    }

    /*The method enqueueWord adds a word ladder into the queue with the purpose of finding the shortest word ladder.*/
    private void enqueueWord(String word, Queue queueObject, WordInfo wordInfoObject) {
        WordInfo addWord = new WordInfo(word, wordInfoObject.getMoves() + 1, wordInfoObject.getHistory() + " " + word);
        queueObject.enqueue(addWord);
    }

    /*The method oneAway returns a list array of all the one away words in the dictionary of the given word.
     * This has the purpose of making new word ladders that may be the word ladder with the final word.*/
    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();
        int lenOfWord = word.length();

        if (!withRemoval) {
            resetRemOrderedWords(lenOfWord);
            word = word.toLowerCase();
        }

        int numOfWords = remOrderedWords.size();

        for (int i = 0; i < numOfWords; i++) {
            String wordInDict = remOrderedWords.get(i);
            boolean isOneAway = diff(word, remOrderedWords.get(i), lenOfWord);
            if (isOneAway) {
                words.add(wordInDict);
            }
        }

        if (withRemoval) {
            remOrderedWords.remove(word);
            for (String removeWord : words) {
                remOrderedWords.remove(removeWord);
            }
        }

        return words;
    }

    /*The method resetRemOrderedWords makes the list array remOrderedWords contain only the words of a given length which
     * will be used to make the word ladder.*/
    private void resetRemOrderedWords (int wordLength) {
        remOrderedWords.clear();
        remOrderedWords.addAll(orderedWords.get(wordLength));
    }

    private boolean diff(String startWord, String dictWord, int lenOfWord) {
        boolean oneAwayBool;
        int diffNum = 0;

        for (int i=0; i < lenOfWord; i++) {
            if (startWord.charAt(i) == dictWord.charAt(i)) {
                diffNum += 1;
            }
        }

        oneAwayBool = diffNum == lenOfWord - 1;

        return oneAwayBool;
    }






}

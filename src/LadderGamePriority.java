class LadderGamePriority  extends LadderGame{
    public LadderGamePriority(String dictionaryFile) {
        super(dictionaryFile);
    }

    public void play(String start, String end) {
        //WordInfoPriority word1 = new WordInfoPriority(start, 1, 0);
        //WordInfoPriority word2 = new WordInfoPriority(end, 1, 0);

        AVLTree<WordInfoPriority> ladder = new AVLTree<>();


    }

    private int estimatedWork(String startWord, String endWord) {
        int lenOfWord = startWord.length();
        int diffNum = 0;
        for (int i=0; i < lenOfWord; i++) {
            if (startWord.charAt(i) == endWord.charAt(i)) {
                diffNum += 1;
            }
        }
        return diffNum;
    }
}

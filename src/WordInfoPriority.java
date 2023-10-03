public class WordInfoPriority implements Comparable<WordInfoPriority>{
    private String word;
    private int moves;
    private String history;
    private int priority;

    public WordInfoPriority(String word, int moves, int estimatedWork) {
        this.word = word;
        this.moves = moves;
        this.history = word;
        this.priority = moves + estimatedWork;
    }

    public WordInfoPriority(String word, int moves, int estimatedWork, String history) {
        this.word = word;
        this.moves = moves;
        this.history = history;
        this.priority = moves + estimatedWork;
    }

    public String getWord() {
        return this.word;
    }

    public int getMoves() {
        return this.moves;
    }

    public String getHistory() {
        return this.history;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(WordInfoPriority d){
        if (this.priority > d.priority) {
            return 1;
        }
        else if (this.priority < d.priority) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("Word %s Moves %d : History[%s]",
                word, moves, history);
    }
}


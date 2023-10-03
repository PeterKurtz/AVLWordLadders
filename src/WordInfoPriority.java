public class WordInfoPriority implements Comparable<WordInfoPriority>{
    private String word;
    private int pastWork;
    private String history;
    private int priority;

    public WordInfoPriority(String word, int pastWork, int estimatedWork) {
        this.word = word;
        this.pastWork = pastWork;
        this.history = word;
        this.priority = pastWork + estimatedWork;
    }

    public WordInfoPriority(String word, int pastWork, int estimatedWork, String history) {
        this.word = word;
        this.pastWork = pastWork;
        this.history = history;
        this.priority = pastWork + estimatedWork;
    }

    public String getWord() {
        return this.word;
    }

    public int getPastWork() {
        return this.pastWork;
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
                word, pastWork, history);
    }
}


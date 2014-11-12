package socialapolitiker.model.dto;

import java.math.BigDecimal;

public class TweetedWord {
    private final String word;
    private final int count;

    public TweetedWord(String word, BigDecimal count) {
        this.word = word;
        this.count = count.intValue();
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

}

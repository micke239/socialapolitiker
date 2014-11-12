package socialapolitiker.model.dto;

import java.math.BigDecimal;

public class PopularWord {
    private final int year;
    private final int month;
    private final String word;
    private final int count;

    public PopularWord(Integer year, Integer month, String word, BigDecimal count) {
        this.year = year;
        this.month = month;
        this.word = word;
        this.count = count.intValue();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

}

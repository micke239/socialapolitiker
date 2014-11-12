package socialapolitiker.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordViewData {
    public static class PopularWord {
        private int year;
        private int month;
        private String word;
        private int count;

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

    public static class Word {
        private String word;
        private int count;

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

    }

    private int id;
    private String twitterScreenName;
    private String urlName;
    private List<PopularWord> popularWords;
    private List<Word> words;

    public int getId() {
        return id;
    }

    @JsonProperty("twitter_screen_name")
    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    @JsonProperty("url_name")
    public String getUrlName() {
        return urlName;
    }

    public List<PopularWord> getPopularWords() {
        return popularWords;
    }

    public List<Word> getWords() {
        return words;
    }

}

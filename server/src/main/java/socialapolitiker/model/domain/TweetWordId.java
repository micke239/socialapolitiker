package socialapolitiker.model.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TweetWordId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String word;
    private Tweet tweet;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @ManyToOne
    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

}

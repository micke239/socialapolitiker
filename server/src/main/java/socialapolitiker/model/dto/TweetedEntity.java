package socialapolitiker.model.dto;

public class TweetedEntity implements Comparable<TweetedEntity> {
    private String entity;
    private long count;

    public String getEntity() {
        return entity;
    }

    public long getCount() {
        return count;
    }

    @Override
    public int compareTo(TweetedEntity tweetedWord) {
        return (int) (tweetedWord.count - count);
    }

}

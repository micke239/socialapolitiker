package socialapolitiker.model.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "politician")
@NamedNativeQueries({
        @NamedNativeQuery(name = "popularWordsPolitician", query = "select t2.year, t2.month, t2.word, t.max as count from "
                + "(select tabl.year,tabl.month,max(count) as max from ( "
                + "select year(t.posted_at) as year, month(t.posted_at) as month, twt.word, sum(twt.count) as count from politician p join tweet t on p.id = t.politician_id join tweet_word_tweet twt on twt.tweet_id = t.id "
                + "where p.twitter_screen_name = ?1 "
                + "and year(t.posted_at) >= 2014 "
                + "group by year(t.posted_at), month(t.posted_at), twt.word "
                + ") as tabl "
                + "group by tabl.year, tabl.month) t "
                + "inner join ( "
                + "select year(t.posted_at) as year, month(t.posted_at) as month, twt.word, sum(twt.count) as count from politician p join tweet t on p.id = t.politician_id join tweet_word_tweet twt on twt.tweet_id = t.id "
                + "where p.twitter_screen_name = ?1 and year(t.posted_at) >= 2014 "
                + "group by year(t.posted_at), month(t.posted_at), twt.word "
                + ") t2 on (t.year = t2.year and t.month = t2.month and t.max = t2.count) "
                + "group by year desc, month desc, max desc", resultSetMapping = "PopularWord"),

        @NamedNativeQuery(name = "tweetedWordsPolitician", query = "select word, sum(twt.count) as count "
                + "from tweet_word_tweet twt join tweet on twt.tweet_id = tweet.id "
                + " join politician p on p.id = tweet.politician_id where p.twitter_screen_name = ?1 "
                + "group by word order by sum(twt.count) desc limit 100", resultSetMapping = "TweetedWord") })
// ignore hibernate lazy-loading properties
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "party" })
public class Politician {
    private Long id;
    private Party party;
    private String name;
    private String twitterScreenName;
    private String twitterId;
    private Date createdAt;
    private Date updatedAt;
    private Date lastImport;
    private Date forceDisabledAt;
    private String description;
    private String profileImageUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @ManyToOne
    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastImport() {
        return lastImport;
    }

    public void setLastImport(Date lastImport) {
        this.lastImport = lastImport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Date getForceDisabledAt() {
        return forceDisabledAt;
    }

    public void setForceDisabledAt(Date forceDisabledAt) {
        this.forceDisabledAt = forceDisabledAt;
    }

}

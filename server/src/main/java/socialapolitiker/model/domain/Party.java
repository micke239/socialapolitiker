package socialapolitiker.model.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "party")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PopularWord", classes = { @ConstructorResult(targetClass = PopularWord.class, columns = {
                @ColumnResult(name = "year"), @ColumnResult(name = "month"), @ColumnResult(name = "word"),
                @ColumnResult(name = "count") }) }),
        @SqlResultSetMapping(name = "TweetedWord", classes = { @ConstructorResult(targetClass = TweetedWord.class, columns = {
                @ColumnResult(name = "word"), @ColumnResult(name = "count") }) }) })
@NamedNativeQueries({
        @NamedNativeQuery(name = "popularWordsParty", query = "select t2.year, t2.month, t2.word, t.max as count from ("
                + "select tabl.year,tabl.month,max(count) as max from ("
                + "select year(t.posted_at) as year, month(t.posted_at) as month, twt.word, sum(twt.count) as count"
                + " from party par join politician p on par.id = p.party_id join tweet t on p.id = t.politician_id"
                + " join tweet_word_tweet twt on twt.tweet_id = t.id where par.url_name = ?1"
                + " and year(t.posted_at) >= 2014 group by year(t.posted_at), month(t.posted_at), twt.word "
                + ") as tabl "
                + "group by tabl.year, tabl.month) t "
                + "inner join ( "
                + "select year(t.posted_at) as year, month(t.posted_at) as month, twt.word, sum(twt.count) as count from party par join politician p on par.id = p.party_id join tweet t on p.id = t.politician_id join tweet_word_tweet twt on twt.tweet_id = t.id "
                + "where par.url_name = ?1 "
                + "and year(t.posted_at) >= 2014 "
                + "group by year(t.posted_at), month(t.posted_at), twt.word "
                + ") t2 on (t.year = t2.year and t.month = t2.month and t.max = t2.count) "
                + "group by year desc, month desc, max desc", resultSetMapping = "PopularWord"),

        @NamedNativeQuery(name = "tweetedWordsParty", query = "select word, sum(twt.count) as count "
                + "from tweet_word_tweet twt join tweet on twt.tweet_id = tweet.id "
                + " join politician p on p.id = tweet.politician_id join party pa on p.party_id = pa.id "
                + "where pa.url_name = ?1 group by word order by sum(twt.count) desc limit 100", resultSetMapping = "TweetedWord") })
// ignore hibernate lazy-loading properties
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "politicians" })
public class Party {

    private Long id;
    private String name;
    private String urlName;
    private Date createdAt;
    private Date updatedAt;
    private Integer displayOrder;
    private List<Politician> politicians;

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

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @OneToMany(mappedBy = "party")
    public List<Politician> getPoliticians() {
        return politicians;
    }

    public void setPoliticians(List<Politician> politicians) {
        this.politicians = politicians;
    }

}

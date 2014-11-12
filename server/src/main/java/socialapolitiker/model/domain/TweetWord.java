package socialapolitiker.model.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tweet_word")
// ignore hibernate lazy-loading properties
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class TweetWord {
	private TweetWordId id;
	private int count;

	@EmbeddedId
	public TweetWordId getId() {
		return id;
	}

	public void setId(TweetWordId id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

package socialapolitiker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import socialapolitiker.model.domain.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, String> {

}

package socialapolitiker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import socialapolitiker.model.domain.Politician;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;

public interface PoliticianRepository extends JpaRepository<Politician, Long> {
    @Query(name = "popularWordsPolitician")
    List<PopularWord> getPopularWords(String twitterScreenName);

    @Query(name = "tweetedWordsPolitician")
    List<TweetedWord> getTweetedWords(String twitterScreenName);

    List<Politician> findByPartyId(Long partyId);

    Politician findOneByTwitterScreenName(String twitterScreenName);
}

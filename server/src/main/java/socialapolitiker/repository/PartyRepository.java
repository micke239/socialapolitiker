package socialapolitiker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import socialapolitiker.model.domain.Party;
import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;

public interface PartyRepository extends JpaRepository<Party, Long> {
    @Query(name = "popularWordsParty")
    List<PopularWord> getPopularWords(String urlName);

    @Query(name = "tweetedWordsParty")
    List<TweetedWord> getTweetedWords(String urlName);

    Party findOneByUrlName(String urlName);
}

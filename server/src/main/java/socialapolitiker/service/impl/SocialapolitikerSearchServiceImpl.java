package socialapolitiker.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;
import socialapolitiker.service.SocialapolitikerSearchService;

@Service
public class SocialapolitikerSearchServiceImpl implements SocialapolitikerSearchService {

    private RestTemplate restTemplate;

    @Autowired
    public SocialapolitikerSearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TweetedWord> getTweetedWordsByPolitician(String politician) {
        TweetedWord[] tweetedWords = restTemplate.getForObject(
                "http://localhost:8081/tweeted-words/politician?politician=" + politician, TweetedWord[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<TweetedWord> getTweetedWordsByParty(String partyUrlName) {
        TweetedWord[] tweetedWords = restTemplate.getForObject(
                "http://localhost:8081/tweeted-words/party?partyUrlName=" + partyUrlName, TweetedWord[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByPolitician(String politician) {
        PopularWord[] popularWords = restTemplate.getForObject(
                "http://localhost:8081/popular-words/politician?politician=" + politician, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByParty(String partyUrlName) {
        PopularWord[] popularWords = restTemplate.getForObject(
                "http://localhost:8081/popular-words/party?partyUrlName=" + partyUrlName, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

}

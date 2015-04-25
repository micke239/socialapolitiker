package socialapolitiker.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import socialapolitiker.model.dto.PopularWord;
import socialapolitiker.model.dto.TweetedWord;
import socialapolitiker.service.SocialapolitikerSearchService;

@Service
public class SocialapolitikerSearchServiceImpl implements SocialapolitikerSearchService {

    private RestTemplate restTemplate;
    private String searchHost;

    @Autowired
    public SocialapolitikerSearchServiceImpl(RestTemplate restTemplate,
            @Value("${socialapolitiker.search}") String searchHost) {
        this.restTemplate = restTemplate;
        this.searchHost = searchHost;
    }

    @Override
    public List<TweetedWord> getTweetedWordsByPolitician(String politician) {
        TweetedWord[] tweetedWords = restTemplate.getForObject(searchHost + "/tweeted-words/politician?politician="
                + politician, TweetedWord[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<TweetedWord> getTweetedWordsByParty(String partyUrlName) {
        TweetedWord[] tweetedWords = restTemplate.getForObject(searchHost + "/tweeted-words/party?partyUrlName="
                + partyUrlName, TweetedWord[].class);

        return Arrays.asList(tweetedWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByPolitician(String politician) {
        PopularWord[] popularWords = restTemplate.getForObject(searchHost + "/popular-words/politician?politician="
                + politician, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

    @Override
    public List<PopularWord> getPopularWordsByParty(String partyUrlName) {
        PopularWord[] popularWords = restTemplate.getForObject(searchHost + "/popular-words/party?partyUrlName="
                + partyUrlName, PopularWord[].class);

        return Arrays.asList(popularWords);
    }

}

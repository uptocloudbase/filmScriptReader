package org.agd.ingest;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.amazonaws.services.comprehend.model.SentimentScore;
import org.agd.entity.FilmLine;
import org.agd.entity.MovieCharacter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CharacterAnalyzer {

    private AmazonComprehend comprehendClient;

    public CharacterAnalyzer() {
        comprehendClient = AmazonComprehendClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
    }

    public List<MovieCharacter> analyseCharacters(List<FilmLine> script, String movieId, String movieName) {

        List<MovieCharacter> characters = new ArrayList<>();

        getCharactersFromScript(script).forEach(name -> {
            MovieCharacter c = new MovieCharacter();
            c.setMovieID(movieId);
            c.setMovieName(movieName);
            c.setName(name);
            c.setNumLines(getNumberOfLines(name, script));

            SentimentScore sentimentAnalysis = getSentimentAnalysis(name, script);
            c.setMixedSentiment(sentimentAnalysis.getMixed());
            c.setPositiveSentiment(sentimentAnalysis.getPositive());
            c.setNegativeSentiment(sentimentAnalysis.getNegative());
            c.setNeutralSentiment(sentimentAnalysis.getNeutral());

            characters.add(c);

        });


        return characters;


    }

    private Set<String> getCharactersFromScript(List<FilmLine> script) {

        Set<String> characterNames = new LinkedHashSet<>();
        script.forEach(l -> characterNames.add(l.getCharacter()));

        return characterNames;
    }

    private int getNumberOfLines(String characterName, List<FilmLine> script) {
        return script.stream().filter(fl -> fl.getCharacter().equalsIgnoreCase(characterName)).collect(Collectors.toList()).size();
    }

    private SentimentScore getSentimentAnalysis(String characterName, List<FilmLine> script) {

        StringBuffer sb = new StringBuffer();
        script.stream().filter(fl -> fl.getCharacter().equalsIgnoreCase(characterName))
                .collect(Collectors.toList())
                .forEach(fl -> {
                    sb.append(fl.getDialogue() + " ");
                });

        //Max size for the API 5000 bytes
        String text = sb.toString();

        if(text.length() > 5000) {
            text = text.substring(0,4999);
        }

        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
                .withLanguageCode("en");
        DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);

        return detectSentimentResult.getSentimentScore();
    }
}

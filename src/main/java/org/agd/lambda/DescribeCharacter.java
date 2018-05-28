package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.agd.db.DynamoDBService;
import org.agd.entity.MovieCharacter;
import org.agd.lambda.req.DescribeCharacterRequest;
import org.agd.lambda.to.MovieAppearanceTO;
import org.agd.lambda.to.MovieCharacterTO;

import java.util.List;

public class DescribeCharacter implements RequestHandler<DescribeCharacterRequest, MovieCharacterTO> {
    @Override
    public MovieCharacterTO handleRequest(DescribeCharacterRequest describeCharacterRequest, Context context) {
        DynamoDBService dbService = new DynamoDBService();
        MovieCharacterTO mvto = new MovieCharacterTO();

        try {

            List<MovieCharacter> result = dbService.getCharacterDescription(describeCharacterRequest.getCharacterName());

            mvto.setName(describeCharacterRequest.getCharacterName());
            result.forEach(mc -> {
                MovieAppearanceTO appearanceTO = new MovieAppearanceTO();

                appearanceTO.setMovieID(mc.getMovieID());
                appearanceTO.setMovieName(mc.getMovieName());
                appearanceTO.setNumLines(mc.getNumLines());
                appearanceTO.setMixedSentiment(mc.getMixedSentiment());
                appearanceTO.setNegativeSentiment(mc.getNegativeSentiment());
                appearanceTO.setPositiveSentiment(mc.getPositiveSentiment());
                appearanceTO.setNeutralSentiment(mc.getNeutralSentiment());

                if (mc.getPositiveSentiment() > 0.2 || mc.getNegativeSentiment() > 0.2) {

                    if (mc.getPositiveSentiment() > mc.getNegativeSentiment()) {
                        appearanceTO.setDsPrediction("JEDI");
                    } else {
                        appearanceTO.setDsPrediction("DARK SIDE");
                    }
                } else {
                    appearanceTO.setDsPrediction("UNCERTAIN");
                }

                mvto.getAppearances().add(appearanceTO);
            });

        } finally {
            dbService.shutdown();
        }
        return mvto;
    }
}

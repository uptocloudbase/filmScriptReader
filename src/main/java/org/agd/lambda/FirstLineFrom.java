package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.agd.db.DynamoDBService;
import org.agd.entity.FilmLine;
import org.agd.lambda.req.FirstLineRequest;

public class FirstLineFrom implements RequestHandler<FirstLineRequest, FilmLine> {


    @Override
    public FilmLine handleRequest(FirstLineRequest firstLineRequest, Context context) {

        DynamoDBService dbService = new DynamoDBService();
        FilmLine result = null;
        try {

            result = dbService.getFirstLineFrom(firstLineRequest.getCharacterName(), firstLineRequest.getMovieId());
        } finally {
            dbService.shutdown();
        }

        return result;
    }
}

package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.agd.db.DynamoDBService;
import org.agd.entity.FilmLine;
import org.agd.lambda.req.FirstLineRequest;
import org.agd.lambda.req.GetLinesRequest;

import java.util.List;

public class GetLines implements RequestHandler<GetLinesRequest, List<FilmLine>> {

    @Override
    public List<FilmLine> handleRequest(GetLinesRequest getLinesRequest, Context context) {

        DynamoDBService dbService = new DynamoDBService();
        List<FilmLine> result = null;

        try {

            result = dbService.getLinesFromMovie(getLinesRequest.getMovieId(), getLinesRequest.getStartLine(), getLinesRequest.getNumLines());


        } finally {
            dbService.shutdown();
        }
        return result;
    }
}

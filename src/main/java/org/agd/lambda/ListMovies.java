package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.agd.db.DynamoDBService;
import org.agd.entity.Movie;

import java.util.List;
import java.util.Map;

public class ListMovies implements RequestHandler<Map<String,Object>, List<Movie>> {


    @Override
    public List<Movie> handleRequest(Map<String, Object> stringObjectMap, Context context) {

        DynamoDBService dbService = new DynamoDBService();
        List<Movie> allMovies = null;

        try {
            allMovies = dbService.listMovies();
        } finally {
            dbService.shutdown();
        }

        return allMovies;

    }
}

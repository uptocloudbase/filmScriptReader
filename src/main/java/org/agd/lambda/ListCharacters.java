package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.agd.db.DynamoDBService;
import org.agd.lambda.req.ListCharactersRequest;

import java.util.List;

public class ListCharacters implements RequestHandler<ListCharactersRequest, List<String>> {

    @Override
    public List<String> handleRequest(ListCharactersRequest listCharactersRequest, Context context) {
        DynamoDBService dbService = new DynamoDBService();
        List<String> result = null;

        try {

            result = dbService.listCharacters(listCharactersRequest.getMovieId());

        } finally {
            dbService.shutdown();
        }

        return result;
    }
}

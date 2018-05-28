package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.agd.entity.FilmLine;
import org.agd.lambda.req.GetLinesRequest;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class GetLinesTest {

    @Test
    public void handleRequest() {

        GetLines target = new GetLines();
        GetLinesRequest glRq = new GetLinesRequest();
        glRq.setMovieId("e017d89b-3c08-4472-9336-d90d5059033d");
        glRq.setStartLine(10);
        glRq.setNumLInes(10);
        Context context = Mockito.mock(Context.class);
        List<FilmLine> result = target.handleRequest(glRq, context);

        assertEquals(11, result.size());
    }
}
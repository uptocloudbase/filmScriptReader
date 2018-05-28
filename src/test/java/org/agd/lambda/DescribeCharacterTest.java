package org.agd.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.agd.lambda.req.DescribeCharacterRequest;
import org.agd.lambda.to.MovieAppearanceTO;
import org.agd.lambda.to.MovieCharacterTO;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DescribeCharacterTest {

    @Test
    @Ignore
    public void handleRequest() {

        DescribeCharacter describeCharacter = new DescribeCharacter();

        Context context = Mockito.mock(Context.class);

        DescribeCharacterRequest req = new DescribeCharacterRequest();
        req.setCharacterName("JABBA");
        MovieCharacterTO result = describeCharacter.handleRequest(req, context);

        System.out.println(result.getName());

        for (MovieAppearanceTO appearanceTO : result.getAppearances()) {

            System.out.println(appearanceTO.getMovieName());
            System.out.println(appearanceTO.getDsPrediction());

        }
    }
}
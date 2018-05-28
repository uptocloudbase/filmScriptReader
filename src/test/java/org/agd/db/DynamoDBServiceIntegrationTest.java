package org.agd.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import org.agd.entity.FilmLine;
import org.agd.entity.Movie;
import org.agd.entity.MovieCharacter;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DynamoDBServiceIntegrationTest {

    @Test
    public void listMovies() {

        List<Movie> result = new DynamoDBService().listMovies();

        result.forEach(m -> {
            System.out.println(m.getId() + " : " + m.getName());
        });

        assertEquals(3, result.size());
    }

    @Test
    public void getFirstLineFrom() {

        DynamoDBService dbService = new DynamoDBService();

        FilmLine result = dbService.getFirstLineFrom("LUKE", "e017d89b-3c08-4472-9336-d90d5059033d");

        assertEquals("LUKE", result.getCharacter());
        assertEquals(6, result.getLineNumber());
        assertEquals("Hurry up!  Come with me!  What are you waiting for?!  Get in gear!", result.getDialogue());

        result = dbService.getFirstLineFrom("HAN", "e017d89b-3c08-4472-9336-d90d5059033d");

        assertEquals("HAN", result.getCharacter());
        assertEquals(342, result.getLineNumber());
        assertEquals("Han Solo.  I'm captain of the Millennium Falcon.  Chewie here tells me you're looking for passage to the Alderaan system.", result.getDialogue());

        dbService.shutdown();
    }

    @Test
    public void getLinesFromMovie() {

        DynamoDBService dbService = new DynamoDBService();

        List<FilmLine> result = dbService.getLinesFromMovie("e017d89b-3c08-4472-9336-d90d5059033d", 25, 10);

        assertEquals(11, result.size());
        assertEquals(25, result.get(0).getLineNumber());
        assertEquals(35, result.get(10).getLineNumber());

        dbService.shutdown();
    }

    @Test
    public void getCharacterDescription() {
        DynamoDBService dbService = new DynamoDBService();
        List<MovieCharacter> result = dbService.getCharacterDescription("HAN");

        assertEquals(3, result.size());

        dbService.shutdown();
    }

    @Test
    public void listCharacters() {

        String movieID = "e017d89b-3c08-4472-9336-d90d5059033d";
        DynamoDBService dbService = new DynamoDBService();

        List<String> characters = dbService.listCharacters(movieID);

        assertEquals(60, characters.size());
        assertTrue(characters.contains("LUKE"));
        assertTrue(characters.contains("HAN"));
        assertTrue(characters.contains("LEIA"));

        dbService.shutdown();

    }
}
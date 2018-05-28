package org.agd.ingest;

import org.agd.entity.FilmLine;
import org.agd.entity.MovieCharacter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CharacterAnalyzerTest {

    @Test
    public void analyseCharacters() {

        List<FilmLine> filmLines = new ArrayList<>();
        filmLines.add(new FilmLine(1,"DAVE", "That's really bad", "mov1"));
        filmLines.add(new FilmLine(1,"MIKE", "I think it's great", "mov1"));
        filmLines.add(new FilmLine(1,"DAVE", "No really it's awful", "mov1"));
        filmLines.add(new FilmLine(1,"DAVE", "So bad I can't tell you how dreadful", "mov1"));
        filmLines.add(new FilmLine(1,"LUCY", "I agree dreadful", "mov1"));
        filmLines.add(new FilmLine(1,"DAVE", "yeah really bad", "mov1"));
        filmLines.add(new FilmLine(1,"MIKE", "No, it's amazing!!", "mov1"));
        filmLines.add(new FilmLine(1,"DAVE", "Urghh", "mov1"));
        filmLines.add(new FilmLine(1,"ANDREA", "Will you all stop bickering", "mov1"));

        List<MovieCharacter> result = new CharacterAnalyzer().analyseCharacters(filmLines, "abc123", "Mary Popins");

        assertEquals(4, result.size());

        MovieCharacter dave = result.get(0);
        assertEquals("DAVE", dave.getName());
        assertEquals("abc123", dave.getMovieID());
        assertEquals("Mary Popins", dave.getMovieName());
        assertEquals(5, dave.getNumLines());
        assertEquals(0.0, dave.getMixedSentiment(), 0.1);
        assertEquals(0.9031502604484558, dave.getNegativeSentiment(), 0.1);
        assertEquals(0.0, dave.getPositiveSentiment(), 0.1);
        assertEquals(0.0, dave.getNeutralSentiment(), 0.1);

        MovieCharacter mike = result.get(1);
        assertEquals("MIKE", mike.getName());
        assertEquals("abc123", mike.getMovieID());
        assertEquals("Mary Popins", mike.getMovieName());
        assertEquals(2, mike.getNumLines());

        MovieCharacter lucy = result.get(2);
        assertEquals("LUCY", lucy.getName());
        assertEquals("abc123", lucy.getMovieID());
        assertEquals("Mary Popins", lucy.getMovieName());
        assertEquals(1, lucy.getNumLines());

        MovieCharacter andrea = result.get(3);
        assertEquals("ANDREA", andrea.getName());
        assertEquals("abc123", andrea.getMovieID());
        assertEquals("Mary Popins", andrea.getMovieName());
        assertEquals(1, andrea.getNumLines());

    }
}
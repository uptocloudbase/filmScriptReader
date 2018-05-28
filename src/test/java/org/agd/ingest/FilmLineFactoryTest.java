package org.agd.ingest;

import org.agd.entity.FilmLine;
import org.agd.exceptions.NotAFilmLineException;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilmLineFactoryTest {

    @Test(expected = NotAFilmLineException.class)
    public void headerLineParse() throws NotAFilmLineException {

        String input = "\"character\" \"dialogue\"";
        FilmLineFactory.createFilmLine(input, "");


    }

    @Test
    public void correctDialogueTest() throws NotAFilmLineException {

        String input = "\"1\" \"THREEPIO\" \"Did you hear that?  They've shut down the main reactor.  We'll be destroyed for sure.  This is madness!\"";
        FilmLine result = FilmLineFactory.createFilmLine(input, "an id");

        assertEquals(1, result.getLineNumber());
        assertEquals("THREEPIO", result.getCharacter());
        assertEquals("Did you hear that?  They've shut down the main reactor.  We'll be destroyed for sure.  This is madness!", result.getDialogue());
        assertEquals("an id", result.getMovieID());
    }

}
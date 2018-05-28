package org.agd.ingest;

import org.agd.entity.FilmLine;
import org.agd.exceptions.NotAFilmLineException;

public class FilmLineFactory {

    public static FilmLine createFilmLine(String input, String movieID) throws NotAFilmLineException {

        String[] inputs = input.split("\" \"");
        if (inputs.length !=3) {
            throw new NotAFilmLineException();
        }

        int lineNumber = Integer.parseInt(inputs[0].replaceAll("\"", ""));
        String character = inputs[1].replaceAll("\"", "");
        String dialogue = inputs[2].replaceAll("\"", "");

        return new FilmLine(lineNumber, character, dialogue, movieID);

    }
}

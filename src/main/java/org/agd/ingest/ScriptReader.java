package org.agd.ingest;

import org.agd.entity.FilmLine;
import org.agd.exceptions.NotAFilmLineException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScriptReader {


    public List<FilmLine> readScript(String location, String movieId) throws IOException {

        List<FilmLine> filmLines = new ArrayList<>();


        URL url = new URL(location);

        try (InputStream is = url.openStream()) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        FilmLine fl = FilmLineFactory.createFilmLine(line, movieId);
                        filmLines.add(fl);
                    } catch (NotAFilmLineException e) {
                        //Ignore headers and malformed lines for now
                        //TODO: Handle malformed lines
                    }
                }
            }

        }

        return filmLines;
    }
}

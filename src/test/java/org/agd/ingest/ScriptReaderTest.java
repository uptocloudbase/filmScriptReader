package org.agd.ingest;

import org.agd.entity.FilmLine;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ScriptReaderTest {

    @Test
    public void handleRequestTest() throws IOException {

        String location = "https://raw.githubusercontent.com/gastonstat/StarWars/master/Text_files/SW_EpisodeIV.txt";

        ScriptReader target = new ScriptReader();

        List<FilmLine> result = target.readScript(location, "an id");

        assertEquals(1010, result.size());

    }

    @Test(expected = IOException.class)
    public void handleBadUrl() throws IOException {

        String location = "rubbish.txt";

        ScriptReader target = new ScriptReader();

        List<FilmLine> result = target.readScript(location, "an id");


    }
}
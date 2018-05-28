package org.agd.ingest;

import org.agd.db.DynamoDBService;
import org.agd.entity.FilmLine;
import org.agd.entity.MovieCharacter;

import java.util.List;

public class ScriptProcessorMain {

    private final String location;
    private final String movieName;
    private final ScriptReader scriptReader = new ScriptReader();
    private final DynamoDBService dynamoDBService = new DynamoDBService();
    private final CharacterAnalyzer characterAnalyzer = new CharacterAnalyzer();

    public ScriptProcessorMain(String location, String movieName) {
        this.location = location;
        this.movieName = movieName;
    }

    private void processScript() throws Exception {

        try {

            //Write movie Entry to DB
            String movieID = dynamoDBService.createMovieEntry(movieName);

            //Read file
            List<FilmLine> script = scriptReader.readScript(location, movieID);

            //Validate file
            if (script.size() < 100) {
                throw new Exception("We expect scripts to longer than 100 lines, it appears this script is corrupt");
            }

            //Write Dialogue to DB
            dynamoDBService.writeDialogue(script);

            //Get characters, including sentiment
            List<MovieCharacter> characters = characterAnalyzer.analyseCharacters(script, movieID, movieName);

            //Write Characters to DB
            dynamoDBService.writeCharacters(characters);


        } finally {
            //Close down resources
            dynamoDBService.shutdown();
        }


    }

    public static void main(String[] args) {

        if (args.length !=2) {
            System.out.println("Usage: <file location url> <film name>");
            System.exit(-1);
        }

        String location = args[0];
        String movieName = args[1];

        ScriptProcessorMain scriptProcessor = new ScriptProcessorMain(location, movieName);

        try {
            scriptProcessor.processScript();
        } catch (Exception e) {
            System.out.println("This script could not be processed. " + e.getMessage());
            e.printStackTrace();
        }

    }
}

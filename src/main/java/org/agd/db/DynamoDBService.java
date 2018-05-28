package org.agd.db;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import org.agd.entity.FilmLine;
import org.agd.entity.Movie;
import org.agd.entity.MovieCharacter;
import org.agd.exceptions.DBException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DynamoDBService {

    private final AmazonDynamoDB dbClient;
    private final DynamoDB dynamoDB;
    private final DynamoDBMapper mapper;
    public static final String MOVIE_TABLE = "Movies";
    public static final String DIALOGUE_TABLE = "Dialogue";
    public static final String CHARACTER_TABLE = "Characters";

    public DynamoDBService() {
        dbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2)
                .build();
        dynamoDB = new DynamoDB(dbClient);
        mapper = new DynamoDBMapper(dbClient);

    }

    public void writeDialogue(List<FilmLine> filmLines) throws DBException {


        mapper.batchSave(filmLines);

    }

    public String createMovieEntry(String movieName) throws DBException {

        Table movieTable = dynamoDB.getTable(MOVIE_TABLE);

        ItemCollection<ScanOutcome> result = movieTable.scan(new ScanSpec().withScanFilters(new ScanFilter("Name").eq(movieName)));

        if (result.firstPage().getLowLevelResult().getItems().size() > 0) {
            throw new DBException("Movie already exists - please delete old data before re-importing.");
        }


        Object uuid = UUID.randomUUID();
        Item item = new Item()
                .withPrimaryKey("ID", uuid.toString())
                .withString("MovieName", movieName);
        movieTable.putItem(item);

        return uuid.toString();

    }

    public void writeCharacters(List<MovieCharacter> movieCharacters) throws DBException {

        mapper.batchSave(movieCharacters);
    }

    //Reader methods

    public List<Movie> listMovies() {

        PaginatedScanList<Movie> movies = mapper.scan(Movie.class, new DynamoDBScanExpression());
        return movies.stream().collect(Collectors.toList());

    }

    public FilmLine getFirstLineFrom(String character, String movieID) {

        Map<String, AttributeValue> expressionAttributeValues =
                new HashMap<>();

        expressionAttributeValues.put(":characterName", new AttributeValue().withS(character));

        FilmLine fl = new FilmLine();
        fl.setMovieID(movieID);

        PaginatedQueryList<FilmLine> result = mapper.query(FilmLine.class,
                new DynamoDBQueryExpression()
                        .withHashKeyValues(fl)
                        .withFilterExpression("characterName = :characterName")
                        .withExpressionAttributeValues(expressionAttributeValues));

        return result.get(0);


    }

    public List<FilmLine> getLinesFromMovie(String movieID, int startLine, int numLines) {
        Map<String, AttributeValue> expressionAttributeValues =
                new HashMap<>();

        expressionAttributeValues.put(":start", new AttributeValue().withN("" + startLine));
        expressionAttributeValues.put(":end", new AttributeValue().withN("" + (startLine + numLines)));
        expressionAttributeValues.put(":movieId", new AttributeValue().withS(movieID));

        PaginatedQueryList<FilmLine> result = mapper.query(FilmLine.class,
                new DynamoDBQueryExpression()
                        .withKeyConditionExpression("MovieID = :movieId and LineNumber between :start and :end")
                        .withExpressionAttributeValues(expressionAttributeValues));

        return result;
    }

    public List<MovieCharacter> getCharacterDescription(String characterName) {

        MovieCharacter mc = new MovieCharacter();
        mc.setName(characterName);

        PaginatedQueryList<MovieCharacter> result = mapper.query(MovieCharacter.class,
                new DynamoDBQueryExpression()
                .withHashKeyValues(mc));

        return result.stream().collect(Collectors.toList());
    }

    public List<String> listCharacters(String movieID) {


        Map<String, AttributeValue> expressionAttributeValues =
                new HashMap<>();

        expressionAttributeValues.put(":movieId", new AttributeValue().withS(movieID));



        PaginatedScanList<MovieCharacter> result = mapper.scan(MovieCharacter.class,
                new DynamoDBScanExpression()
                        .withFilterExpression("MovieID = :movieId")
                        .withExpressionAttributeValues(expressionAttributeValues));

        List<String> characterNames = result.stream().map(mc -> mc.getName()).collect(Collectors.toList());

        return characterNames;
    }

    public void shutdown() {
        dynamoDB.shutdown();
        dbClient.shutdown();
    }

}

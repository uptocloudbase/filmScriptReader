package org.agd.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.agd.db.DynamoDBService;

import java.util.List;

@DynamoDBTable(tableName = DynamoDBService.CHARACTER_TABLE)
public class MovieCharacter {

    private String name;
    private String movieID;
    private String movieName;
    private int numLines;
    private double neutralSentiment;
    private double positiveSentiment;
    private double negativeSentiment;
    private double mixedSentiment;


    @DynamoDBHashKey(attributeName = "CharacterName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "MovieID")
    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    @DynamoDBAttribute(attributeName = "numLines")
    public int getNumLines() {
        return numLines;
    }

    public void setNumLines(int numLines) {
        this.numLines = numLines;
    }

    @DynamoDBAttribute(attributeName = "neutralSentiment")
    public double getNeutralSentiment() {
        return neutralSentiment;
    }

    public void setNeutralSentiment(double neutralSentiment) {
        this.neutralSentiment = neutralSentiment;
    }

    @DynamoDBAttribute(attributeName = "positiveSentiment")
    public double getPositiveSentiment() {
        return positiveSentiment;
    }

    public void setPositiveSentiment(double positiveSentiment) {
        this.positiveSentiment = positiveSentiment;
    }

    @DynamoDBAttribute(attributeName = "negativeSentiment")
    public double getNegativeSentiment() {
        return negativeSentiment;
    }

    public void setNegativeSentiment(double negativeSentiment) {
        this.negativeSentiment = negativeSentiment;
    }

    @DynamoDBAttribute(attributeName = "mixedSentiment")
    public double getMixedSentiment() {
        return mixedSentiment;
    }

    public void setMixedSentiment(double mixedSentiment) {
        this.mixedSentiment = mixedSentiment;
    }

    @DynamoDBAttribute(attributeName = "movieName")
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

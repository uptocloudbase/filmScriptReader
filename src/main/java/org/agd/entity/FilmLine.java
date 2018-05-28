package org.agd.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.agd.db.DynamoDBService;

import java.util.UUID;

@DynamoDBTable(tableName = DynamoDBService.DIALOGUE_TABLE)
public class FilmLine {

    private int lineNumber;
    private String character;
    private String dialogue;
    private String movieID;

    public FilmLine() {}

    public FilmLine(int lineNumber, String character, String dialogue, String movieID) {
        this.lineNumber = lineNumber;
        this.character = character;
        this.dialogue = dialogue;
        this.movieID = movieID;
    }

    @DynamoDBHashKey(attributeName = "MovieID")
    public String getMovieID() {
        return movieID;
    }

    @DynamoDBRangeKey(attributeName = "LineNumber")
    public int getLineNumber() {
        return lineNumber;
    }

    @DynamoDBAttribute(attributeName = "characterName")
    public String getCharacter() {
        return character;
    }

    @DynamoDBAttribute(attributeName = "dialogue")
    public String getDialogue() {
        return dialogue;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}

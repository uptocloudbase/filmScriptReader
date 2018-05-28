package org.agd.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.agd.db.DynamoDBService;

@DynamoDBTable(tableName = DynamoDBService.MOVIE_TABLE)
public class Movie {

    private String id;
    private String name;

    @DynamoDBHashKey(attributeName = "ID")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "MovieName")
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

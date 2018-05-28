package org.agd.lambda.req;

public class FirstLineRequest {

    private String characterName;
    private String movieId;

    public String getCharacterName() {
        return characterName == null ? null : characterName.toUpperCase();
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}

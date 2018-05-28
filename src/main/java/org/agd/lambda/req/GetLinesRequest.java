package org.agd.lambda.req;

public class GetLinesRequest {

    private String movieId;
    private int startLine;
    private int numLInes;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getNumLInes() {
        return numLInes;
    }

    public void setNumLInes(int numLInes) {
        this.numLInes = numLInes;
    }
}

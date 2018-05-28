package org.agd.lambda.to;

public class MovieAppearanceTO {

    private String movieID;
    private String movieName;
    private int numLines;
    private double neutralSentiment;
    private double positiveSentiment;
    private double negativeSentiment;
    private double mixedSentiment;
    private String dsPrediction;

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getNumLines() {
        return numLines;
    }

    public void setNumLines(int numLines) {
        this.numLines = numLines;
    }

    public double getNeutralSentiment() {
        return neutralSentiment;
    }

    public void setNeutralSentiment(double neutralSentiment) {
        this.neutralSentiment = neutralSentiment;
    }

    public double getPositiveSentiment() {
        return positiveSentiment;
    }

    public void setPositiveSentiment(double positiveSentiment) {
        this.positiveSentiment = positiveSentiment;
    }

    public double getNegativeSentiment() {
        return negativeSentiment;
    }

    public void setNegativeSentiment(double negativeSentiment) {
        this.negativeSentiment = negativeSentiment;
    }

    public double getMixedSentiment() {
        return mixedSentiment;
    }

    public void setMixedSentiment(double mixedSentiment) {
        this.mixedSentiment = mixedSentiment;
    }

    public String getDsPrediction() {
        return dsPrediction;
    }

    public void setDsPrediction(String dsPrediction) {
        this.dsPrediction = dsPrediction;
    }
}

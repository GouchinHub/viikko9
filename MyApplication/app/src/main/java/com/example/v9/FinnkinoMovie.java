package com.example.v9;

public class FinnkinoMovie {

    private String movieID = "";
    private String movieTitle = "";
    private String movieStart = "";

    public FinnkinoMovie(String id, String title, String startTime) {
        movieID = id;
        movieTitle = title;
        movieStart = startTime;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getStartTime() {
        return movieStart;
    }

    @Override
    public String toString() {
        return movieTitle;
    }
}

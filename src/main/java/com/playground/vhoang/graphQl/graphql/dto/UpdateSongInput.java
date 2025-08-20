package com.playground.vhoang.graphQl.graphql.dto;

public class UpdateSongInput {
    private String title;

    public UpdateSongInput() {}

    public UpdateSongInput(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

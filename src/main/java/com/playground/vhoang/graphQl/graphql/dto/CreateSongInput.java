package com.playground.vhoang.graphQl.graphql.dto;

public class CreateSongInput {

    private String title;

    public CreateSongInput() {}

    public CreateSongInput(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

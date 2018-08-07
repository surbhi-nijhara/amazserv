package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionResponse {
	 @JsonProperty("title")
	    private String title;
    @JsonProperty("description")
    private String description;

    public QuestionResponse(boolean success, String message) {
        this.title = title;
        this.description = description;
    }
}
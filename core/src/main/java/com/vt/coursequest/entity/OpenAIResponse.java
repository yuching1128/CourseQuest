package com.vt.coursequest.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OpenAIResponse {
    private String id;
    private String object;
    private String model;
    private LocalDate created;
    private List<Choice> choices;
}


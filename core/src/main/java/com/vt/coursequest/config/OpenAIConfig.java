package com.vt.coursequest.config;

public class OpenAIConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "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";
    public static final String MODEL = "davinci:ft-personal-2023-04-28-16-41-47";
    public static final Integer MAX_TOKEN = 30;
    public static final Double TEMPERATURE = 0.2;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/completions";
}

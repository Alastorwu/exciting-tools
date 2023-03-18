package com.exciting.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ChatGPTClient {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
//    private static final String API_ENDPOINT = "https://api.openai.com//v1/chat/completions";
    private static final String API_KEY = "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";
    private static final String AUTH_HEADER = "Bearer " + API_KEY;
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient httpClient = new OkHttpClient();

    public String generateResponse(String prompt) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, createJsonRequestBody(prompt));
        Request request = createHttpRequest(requestBody);

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    return parseResponseBody(responseBody.string());
                } else {
                    throw new IOException("Unexpected empty response body");
                }
            } else {
                throw new IOException("Unexpected response code " + response.code());
            }
        }
    }

    private String createJsonRequestBody(String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(prompt);
        return JSON.toJSONString(request);
    }

    private Request createHttpRequest(RequestBody requestBody) {
        return new Request.Builder()
                .url(API_ENDPOINT)
                .header("Authorization", AUTH_HEADER)
                .post(requestBody)
                .build();
    }

    private String parseResponseBody(String responseBody) throws IOException {
        try {
            ChatGPTResponse response = JSON.parseObject(responseBody, ChatGPTResponse.class);
            return response.choices[0].text;
        } catch (JSONException e) {
            throw new IOException("Unable to parse response body: " + e.getMessage());
        }
    }

    private static class ChatGPTRequest {
        private final String prompt;
        private final int maxTokens = 50;
        private final int temperature = 0;
        private final int topP = 1;
        private final int n = 1;
        private final boolean stop = true;

        public ChatGPTRequest(String prompt) {
            this.prompt = prompt;
        }
    }

    private static class ChatGPTResponse {
        private ChatGPTChoice[] choices;

        private static class ChatGPTChoice {
            private final String text;
            private final float logprobs;
            private final float finishReason;

            public ChatGPTChoice(String text, float logprobs, float finishReason) {
                this.text = text;
                this.logprobs = logprobs;
                this.finishReason = finishReason;
            }
        }
    }
}

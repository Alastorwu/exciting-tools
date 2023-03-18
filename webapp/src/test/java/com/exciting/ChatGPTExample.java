package com.exciting;


import java.io.IOException;

import com.exciting.common.util.ChatGPTClient;

public class ChatGPTExample {


    public static void main(String[] args) throws IOException {
        ChatGPTClient chatGPTClient = new ChatGPTClient();
        String hello = chatGPTClient.generateResponse("hello");
        System.out.println(hello);
    }

}
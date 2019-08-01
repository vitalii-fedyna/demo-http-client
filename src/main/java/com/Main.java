package com;

import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("URL and request JSON must be specified");
        }
        final String url = args[0];
        final String requestJSON = args[1];

        final HttpClient client = new HttpClient(url, HttpClients.createDefault());

        // First run
        client.doPost(requestJSON);

        //Second run
        client.doPost(requestJSON);

    }

}

package com;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.Constants.CONTENT_TYPE_HEADER_HAME;
import static com.Constants.JSON_TYPE_HEADER_VALUE;

public class HttpClient {

    private CloseableHttpClient client;

    private String url;

    public HttpClient(String url, CloseableHttpClient client) {
        this.client = client;
        this.url = url;
    }

    public void doPost(final String request) throws IOException {

        final HttpPost post = createPostRequest(request);

        final CloseableHttpResponse response;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }

        System.out.println(response);
    }

    HttpPost createPostRequest(final String request) throws UnsupportedEncodingException {
        final HttpPost post = new HttpPost(url);

        post.setEntity(new StringEntity(request));
        post.setHeader(CONTENT_TYPE_HEADER_HAME, JSON_TYPE_HEADER_VALUE);

        return post;
    }
}

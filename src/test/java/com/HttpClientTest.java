package com;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class HttpClientTest {

    private HttpClient httpClient;

    @Before
    public void initialize() {
        final CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);
        httpClient = Mockito.spy(new HttpClient("testURL", closeableHttpClient));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHttpClientWithNullRequest() throws IOException {
        httpClient.doPost(null);
    }


    @Test(expected = IOException.class)
    public void testErrorAfterRequestSending() throws IOException {

        Mockito.doThrow(new IOException("test")).when(httpClient).doPost(Mockito.anyString());

        httpClient.doPost("test");

        Mockito.verify(httpClient, Mockito.times(1)).createPostRequest("testRequest");
    }

    @Test
    public void testHttpPostCreation() throws IOException {
        HttpPost request = httpClient.createPostRequest("request");
        BasicHeader expectedHeader = new BasicHeader(Constants.CONTENT_TYPE_HEADER_HAME, Constants.JSON_TYPE_HEADER_VALUE);
        StringEntity expectedEntity = new StringEntity("request");

        //Headers checking
        Header[] actualHeaders = request.getAllHeaders();

        Assert.assertNotNull(actualHeaders);
        Assert.assertEquals(actualHeaders.length, 1);
        Assert.assertEquals(actualHeaders[0].getName(), expectedHeader.getName());
        Assert.assertEquals(actualHeaders[0].getValue(), expectedHeader.getValue());

        //Entity checking
        Assert.assertNotNull(request.getEntity());
        Assert.assertEquals(request.getEntity().getContentLength(), expectedEntity.getContentLength());

    }

    @Test
    public void testHttpRequestSending() throws IOException {
        httpClient.doPost("testRequest");

        Mockito.verify(httpClient, Mockito.times(1)).createPostRequest("testRequest");
    }

}

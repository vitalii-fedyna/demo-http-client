package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    private HttpClient client;

    @Before
    public void initialize()
    {
        client = Mockito.mock(HttpClient.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNullParameters() throws IOException {
        Main.main(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithWrongParametersSize() throws IOException {
        Main.main(new String[]{"test"});
    }
}

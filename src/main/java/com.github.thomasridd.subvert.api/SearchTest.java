package com.github.thomasridd.subvert.api;

import com.github.davidcarboni.httpino.Endpoint;
import com.github.davidcarboni.httpino.Host;
import com.github.davidcarboni.httpino.Http;
import com.github.davidcarboni.httpino.Response;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void search_shouldDownload_something() throws IOException {
        HttpGet get = new HttpGet("http://beta.ons.gov.uk/search/data?q=labour");


        // Post to the endpoint
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(get)) {


            //
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {

                        String theString = IOUtils.toString(inputStream, "UTF8");
                    System.out.println(theString);
                }
            } else {
                EntityUtils.consume(entity);
            }
        }
    }
}
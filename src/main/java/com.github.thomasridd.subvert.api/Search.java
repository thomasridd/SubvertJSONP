package com.github.thomasridd.subvert.api;

import com.github.davidcarboni.httpino.Endpoint;
import com.github.davidcarboni.httpino.Host;
import com.github.davidcarboni.httpino.Http;
import com.github.davidcarboni.httpino.Response;
import com.github.davidcarboni.restolino.framework.Api;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Created by Tom.Ridd on 20/08/15.
 */
@Api
public class Search {

    @GET
    public String getSearchResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Post to the endpoint
        String query = request.getParameter("q");
        HttpGet get = new HttpGet("http://beta.ons.gov.uk/search/data?q=" + query);

        String result = "";
        try (CloseableHttpResponse response2 = HttpClients.createDefault().execute(get)) {

            HttpEntity entity = response2.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {

                    result = IOUtils.toString(inputStream, "UTF8");
                }
            } else {
                EntityUtils.consume(entity);
            }
        }

        return "json=(" + result + ")";
    }
}

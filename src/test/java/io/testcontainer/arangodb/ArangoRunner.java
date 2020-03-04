package io.testcontainer.arangodb;

import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description in progress
 *
 * @author Anton Kurako (GoodforGod)
 * @since 4.3.2020
 */
public abstract class ArangoRunner extends Assertions {

    protected URL getCheckUrl(ArangoContainer container) throws Exception {
        return new URL("http://" + container.getHost() + ":" + container.getPort() + "/_api/collection");
    }

    protected String getResponse(HttpURLConnection connection) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
        } catch (Exception e) {}
        return builder.toString();
    }
}

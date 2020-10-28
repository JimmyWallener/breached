package se.breached.api;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import se.breached.model.ApiModel;

public class ApiConnection {
    private final String apiKey = ApiModel.getApiKey();

    public String ApiResponse(String url){
            /*
            * Creates a Https request with headers.
            * statusCode 200 builds a String response
            */
        try {
            URL uri = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "breached-app");
            connection.setRequestProperty("hibp-api-key", apiKey);
            connection.connect();
            int statusCode = connection.getResponseCode();

            if(statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
                }
                reader.close();
                return sb.toString();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}

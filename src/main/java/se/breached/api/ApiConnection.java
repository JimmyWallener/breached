package se.breached.api;
import se.breached.model.ApiModel;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ApiConnection {
    private final String apiKey = ApiModel.getApiKey();

    public String ApiResponse(String url){
            /*
            * Creates a Https request with headers, otherwise the request will return 401: Unauthorized.
            * For now responseCode returns String messages but can be expanded on.
            * 200 builds a String response, which will have to be converted to Json
            */
        try {
            URL uri = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "breached-app");
            connection.setRequestProperty("hibp-api-key", apiKey);
            connection.connect();
            int statusCode = connection.getResponseCode();


            switch (statusCode){

                case 200:   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            String line;

                            while ((line = reader.readLine()) != null) {
                            sb.append(line).append("\n");
                            }
                            reader.close();
                            return sb.toString();
                case 400:   return "Bad Request: " + statusCode;
                case 401:   return "Unauthorized: " + statusCode;
                case 403:   return "Forbidden Search: " + statusCode;
                case 404:   return "Search not Found: " + statusCode;
                case 429:   return "Too Many Requests: " + statusCode;
                case 503:   return "Service unavailable: " + statusCode;
                default:    return "Unknown Error: " + statusCode;

            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}

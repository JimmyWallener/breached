package se.breached.api;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import se.breached.model.ApiModel;

public class ApiConnection {

    private static String errorMsg;
    private final String apiKey = ApiModel.getApiKey();

    public String ApiResponse(String url){

        final String BAD_REQUEST = "Bad request:\nThe account does not comply with an acceptable format (i.e. it's an empty string)";
        final String UNAUTHORISED = "Unauthorised:\nEither no API key was provided or it wasn't valid";
        final String FORBIDDEN = "Forbidden:\nNo user agent has been specified in the request";
        final String NOT_FOUND = "Not found:\nThe account could not be found and has therefore not been pwned";
        final String EXCEEDED_REQUESTS = "Too many requests:\nThe rate limit has been exceeded";
        final String NO_SERVICE = "Service unavailable";
        final String DEFAULT = "Unexpected Error: Undefined Error";
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
            int responseCode = connection.getResponseCode();

            switch(responseCode) {
                case 200:   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            String line;

                            while ((line = reader.readLine()) != null) {
                            sb.append(line).append("\n");
                            }
                            reader.close();
                            return sb.toString();
                case 400:   errorMsg = BAD_REQUEST;
                            break;
                case 401:   errorMsg = UNAUTHORISED;
                            break;
                case 403:   errorMsg = FORBIDDEN;
                            break;
                case 404:   errorMsg = NOT_FOUND;
                            break;
                case 429:   errorMsg = EXCEEDED_REQUESTS;
                            break;
                case 503:   errorMsg = NO_SERVICE;
                            break;
                default:    errorMsg = DEFAULT;
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static String errorMessage() {
        return ApiConnection.errorMsg;
    }

}

package se.breached;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.breached.api.ApiConnection;
import se.breached.encryption.HashEncoder;
import se.breached.model.Breached;
import se.breached.model.Response;

public class HandleResponse implements Response {

    private String hashPrefix(String password) {
        return password.substring(0,5);
    }

    private String hashSuffix(String password) {
        return password.substring(5);
    }

    /*
    * Hashes password and creates a prefix (5 chars), API returns list of matches from partial search.
    * Second search uses the suffix (35 chars) and search for a match.
    * If match exists, returns last digit which represents the number of compromises password has been involved in.
    * */
    @Override
    public AtomicInteger numberOfBreaches(String password) {
        final String pwnedPassword = "https://api.pwnedpasswords.com/range/";
        ApiConnection conn = new ApiConnection();

        final String hashedPassword = HashEncoder.encryptPassword(password);
        assert hashedPassword != null;
        final String kAnonymity = hashPrefix(hashedPassword).toUpperCase();
        final String suffixValue = hashSuffix(hashedPassword).toUpperCase();
        try {
            String[] result = conn.ApiResponse(pwnedPassword + kAnonymity).split("\n");
            AtomicInteger breaches = new AtomicInteger();

            Arrays.stream(result).forEach(e -> {
                if(e.contains(suffixValue)) {
                    breaches.set(Integer.parseInt(e.substring(36)));
                }
            });
           return breaches;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Creates a API request then maps string with Model for structure and returns an ArrayList
    @Override
    public ArrayList<Breached> breachDataFromEmail(String url) {

        ApiConnection conn = new ApiConnection();
        String result = conn.ApiResponse(url);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if(result != null) {
                return objectMapper.readValue(result, new TypeReference<>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

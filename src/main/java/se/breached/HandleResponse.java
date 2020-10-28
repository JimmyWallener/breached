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

    @Override
    public ArrayList<Breached> breachDataFromEmail(String url) {
        // TODO: Handle if no result is found, returns null now, which crashes application
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

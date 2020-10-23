package se.breached;

import se.breached.api.ApiConnection;


public class Controller {
    // API has {Account} - Email/Passwords and {Parameters} and different {Service}. Need to work out logic for that
    private final String apiUrl = "https://haveibeenpwned.com/api/v3/breachedaccount/";

    public void initialize() {
        ApiConnection connection = new ApiConnection();
        String data = connection.ApiResponse(apiUrl);
        System.out.println(data);
    }
}

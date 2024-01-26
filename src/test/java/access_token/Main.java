package access_token;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Main {
    public static String accessToken;
    public static void main(String[] args) {
        // Set base URI
        String baseURL = "https://accounts.spotify.com/api/token";

        // Send POST request and store the response
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "b21009643b4a4c52b6dd0466870f0c1a")
                .formParam("client_secret", "a3e1e76bf476497188826811fa57761b")
                .post(baseURL);
        System.out.println("Status Code: " + response.getStatusCode());

        // Print response body
        accessToken = response.jsonPath().getString("access_token");
        System.out.println(accessToken);
    }
}


package Artists;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Get_Artist {
    @Test
    public void testGetArtist() throws Exception{
        String token = "BQCZcmFHv0_5Bdsmn6V9gExde9-VQmjzFkhGmXQVe6wDU9RODR_NO2dDEA6eyHOk9zjnY7ZjgK27Upx5f0yXXRs6XXooEiztNsnnYQ51N71cZ1-X4x0";
        
        Response artist_data = RestAssured.given()
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists/1Xyo4u8uXC1ZmMpatF05PJ");
        Assert.assertEquals(artist_data.statusCode(), 200);
        String artist_name = artist_data.jsonPath().getString("name");
        System.out.println(artist_data.asString());
    }
}

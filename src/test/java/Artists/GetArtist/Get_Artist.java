package Artists.GetArtist;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Get_Artist {
    String validToken = "BQATJWVxkedxKRyoQpONvztm9ThM7MFlOiCUE5Ly2gNCH9ddLw7XPcPHZ4GQxiiTjI17wuxJeaCheWAwMyQfL47RwOdF_WtSAifxpL_c2LY0CA0RJ8o";
    @Test
    //valid token => return valid body code 200
    public void testGetArtist_validToken(){
        String validToken = "BQATJWVxkedxKRyoQpONvztm9ThM7MFlOiCUE5Ly2gNCH9ddLw7XPcPHZ4GQxiiTjI17wuxJeaCheWAwMyQfL47RwOdF_WtSAifxpL_c2LY0CA0RJ8o";
        Response artist_data = RestAssured.given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + validToken)
                .when()
                .get("https://api.spotify.com/v1/artists/1Xyo4u8uXC1ZmMpatF05PJ");

        Assert.assertEquals(artist_data.statusCode(), 200);
        System.out.println(artist_data.asString());
    }


    @Test
    //expired token => error code 401 
    public void testGetArtist_expToken() throws Exception{
        String token = "BQCZcmFHv0_5Bdsmn6V9gExde9-VQmjzFkhGmXQVe6wDU9RODR_NO2dDEA6eyHOk9zjnY7ZjgK27Upx5f0yXXRs6XXooEiztNsnnYQ51N71cZ1-X4x0";
        
        Response artist_data = RestAssured.given()
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists/1Xyo4u8uXC1ZmMpatF05PJ");
        Assert.assertEquals(artist_data.statusCode(), 401);
        String artist_name = artist_data.jsonPath().getString("name");
        System.out.println(artist_data.asString());
    }


    @Test
    //ArtistID is invalid => error code 404
    public void testGetArtist_badAuth(){
        String validToken = "BQATJWVxkedxKRyoQpONvztm9ThM7MFlOiCUE5Ly2gNCH9ddLw7XPcPHZ4GQxiiTjI17wuxJeaCheWAwMyQfL47RwOdF_WtSAifxpL_c2LY0CA0RJ8o";
        Response artist_data = RestAssured.given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + validToken)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myasgg");

        Assert.assertEquals(artist_data.statusCode(), 404);
        System.out.println(artist_data.asString());
    }


    @Test
    //Request limit within time => error code 429
    public void testAPIRateLimit(){
        for (int t=0 ; t<1050 ; t++){
            Response artist_data = RestAssured.given()
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Authorization", "Bearer " + validToken)
                    .when()
                    .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
            Assert.assertEquals(artist_data.statusCode(), 200, "Request failed:"+(t+1));
        }
    }
}

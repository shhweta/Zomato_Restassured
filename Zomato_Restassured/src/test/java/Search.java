

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

public class Search {

    static String url = "https://developers.zomato.com/api/v2.1/search";
    //static String entity_type= "City";
    //static String entity_id="1";




    public Response getResponseBody()
    {
        Response response=given().header("user-key","e953c8e584df9c9ae5dfcb0b8ed78b0e")
                .queryParam("entity_id",1).queryParam("entity_type","city")
                .when().get(url).then().log()
                .body().extract().response();

        //  System.out.println(response.getBody());
        return response;
    }
    @Test
    public void getStatusCode(){
        Response response=getResponseBody().then().extract().response();
        int statusCode =response.getStatusCode() ;
        Assert.assertEquals(statusCode, 200);
    }
    @Test
    public void getLine(){
        Response response=getResponseBody().then().extract().response();
        System.out.println("Status Line "+response.getStatusLine());

    }

    @Test
    public static void getcookies(){
        System.out.println("The cookies "+
                get(url).then().extract()
                        .cookies());
    }
    @Test
    public static void getResponseHeaders(){
        System.out.println("The headers in the response "+
                get(url).then().extract()
                        .headers());
    }

    @Test
    public static void getResponseContentType(){
        System.out.println("The content type of response "+
                get(url).then().extract()
                        .contentType());
    }

    @Test
    public static void getResponseTime(){
        System.out.println("The time taken to fetch the response "+get(url)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    }

    @Test
    public void getParameter() {

        Response response=getResponseBody().then().extract().response();
        JsonPath jsonPathEvaluator = response.jsonPath();

        System.out.println("results_found received from Response " + jsonPathEvaluator.get("results_found"));


    }


}

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.poi.xwpf.usermodel.IBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;

import  org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;





public class GET {

	static String url = "https://developers.zomato.com/api/v2.1/cities";




	public Response getResponseBody()
	{
	    	Response response=given().header("user-key","e953c8e584df9c9ae5dfcb0b8ed78b0e")
				.queryParam("q","Delhi NCR")

				.when().get(url).then().log()
				.body().extract().response();



		System.out.println(response.getBody());
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
	public void getCityName() {

		Response response=getResponseBody().then().extract().response();
		JsonPath jsonPathEvaluator = response.jsonPath();

		List<Map<String, String>>  location_suggestions = jsonPathEvaluator.getList("location_suggestions");
		System.out.println(location_suggestions.get(0).get("name"));

	}
	@Test
	public void getstatus() {

		Response response=getResponseBody().then().extract().response();
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("status received from Response " + jsonPathEvaluator.get("status"));

	}
}



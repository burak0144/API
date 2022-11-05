package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceholderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06ObjectMapper_Pojo extends JsonplaceholderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like  {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post06ObjectMapper() {
        //Set the Url
        spec.pathParam("first","todos");

        //Set the Expected Data
        JsonPlaceholderPojo expectedData = new JsonPlaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);
        //Send the Request and Get the Response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
    response.prettyPrint();

    //Do Assertion
        JsonPlaceholderPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}

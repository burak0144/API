package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Shashi Gandhi I and Daevika Bhat are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void get11() {
        spec.pathParam("first","users");
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().statusCode(200).body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),
                "data.status",hasItem("active"),
                "data.name",hasItems("Daevika Bhat", "Shashi Gandhi I"));

        List<String> genders =response.jsonPath().getList("data.gender");

        int numfemale=0;
        for (String w:genders
             ) {
            if (w.equalsIgnoreCase("female")){
                numfemale++;
            }
        }
        assertTrue(numfemale<=genders.size()-numfemale);

        //2.yol;Kadin ve erkek sayilarini Groovy ile bulalim

       List<String> femaleNames= response.jsonPath().getList("data.findAll{it.gender='female'}.gender");
       List<String> maleNames= response.jsonPath().getList("data.findAll{it.gender='male'}.gender");
       assertTrue(femaleNames.size()<=maleNames.size());


    }
}

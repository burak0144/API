package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {
     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/
    @Test
    public void Get06b(){
        spec.pathParam("first","unknown");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.pantone_value").size());

       List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);
        assertEquals(3,ids.size());

        List<String> names=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);
        assertEquals(2,names.size());
    }
}

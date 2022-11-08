package post_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post07 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
/*
    Given
        https://dummy.restapiexample.com/api/v1/create

    And    {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
          }
     When
        User sends POST request

     Then
        Status code is 200
     And
        Response body should be like the following
        {
            "status": "success",
            "data": {
                "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
            },
            "message": "Successfully! Record has been added."
        }


     */


    @Test
    public void post07() {
     spec.pathParams("first","create");
        DummyRestApiDataPojo dataPojo=new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyRestApiPojo expectedData=new DummyRestApiPojo("success",dataPojo,"Successfully! Record has been added.");
        System.out.println("expectedData = " + dataPojo);
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dataPojo).when().post("/{first}");
        DummyRestApiPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiPojo.class);
        System.out.println("actualData = " + actualData);
       // assertEquals(200,response.statusCode());
        assertEquals(dataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dataPojo.getEmployee_salarty(),actualData.getData().getEmployee_salarty());
        assertEquals(dataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dataPojo.getProfile_image(),actualData.getData().getProfile_image());

      //  assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

    }
}

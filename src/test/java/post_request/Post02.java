package post_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post02() {
        // 1. Set The URL
        spec.pathParam("first","booking");

        // 2. Set The Expected Data ( put, post, patch)
        RestfulTestData obj= new RestfulTestData();
        Map<String,String> expectedBookingDatas=obj.expectedDataBookingdatesMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedDataAll=obj.expectedDataAllMethod("John","Doe",11111,true,expectedBookingDatas);


        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataAll).when().post("/{first}");
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println(actualData);
        // 4. Do Assertion
        assertEquals(expectedDataAll.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedDataAll.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedDataAll.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedDataAll.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedBookingDatas.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDatas.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }
}

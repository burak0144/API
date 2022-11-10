package get_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees

    When
      User sends Get request

   Them
       i) Status code is 200
   And
       ii) There are 24 employees
   And
      iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       iv) The greatest age is 66
   And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
   And
       vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get17() {
        spec.pathParam("first","employees");
        Response response =given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("data",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));
        List<Integer> ages =response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("En yuksek yas = " + ages.get(ages.size() - 1));
        assertEquals((Integer) 66,ages.get(ages.size() - 1));

        //v) The name of the lowest age is "Tatyana Fitzpatrick"
        String lowestAgeEmployee=response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
        System.out.println("lowestAgeEmployee = " + lowestAgeEmployee);

        assertEquals("[Tatyana Fitzpatrick]",lowestAgeEmployee);

        //vi) Total salary of all employees is 6,644,770
        List<Integer>salaries=response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        //1.yol
        int sum=0;
        for (int w:salaries
             ) {
            sum+=w;
        }
        System.out.println("sum = " + sum);
        assertEquals(6644770,sum);
        //2.yol
        int sum2=salaries.stream().reduce(Math::addExact).get();
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770,sum2);
        //3.yol
        int sum3=salaries.stream().reduce(Integer::sum).get();
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770,sum3);

    }
}

package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void setSpec(){
        spec=new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
    }
    }


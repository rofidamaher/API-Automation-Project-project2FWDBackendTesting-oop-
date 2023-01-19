package oop;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest extends ReqresServer {

    public GetRequest(String endpoint) {
        super(endpoint);
    }
    public static Response send()
    {
       return ReqresServer.request.get();
    }



}

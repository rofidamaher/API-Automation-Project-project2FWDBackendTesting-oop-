package oop;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class  ReqresServer {
    public static String baseUrl = "https://api.trello.com/1/";
    public static String endPoint;
    public static RequestSpecification request;
    public static Map<String, String> parameter;

    static {
        parameter = new HashMap<>();
        parameter.put("key", "459697c4ca3024a82d4afbce8b98cec7");
        parameter.put("token", "ATTAcaf6937e0c91c862812b3854d0840393a01d298da78ba5b7d924ae22eae1c99fAF872D78");
    }
    public ReqresServer(String endpoint) {
        endPoint = endpoint;
        request = RestAssured.
                given()
                .baseUri(baseUrl)
                .basePath(endPoint);

    }
    public void addHeader(String header ,String value)
    {
        request.header(header,value);
    }
    public void addQueryParameters()
    {
        request.queryParams(parameter);
    }


}

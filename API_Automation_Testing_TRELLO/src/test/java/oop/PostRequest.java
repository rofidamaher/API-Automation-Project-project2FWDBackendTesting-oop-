package oop;

import io.restassured.response.Response;

public class PostRequest extends ReqresServer {

    public PostRequest(String endpoint) {
        super(endpoint);
    }
    public void addBody(String body)
    {
        request.body(body);
    }

    public static Response send()
    {
        return ReqresServer.request.post();
    }




}

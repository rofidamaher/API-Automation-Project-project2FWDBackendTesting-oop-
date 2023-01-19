package oop;

import io.restassured.response.Response;

public class DeleteRequest extends ReqresServer {

    public DeleteRequest(String endpoint) {
        super(endpoint);
    }
    public static Response send()
    {
        return ReqresServer.request.delete();
    }


}


package getRequests;

import oop.GetRequest;

public class GetAllListsOnABoard extends GetRequest {

    public GetAllListsOnABoard(String endpoint) {
        super(endpoint);
    }

    public String getMemberid(String value)
    {
        return send().jsonPath().getString(value);
    }

}
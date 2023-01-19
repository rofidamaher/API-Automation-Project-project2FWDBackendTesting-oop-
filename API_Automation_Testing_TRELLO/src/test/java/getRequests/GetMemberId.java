package getRequests;

import oop.GetRequest;
import oop.ReqresServer;

public class GetMemberId extends GetRequest {

    public GetMemberId(String endpoint) {
        super(endpoint);
    }

    public String getMemberid(String value)
    {
        return send().jsonPath().getString(value);
    }

}

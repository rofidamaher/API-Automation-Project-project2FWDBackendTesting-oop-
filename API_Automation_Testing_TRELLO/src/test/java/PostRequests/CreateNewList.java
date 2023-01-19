package PostRequests;

import oop.PostRequest;

public class CreateNewList extends PostRequest {
    public CreateNewList(String endpoint) {
        super(endpoint);
    }

    public String getListId(String value)
    {
        return send().jsonPath().getString(value);
    }


}
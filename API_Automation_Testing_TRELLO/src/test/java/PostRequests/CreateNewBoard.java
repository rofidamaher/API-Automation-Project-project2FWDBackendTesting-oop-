package PostRequests;

import oop.PostRequest;

public class CreateNewBoard extends PostRequest {
    public CreateNewBoard(String endpoint) {
        super(endpoint);
    }

    public String getBoardId(String value)
    {
        return send().jsonPath().getString(value);
    }


}
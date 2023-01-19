package PostRequests;

import oop.PostRequest;

public class CreateNewOrganization extends PostRequest {
    public CreateNewOrganization(String endpoint) {
        super(endpoint);
    }

    public String getOrganizationId(String value)
    {
        return send().jsonPath().getString(value);
    }


}

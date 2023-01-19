package AllRequestOnOneClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class TrelloApiTestAutomation {
    public static String baseUrl = "https://api.trello.com/1/";
    public static String organization_Id;
    public static String member_id;
    public static String board_id;
    public static String list_id;
    public static Map<String, String> parameter;
    static {
        parameter = new HashMap<>();
        parameter.put("key", "459697c4ca3024a82d4afbce8b98cec7");
        parameter.put("token", "ATTAcaf6937e0c91c862812b3854d0840393a01d298da78ba5b7d924ae22eae1c99fAF872D78");
    }

    public static void main(String[] args) throws InterruptedException {
        create_a_new_organization();
        Thread.sleep(1000);
        get_member_id();
        Thread.sleep(1000);
        create_new_Board();
        Thread.sleep(1000);
        get_boards_in_an_organization();
        Thread.sleep(1000);
        create_a_new_list();
        Thread.sleep(1000);
        get_all_lists_on_a_board();
        Thread.sleep(1000);
        archive_a_list();
        Thread.sleep(1000);
        delete_a_board();
        Thread.sleep(1000);
        delete_an_organization();
    }

    public static void create_a_new_organization()
    {
        //{{BaseURL}}organizations?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl +"organizations";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        request.header("Content-Type","application/json");
        //{"displayName": "ApiTestAutomationTrello","desc": "new organization for test Trello Api"}
        request.body("{\"displayName\": \"ApiTestAutomationTrello\",\"desc\": \"new organization for test Trello Api\"}");
        Response response = request.post();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        JsonPath path = response.jsonPath();
        organization_Id = path.getString("id");
        //System.out.println(organization_Id);

        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);

    }
    public static void get_member_id()
    {
        //{{BaseURL}}members/me?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "members/me";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();
        JsonPath path = response.jsonPath();
        member_id = path.getString("id");
        System.out.println(member_id);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }
    public static void create_new_Board()
    {
        //{{BaseURL}}boards/?idOrganization={{organization_Id}}&key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl +"boards";
        RequestSpecification request = RestAssured.given();
        parameter.put("idOrganization",organization_Id);
        request.queryParams(parameter);
        request.header("Content-Type","application/json");
        //{"name": "newBoard","desc": "Create new board","descData":"Create new Board"}
        request.body("{\"name\": \"newBoard\",\"desc\": \"Create new board\",\"descData\":\"Create new Board\"}");
        Response response = request.post();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        JsonPath path = response.jsonPath();
        board_id = path.getString("id");
        //System.out.println(board_id);

        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void get_boards_in_an_organization()
    {
        //{{BaseURL}}organizations/{{organization_Id}}/boards?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "organizations/"+organization_Id+"/boards";
        RequestSpecification request = RestAssured.given();



        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }
    public static void create_a_new_list()
    {
        //{{BaseURL}}lists?name=NewList&idBoard={{board_id}}&key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl +"lists";
        RequestSpecification request = RestAssured.given();
        parameter.put("name","NewList");
        parameter.put("idBoard",board_id);
        request.queryParams(parameter);
        request.header("Content-Type","application/json");

        Response response = request.post();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        JsonPath path = response.jsonPath();
        list_id = path.getString("id");
        //System.out.println(board_id);

        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void get_all_lists_on_a_board()
    {
        //{{BaseURL}}boards/{{board_id}}/lists?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "boards/"+board_id+"/lists";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }

    public static void archive_a_list()
    {
        //{{BaseURL}}lists/{{list_id}}/closed?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "lists/"+list_id+"/closed";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }

    public static void delete_a_board()
    {
        //{{BaseURL}}boards/{{board_id}}?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "boards/"+board_id;
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.delete();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void delete_an_organization()
    {
        //{{BaseURL}}organizations/{{organization_Id}}?key={{key}}&token={{token}}
        RestAssured.baseURI = baseUrl + "organizations/"+organization_Id;
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.delete();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);

    }

}

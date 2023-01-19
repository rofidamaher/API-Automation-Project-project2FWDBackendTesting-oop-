package TestCase;

import DeleteRequests.DeleteABoard;
import DeleteRequests.DeleteAnOrganization;
import PostRequests.CreateNewBoard;
import PostRequests.CreateNewList;
import PostRequests.CreateNewOrganization;
import getRequests.ArchiveAList;
import getRequests.GetAllListsOnABoard;
import getRequests.GetMemberId;
import getRequests.GetAllBoardsInAnOrganization;
import io.restassured.response.Response;
import oop.DeleteRequest;
import oop.GetRequest;
import oop.PostRequest;
import oop.ReqresServer;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TrelloApiTest {
    static Response response;
    public static String member_id;
    public static String organization_Id;
    public static String board_id;
    public static String list_id;

    @Test
    public void test1()
    {
        //{{BaseURL}}organizations?key={{key}}&token={{token}}
        //create new Organization
        CreateNewOrganization createNewOrganization = new CreateNewOrganization("organizations");
        createNewOrganization.addQueryParameters();
        createNewOrganization.addHeader("Content-Type","application/json");
        //{"displayName": "ApiTestAutomationTrello","desc": "new organization for test Trello Api"}
        createNewOrganization.addBody("{\"displayName\": \"ApiTestAutomationTrello\",\"desc\": \"new organization for test Trello Api\"}");
        response = PostRequest.send();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        organization_Id = createNewOrganization.getOrganizationId("id");
        System.out.println(" organization id : "+ organization_Id);
        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);


    }
    @Test
    public void test2()
    {
        // get member id
        GetMemberId getMemberId = new GetMemberId("members/me");
        getMemberId.addQueryParameters();
        response = GetRequest.send();
        response.prettyPrint();
        member_id = getMemberId.getMemberid("id");
        System.out.println(" member id : " + member_id);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }
    @Test
    public void test3()
    {
        // create new board
        //{{BaseURL}}boards/?idOrganization={{organization_Id}}&key={{key}}&token={{token}}
        CreateNewBoard newBoard = new CreateNewBoard("boards");
        ReqresServer.parameter.put("idOrganization",organization_Id);
        newBoard.addQueryParameters();
        newBoard.addHeader("Content-Type","application/json");
        //{"name": "newBoard","desc": "Create new board","descData":"Create new Board"}
        newBoard.addBody("{\"name\": \"newBoard\",\"desc\": \"Create new board\",\"descData\":\"Create new Board\"}");
        response = newBoard.send();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        board_id = newBoard.getBoardId("id");
        System.out.println("board_id : "+ board_id);
        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test4()
    {
        //get All boards in an organizations
        //{{BaseURL}}organizations/{{organization_Id}}/boards?key={{key}}&token={{token}}
        GetAllBoardsInAnOrganization boards = new GetAllBoardsInAnOrganization("organizations/"+organization_Id+"/boards");
        boards.addQueryParameters();
        response = GetRequest.send();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


    }
    @Test
    public void test5()
    {
        //create new list
        //{{BaseURL}}lists?name=NewList&idBoard={{board_id}}&key={{key}}&token={{token}}
        CreateNewList list = new CreateNewList("lists");
        ReqresServer.parameter.put("name","NewList");
        ReqresServer.parameter.put("idBoard",board_id);
        list.addQueryParameters();
        list.addHeader("Content-Type","application/json");
        response = PostRequest.send();
        response.prettyPrint();
        //get id from response body using  JsonPath or xml
        list_id = list.getListId("id");
        System.out.println("list id : " +list_id);
        // assertion on status code using TestNG framework
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test6()
    {
        //get All lists in aboard
        //{{BaseURL}}boards/{{board_id}}/lists?key={{key}}&token={{token}}
        GetAllListsOnABoard lists = new GetAllListsOnABoard("boards/"+board_id+"/lists");
        lists.addQueryParameters();
        response = GetRequest.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }
    @Test
    public void test7()
    {
        // Archive a list
        //{{BaseURL}}lists/{{list_id}}/closed?key={{key}}&token={{token}}
        ArchiveAList archiveAList = new ArchiveAList( "lists/"+list_id+"/closed");
        archiveAList.addQueryParameters();
        response = GetRequest.send();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


    }
    @Test
    public void test8()
    {
        // delete a board
        //{{BaseURL}}lists/{{list_id}}/closed?key={{key}}&token={{token}}
        DeleteABoard deleteABoard = new DeleteABoard("boards/"+board_id);
        deleteABoard.addQueryParameters();
        response = DeleteRequest.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test9()
    {
        //delete an organization
        //{{BaseURL}}organizations/{{organization_Id}}?key={{key}}&token={{token}}
        DeleteAnOrganization deleteAnOrganization = new DeleteAnOrganization( "organizations/"+organization_Id);
        deleteAnOrganization.addQueryParameters();
        response = DeleteRequest.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);


    }

}

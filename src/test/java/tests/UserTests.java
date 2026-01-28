package tests;

import com.kalopap.base.BaseTest;
import com.kalopap.clients.BaseClient;
import com.kalopap.clients.UserClient;
import com.kalopap.models.Address;
import com.kalopap.models.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserTests extends BaseTest {

    UserClient userClient = new UserClient();


    @Test
    public void verifyUserDetails(){

        Response resp1 = userClient.getUserById("2");
        User actualUser = resp1.as(User.class);

        Assert.assertEquals(resp1.getStatusCode(),200);
        Assert.assertEquals(resp1.jsonPath().get("username"),actualUser.getUsername());//"mor_2314"
        Assert.assertEquals(resp1.jsonPath().get("address.zipcode"),actualUser.getAddress().getZipcode()); //"12926-3874"

    }
    @Test
    public void verifyCreateUser(){

        Address addr = new Address();
        addr.setCity("NewYork");
        addr.setNumber(2345);
        addr.setStreet("123 Some St");
        addr.setZipcode("2345-8970");

        User usr1 = new User();
        usr1.setAddress(addr);
        usr1.setEmail("someone@something.com");
        usr1.setId(908);
        usr1.setUsername("somota");
        usr1.setPassword("s_3r");

        Response resp2 = userClient.createUser(usr1);
        Assert.assertEquals(resp2.getStatusCode(),201);

    }

    @Test
    public void verifyInvalidUserId(){
        Response resp3 = userClient.getUserById("y");
        Assert.assertEquals(resp3.getStatusCode(),400);
        Assert.assertEquals(resp3.jsonPath().get("status"),"error");

    }

    @Test
    public void verifyDeleteUser(){
       Response resp4 = userClient.deleteUser("9");

       Assert.assertEquals(resp4.getStatusCode(),200);

    }
    @Test
    public void verifyEdgeCaseForId(){
        Response resp5 = BaseClient.get("user");

        Assert.assertEquals(resp5.getStatusCode(),404);
    }
    @Test
    public void verifyGetAllUsers(){
        Response resp6 = userClient.getAllUsers();

        List<User> allUsers = resp6.jsonPath().get("$");
        int actualCount = allUsers.size();

        Assert.assertEquals(resp6.getStatusCode(),200);
        Assert.assertEquals(actualCount,10);

    }
}

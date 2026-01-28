package tests;

import com.kalopap.base.BaseTest;
import com.kalopap.clients.AuthLoginClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthLoginTests extends BaseTest {

    AuthLoginClient authClient = new AuthLoginClient();

    String testDataJson = "{\"username\":\"kiakals\", \"password\":\"krampwd@\"}";

    @DataProvider(name="loginUsers")
    public Object[][] loginUsers(){
        return new Object[][]{
                {"mor_2314","83r5^_"},
                {"johnd","m38rmF$"}
        };
    }

    @Test
    public void verify400BadRequestError(){
        Response res1 = authClient.postLoginWithoutContentType(testDataJson);
        Assert.assertEquals(res1.getBody().asString(),"username and password are not provided in JSON format");
        Assert.assertEquals(res1.getStatusCode(),400);
    }

    @Test
    public void verify401UnauthorizedError(){
        Response res2 = authClient.postLogin("mor_2314","kramp%^");
        Assert.assertEquals(res2.getStatusCode(),401);
        Assert.assertEquals(res2.getBody().asString(),"username or password is incorrect");
    }

    @Test(dataProvider = "loginUsers")
    public void verifyValidUserLogin(String uname, String passwd){
        Response res3 = authClient.postLogin(uname,passwd);
        Assert.assertNotNull(res3.jsonPath().get("token"));
        Assert.assertEquals(res3.getStatusCode(),201);
    }


}

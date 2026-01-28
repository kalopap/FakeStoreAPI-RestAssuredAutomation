package com.kalopap.clients;

import com.kalopap.models.Address;
import com.kalopap.models.User;
import com.kalopap.utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserClient {

    public Response getUserById(String id) {
        return RestAssured.given()
                .pathParam("id",id)
                .when()
                .get(EndPoints.USER_BY_ID);

    }

    public Response createUser(User user) {

        return RestAssured.given()
                .body(user)
                .when()
                .post(EndPoints.USERS);
    }

    public Response deleteUser(String id){
       return RestAssured.given()
                .header("Content-Type","application/json")
               .pathParam("id",id)
                .when()
                .delete(EndPoints.USER_BY_ID);

    }
    public Response getAllUsers(){
        return RestAssured.given()
                .when()
                .get(EndPoints.USERS);
    }
}

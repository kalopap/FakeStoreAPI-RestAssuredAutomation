package com.kalopap.clients;

import com.kalopap.utils.EndPoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthLoginClient {

    public String buildJsonBody(String uname, String pwd){
       return "{\"username\":\"" + uname + "\",\"password\":\"" + pwd + "\"}";
    }

    public Response postLogin(String username,String password){
        return given()
                .contentType("application/json")
                .body(buildJsonBody(username,password))
                .when()
                .post(EndPoints.AUTH_LOGIN);
    }

    public Response postLoginWithoutContentType(String body){
        return given()
                .body(body)
                .when()
                .post(EndPoints.AUTH_LOGIN);
    }
}

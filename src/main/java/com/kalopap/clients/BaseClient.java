package com.kalopap.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public static Response get(String endPoint){
        return given()
                .when()
                .get(endPoint);
    }

    public static Response put(String endPoint){
        return given()
                .when()
                .put(endPoint);
    }

    public static Response post(String endPoint){
        return given()
                .when()
                .post(endPoint);
    }
}

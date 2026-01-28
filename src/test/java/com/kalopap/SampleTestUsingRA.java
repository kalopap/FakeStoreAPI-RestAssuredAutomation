package com.kalopap;

import org.testng.Assert;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SampleTestUsingRA {

    @Test
    static void getUsers(){
        String baseURL = "https://fakestoreapi.com";
        Response response = given()
                .contentType("application/json")

                .when()
                .get(baseURL+"/users/2");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().get("name.lastname"),"morrison");
        System.out.println();
        System.out.println(response.jsonPath().getString("address.zipcode"));
        System.out.println(response.jsonPath().get("name.lastname").toString());


        System.out.println(response.jsonPath().get("address.geolocation.lat").toString());
/*
{
    "address": {
        "geolocation": {
            "lat": "-37.3159",
            "long": "81.1496"
        },
        "city": "kilcoole",
        "street": "Lovers Ln",
        "number": 7267,
        "zipcode": "12926-3874"
    },
    "id": 2,
    "email": "morrison@gmail.com",
    "username": "mor_2314",
    "password": "83r5^_",
    "name": {
        "firstname": "david",
        "lastname": "morrison"
    },
    "phone": "1-570-236-7033",
    "__v": 0
}
 */
    }
}

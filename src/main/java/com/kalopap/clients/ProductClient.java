package com.kalopap.clients;

import com.kalopap.models.Product;
import com.kalopap.utils.ConfigReader;
import com.kalopap.utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductClient extends BaseClient{


    public Response getAllProducts(){

       return RestAssured.given()
                .when()
                .get(EndPoints.PRODUCTS);

    }

    public Response getProductById(String id){
        return RestAssured.given()
                .pathParam("id",id)
                .when()
                .get(EndPoints.PRODUCT_BY_ID);
    }

    public Response createProduct(Product product){
        return RestAssured.given()
                .header("Content-Type","application/json")
                .body(product)
                .when()
                .post(EndPoints.PRODUCTS);
    }

    public Response updateProduct( Product updatedProduct, String id){
        return RestAssured.given()
                .body(updatedProduct)
                .pathParam("id",id)
                .when()
                .put(EndPoints.PRODUCT_BY_ID);
    }

    public Response deleteProduct( String id){
        return RestAssured.given()
                .pathParam("id",id)
                .when()
                .delete(EndPoints.PRODUCT_BY_ID);
    }
}

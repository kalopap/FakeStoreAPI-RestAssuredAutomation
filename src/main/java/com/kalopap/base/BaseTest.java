package com.kalopap.base;

import com.kalopap.utils.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters("env")
    @BeforeClass
    public void setup(@Optional("DEV") String env){
        ConfigReader.setEnv(env);
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        RestAssured.filters(new AllureRestAssured());
    }

}

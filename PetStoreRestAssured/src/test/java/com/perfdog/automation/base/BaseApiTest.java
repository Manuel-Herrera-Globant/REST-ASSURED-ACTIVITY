package com.perfdog.automation.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    //Base URL de Swagger Petstore
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}



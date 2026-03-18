package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetSinglePetTest extends BaseApiTest {

    @Test
    public void shouldGetSpecificPetById() {
        //obtener una mascota disponible cualquiera
        Response listResponse =
            given()
                .contentType(ContentType.JSON)
                .queryParam("status", "available")
            .when()
                .get("/pet/findByStatus")
            .then()
                .statusCode(200)
                .body("$", not(empty()))
                .extract()
                .response();

        // Cogemos la primera mascota de la lista
        Long petId = listResponse.path("[0].id");
        String petName = listResponse.path("[0].name");

        //Y Consultamos la mascota por ID
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/pet/{petId}", petId)
        .then()
            .statusCode(200)
            //.body("id", equalTo(petId.intValue()))
            .body("id", equalTo(petId))
            .body("name", equalTo(petName))
            .body("status", equalTo("available"));
    }
}



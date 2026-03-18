package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateOrderTest extends BaseApiTest {

    @Test
    public void shouldCreateOrderForAvailablePet() {
        //Obtener una mascota disponible para usar su id
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

        Long petId = listResponse.path("[0].id");

        //Crear la orden
        long orderId = System.currentTimeMillis();

        Map<String, Object> orderBody = new HashMap<>();
        orderBody.put("id", orderId);
        orderBody.put("petId", petId);
        orderBody.put("quantity", 1);
        orderBody.put("shipDate", "2026-03-17T00:00:00.000Z");
        orderBody.put("status", "placed");
        orderBody.put("complete", true);

        given()
            .contentType(ContentType.JSON)
            .body(orderBody)
        .when()
            .post("/store/order")
        .then()
            .statusCode(200)
            .body("id", equalTo(orderId))
            .body("petId", equalTo(petId)) 
            .body("quantity", equalTo(1))
            .body("status", equalTo("placed"))
            .body("complete", equalTo(true));
    }
}



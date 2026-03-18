package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListAvailablePetsTest extends BaseApiTest {
    // Este test valida que al consultar por mascotas disponibles, todas las mascotas retornadas tengan el status "available"
    @Test
    public void shouldListAllAvailablePets() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("status", "available")
        .when()
            .get("/pet/findByStatus")
        .then()
            .statusCode(200)
            // Aqui validamos que sea un array en la raíz
            .body("$", is(not(empty())))
            // Aqui validamos que cada elemento tenga status = "available"
            .body("status", everyItem(equalTo("available")));
    }
}



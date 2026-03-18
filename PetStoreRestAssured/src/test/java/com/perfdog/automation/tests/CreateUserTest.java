package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseApiTest {
    //Creo usuario con datos dinámicos para evitar conflictos con usuarios existentes
    @Test
    public void shouldCreateUser() {
        long id = System.currentTimeMillis();
        String username = "user_" + UUID.randomUUID();
        String password = "pass123";

        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("username", username);
        body.put("firstName", "Test");
        body.put("lastName", "User");
        body.put("email", username + "@mail.com");
        body.put("password", password);
        body.put("phone", "123456789");
        body.put("userStatus", 1);

        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/user")
        .then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", anyOf(nullValue(), is(emptyOrNullString()), equalTo("unknown")))
            .body("message", equalTo(String.valueOf(id)));
    }
} // Y hacemos la validacion final para verificar que el usuario se creó correctamente, validando el código de respuesta y el mensaje que contiene el ID del nuevo usuario.


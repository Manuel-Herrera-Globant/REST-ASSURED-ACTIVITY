package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginUserTest extends BaseApiTest {

    @Test
    public void shouldLoginWithNewlyCreatedUser() {
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

        //Crear usuario denuevo , para que sea una precondicion dentro del mismo test, sin depender de otros tests
        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("/user")
        .then()
            .statusCode(200)
            .body("code", equalTo(200));

        // Ahora si el login
        given()
            .queryParam("username", username)
            .queryParam("password", password)
        .when()
            .get("/user/login")
        .then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("message", containsString("logged in user session"));
    }
}



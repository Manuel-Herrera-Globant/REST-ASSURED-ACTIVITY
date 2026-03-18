package com.perfdog.automation.tests;

import com.perfdog.automation.base.BaseApiTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LogOutUserTest extends BaseApiTest {

    @Test
    public void shouldLogoutAfterLogin() {
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

        //Crear usuario
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/user")
        .then()
            .statusCode(200)
            .body("code", equalTo(200));

        //Login
        given()
            .queryParam("username", username)
            .queryParam("password", password)
        .when()
            .get("/user/login")
        .then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("message", containsString("logged in user session"));

        //Logout
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/user/logout")
        .then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("message", anyOf(equalTo("ok"), equalTo("OK")));
    }
}



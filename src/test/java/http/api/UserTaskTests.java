package http.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.dto.UserDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

// IMPORTANT! Test requires rest api server to be running
public class UserTaskTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080/example-rest-1.0-SNAPSHOT/api";
    }

    @Test
    public void successfulTaskCreationTest(){
        var userModel = new UserDto();
        userModel.login = "Jaca";

        var response1 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(userModel)
                .when()
                .post("/users");
        response1.then()
                .statusCode(201)
                .header("Location", notNullValue());

        var user = response1.body().as(UserDto.class);

        Assertions.assertEquals("Jaca", user.login);

        var taskModel = new TaskDto();
        taskModel.title = "Pranie";
        taskModel.description = "Zrobić pranie";
        taskModel.userId = user.guid;

        var response2 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(taskModel)
                .when()
                .post("/tasks");
        response2.then()
                .statusCode(201)
                .header("Location", notNullValue());
    }
}

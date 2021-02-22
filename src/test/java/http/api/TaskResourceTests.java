package http.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import sitammatt.example_rest.dto.TaskDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

// IMPORTANT! Test requires rest api server to be running
public class TaskResourceTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080/example-rest-1.0-SNAPSHOT/api";
    }

    @Test
    public void successfulTaskCreationTest(){
        var model = new TaskDto();
        model.title = "Pranie";
        model.description = "Zrobić pranie";

        var response = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/tasks");
        response.then()
                .statusCode(201)
                .header("Location", notNullValue());
    }

    @Test
    public void successfulTaskRemovalTest(){
        var model = new TaskDto();
        model.title = "Pranie";
        model.description = "Zrobić pranie";

        var response1 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/tasks");
        response1.then()
                .statusCode(201)
                .header("Location", notNullValue());

        var guid = response1.body().as(TaskDto.class).guid;

        var response2 = given().relaxedHTTPSValidation()
                .when()
                .get("/tasks/" + guid);
        response2.then()
                .statusCode(200);

        var response3 = given().relaxedHTTPSValidation()
                .when()
                .delete("/tasks/" + guid);
        response3.then()
                .statusCode(204);

        var response4 = given().relaxedHTTPSValidation()
                .when()
                .get("/tasks/" + guid);
        response4.then()
                .statusCode(404);
    }

    @Test
    public void successfulTaskUpdateTest() throws ParseException {
        var model = new TaskDto();
        model.title = "Pranie";
        model.description = "Zrobić pranie";

        var response1 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/tasks");
        response1.then()
                .statusCode(201)
                .header("Location", notNullValue());

        var guid = response1.body().as(TaskDto.class).guid;

        model.description = "Zrobić pranie i wywiesić je";

        var date = new Date();

        var response2 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .put("/tasks/" + guid);
        response2.then()
                .contentType("application/json")
                .statusCode(200);

        var body = response2.body().as(TaskDto.class);

        Assertions.assertEquals("Zrobić pranie i wywiesić je", body.description);
        Assertions.assertEquals("Pranie", body.title);

        var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        Assertions.assertTrue(date.before(formatter.parse(body.modifiedDate)));
    }
}

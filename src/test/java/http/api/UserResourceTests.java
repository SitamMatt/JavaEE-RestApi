package http.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import sitammatt.example_rest.dto.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// IMPORTANT! Test requires rest api server to be running
public class UserResourceTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080/example-rest-1.0-SNAPSHOT/api";
    }

    @Test
    public void successfulTaskCreationTest(){
        var model = new UserDto();
        model.login = "Jaca";

        var response = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/users");
        response.then()
                .statusCode(201)
                .header("Location", notNullValue());
    }

    @Test
    public void successfulTaskRemovalTest(){
        var model = new UserDto();
        model.login = "Jaca";

        var response1 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/users");
        response1.then()
                .statusCode(201)
                .header("Location", notNullValue());

        var guid = response1.body().as(UserDto.class).guid;

        var response2 = given().relaxedHTTPSValidation()
                .when()
                .get("/users/" + guid);
        response2.then()
                .statusCode(200);

        var response3 = given().relaxedHTTPSValidation()
                .when()
                .delete("/users/" + guid);
        response3.then()
                .statusCode(204);

        var response4 = given().relaxedHTTPSValidation()
                .when()
                .get("/users/" + guid);
        response4.then()
                .statusCode(404);
    }

    @Test
    public void successfulTaskUpdateTest() throws ParseException {
        var model = new UserDto();
        model.login = "Jaca";

        var response1 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/users");
        response1.then()
                .statusCode(201)
                .header("Location", notNullValue());

        var guid = response1.body().as(UserDto.class).guid;

        model.login = "Sitam";

        var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        var date = formatter.parse(formatter.format(new Date()));

        var response2 = given().relaxedHTTPSValidation()
                .contentType("application/json")
                .body(model)
                .when()
                .put("/users/" + guid);
        response2.then()
                .contentType("application/json")
                .statusCode(200);

        var body = response2.body().as(UserDto.class);

        Assertions.assertEquals("Sitam", body.login);

        assertThat(date, anyOf(lessThanOrEqualTo(formatter.parse(body.modifiedDate))));
    }
}

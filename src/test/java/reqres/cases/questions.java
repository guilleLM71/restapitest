package reqres.cases;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class questions {

    public static void validateStatusCode(Response response, Integer statusCodeExpected) {
        assertEquals(
                statusCodeExpected,
                response.statusCode(),
                "Validacion del Status Code"
        );
    }

    public static void validateJsonSchemaPage(Response response, Integer pageExpected) {
        JsonSchemaValidator jsv = JsonSchemaValidator.matchesJsonSchemaInClasspath("reqres/ReqResJsonSchemaPage.json");
        response.then().assertThat().body(jsv);
        int page = response.jsonPath().getInt("page");
        assertEquals(pageExpected, page);
    }

    public static void validateJsonSchemaUser(Response response, Integer idExpected, String emailExpected, String first_nameExpected, String last_nameExpected, String avatarExpected) {
        JsonSchemaValidator jsv = JsonSchemaValidator.matchesJsonSchemaInClasspath("reqres/ReqResJsonSchemaUser.json");
        response.then().assertThat().body(jsv);
        int id = response.jsonPath().getInt("data.id");
        String email = response.jsonPath().getString("data.email");
        String first_name = response.jsonPath().getString("data.first_name");
        String last_name = response.jsonPath().getString("data.last_name");
        String avatar = response.jsonPath().getString("data.avatar");
        assertEquals(idExpected, id);
        assertEquals(emailExpected, email);
        assertEquals(first_nameExpected, first_name);
        assertEquals(last_nameExpected, last_name);
        assertEquals(avatarExpected, avatar);
    }

    public static void validateJsonSchemaUserNotFound(Response response) {
        assertNull(response.jsonPath().getString("data"));
    }

    public static void validateJsonSchemaCreateUser(Response response, String nameExpected, String jobExpected) {
        JsonSchemaValidator jsv = JsonSchemaValidator.matchesJsonSchemaInClasspath("reqres/ReqResJsonSchemaCreateUser.json");
        response.then().assertThat().body(jsv);
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        assertEquals(nameExpected, name);
        assertEquals(jobExpected, job);
    }

}

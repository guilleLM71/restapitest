package reqres.cases;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import reqres.general.reqresAPIBase;

public class caseTest extends reqresAPIBase {

    @Test
    public void getRequestPage() {
        Response response = requestPage.getRequestPage(requestSpecification, 2);
        questions.validateStatusCode(response, 200);
        questions.validateJsonSchemaPage(response,2);
    }

    @Test
    public void getRequestUser() {
        Response response = requestPage.getRequestUser(requestSpecification, 12);
        questions.validateStatusCode(response, 200);
        questions.validateJsonSchemaUser(response,12,"rachel.howell@reqres.in","Rachel","Howell","https://reqres.in/img/faces/12-image.jpg");
    }

    @Test
    public void getRequestUserNotFound() {
        Response response = requestPage.getRequestUser(requestSpecification, 40);
        questions.validateStatusCode(response, 404);
        questions.validateJsonSchemaUserNotFound(response);
    }

    @Test
    public void postCreateUser() {
        Response response = requestPage.postCreateUser(requestSpecification, "morpheus", "leader");
        questions.validateStatusCode(response, 201);
        questions.validateJsonSchemaCreateUser(response, "morpheus", "leader");
    }

    @Test
    public void delayRequestPage() {
        Response response = requestPage.delayRequestPage(requestSpecification, 10);
        questions.validateStatusCode(response, 200);
    }

}

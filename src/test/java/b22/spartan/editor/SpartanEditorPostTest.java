package b22.spartan.editor;

import io.cucumber.java.af.En;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;
import java.util.Map;

@Disabled
@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @Test
    public void postSpartanAsEditor(){


        Map<String, Object> bodyMap= SpartanUtil.getRandomSpartanMap();
       // System.out.println(bodyMap);

        SerenityRest.given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/spartans")
                .prettyPrint();
        Ensure.that("Status code is 201", v -> v.statusCode(201));
        Ensure.that("Content type is JSON", validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("Success message is says A Spartan is Born!", valid -> valid.body("success",
                is("A Spartan is Born!")));
        Ensure.that("id  has creation number", jamal -> jamal.body("data.id",is(notNullValue())));
        Ensure.that("name is correct", vR -> vR.body("data.name", is(bodyMap.get("name"))));
        Ensure.that("Gender is Correct ", v -> v.body("data.gender", is(bodyMap.get("gender"))));
        Ensure.that("phone is correct", v -> v.body("data.phone", is(bodyMap.get("phone"))));
String id = lastResponse().jsonPath().getString("data.id");
Ensure.that("chcek location header ends with newly generated id",
        vR-> vR.header("Location", endsWith(id)));
    }


    @Test
    public void test3(){

    }
}

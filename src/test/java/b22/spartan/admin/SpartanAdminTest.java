package b22.spartan.admin;


import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;
@Disabled
@SerenityTest
public class SpartanAdminTest {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.195.19.167:7000";

    }
    //   SerenityProject/target/site/serenity/index.html

    @Test
    public void getAllSpartan() {

        given().accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("api/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }


    @Test
    public void getOneSpartan() {
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}");

        System.out.println("lastResponse().statusCode() = " + lastResponse().statusCode());
        System.out.println("lastResponse().path(\"id\") = " + lastResponse().path("id"));
        String name = lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);


    }

    @Test
    public void test4(){
        given().accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}");

        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200) );
        Ensure.that("Content type is JSON", validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("id is 15", validatableResponse -> validatableResponse.body("id", is(15)));

    }




}

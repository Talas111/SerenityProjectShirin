package b22.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.SpartanNewBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;

@Disabled
@SerenityTest
public class PostWithParametirized extends SpartanNewBase {

    @ParameterizedTest(name= "New Spartan {index} - name:{0}")
    @CsvFileSource(resources = "/SpartanData.csv", numLinesToSkip = 1)
    public void postSpartanWithCSV(String name, String gender, long phone){
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


        Map<String, Object> bodyMap= new LinkedHashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("gender", gender);
        bodyMap.put("phone", phone);


        SerenityRest.   given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/spartans")
                .then().log().all();

        //status code is 201
        Ensure.that("Status code is 201", x -> x.statusCode(201));
        //content type is Json
        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));
        //success message is A Spartan is Born!
        Ensure.that("success message is correct",
                thenPart -> thenPart.body("success",is("A Spartan is Born!"))
        );
        //id is not null
        Ensure.that("id is not null",
                thenPart -> thenPart.body("data.id",notNullValue())
        );
        //name is correct
        Ensure.that("name is correct",
                thenPart -> thenPart.body("data.name",is(bodyMap.get("name")))
        );
        //gender is correct
        Ensure.that("gender is correct",
                thenPart -> thenPart.body("data.gender",is(bodyMap.get("gender")))
        );
        //phone is correct
        Ensure.that("phone is correct",
                thenPart -> thenPart.body("data.phone",is(bodyMap.get("phone")))
        );
        //check location header ends with newly generated id
        //get id and save
        String id = lastResponse().jsonPath().getString("data.id");

        Ensure.that("check location header ends with newly generated id",
                vR -> vR.header("Location",endsWith(id))
        );

    }

}

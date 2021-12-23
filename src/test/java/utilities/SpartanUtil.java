package utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public static Faker faker = new Faker();

    public static Map<String,Object> getRandomSpartanMap(){

        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName()   );
        bodyMap.put("gender", faker.demographic().sex()  );
       // bodyMap.put("lastname", faker.demographic().educationalAttainment());
        bodyMap.put("phone", faker.number().numberBetween(5_000_000_000L, 10_000_000_000L  ) ) ;

        return bodyMap ;
    }






}

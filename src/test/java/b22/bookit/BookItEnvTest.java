package b22.bookit;

import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigReader;


@SerenityTest
public class BookItEnvTest {


    @Test
    public void test2(){
        System.out.println("ConfigReader.getProperty(\"base.url\") = " + ConfigReader.getProperty("base.url"));
        System.out.println("ConfigReader.getProperty(\"teacher.username\") = " + ConfigReader.getProperty("teacher.username"));
        System.out.println("ConfigReader.getProperty(\"teacher.password\") = " + ConfigReader.getProperty("teacher.password"));
    }
}

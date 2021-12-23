package b22.spartan.editor;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

@Disabled
@SerenityTest
public class ConfigDemoTest {


    @Test
    public void test1(){
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
    }
}

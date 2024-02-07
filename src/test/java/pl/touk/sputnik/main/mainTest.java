package pl.touk.sputnik.main;
import org.junit.jupiter.api.Test;
import pl.touk.sputnik.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class mainTest {
    @Test
    public void testSayHello() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.sayHello();

        String expectedOutput = "Hello from team delta";
        assertTrue(outContent.toString().trim().contains(expectedOutput));

        System.setOut(System.out);
    }
}

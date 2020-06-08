package bindings.driver;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SeleniumHelper extends DriverFactory{

    public void takeScrenshoot ()  {
        TakesScreenshot screnshooter = (TakesScreenshot) getDriver() ;
        File screenshot  =screnshooter.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get("src/main/resources/test.pmg"));
        } catch (IOException ioException) {
            System.out.println("Nie znaleziono pliku");
        }

    }
}


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static WebDriver driver;

    public static void initializeDriver() {
        if (driver == null) {
            //Need to replace driver path according to your environment:
            System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver-win64\\chromedriver.exe"); // Update the path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public static void openUrl(String url) {
        if (driver == null) {
            initializeDriver();
        }
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

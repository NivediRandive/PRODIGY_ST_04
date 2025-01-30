import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class CrossBrowserTest {
    public static final String USERNAME = "nivedirandive_XPwVxQ";
    public static final String ACCESS_KEY = "bUzrypvWwsEWszrLxp6t";
    public static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException {
        String[][] browsers = {
            {"Chrome", "latest", "Windows", "10"},
            {"Firefox", "latest", "Windows", "10"},
            {"Safari", "latest", "OS X", "Big Sur"},
            {"Edge", "latest", "Windows", "10"}
        };

        for (String[] browser : browsers) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browser[0]);
            caps.setCapability("browserVersion", browser[1]);
            caps.setCapability("os", browser[2]);
            caps.setCapability("osVersion", browser[3]);
            caps.setCapability("name", "Cross Browser Test");

            WebDriver driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);

            try {
                driver.get("https://browserstack.com/login");

                WebElement usernameField = driver.findElement(By.name("username"));
                WebElement passwordField = driver.findElement(By.name("password"));
                WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

                usernameField.sendKeys("User");
                passwordField.sendKeys("pass123");
                loginButton.click();

                if (driver.getTitle().contains("Dashboard")) {
                    System.out.println(" Test Passed on " + browser[0]);
                } else {
                    System.out.println(" Login failed on " + browser[0]);
                }
            } catch (Exception e) {
                System.out.println(" Test Failed on " + browser[0] + " - " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }
}

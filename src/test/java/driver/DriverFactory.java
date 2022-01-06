package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Locale;

public class DriverFactory {

    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String IE = "ie";
    public static final String EDGE = "edge";
    private static final String[] BROWSERS = {EDGE, FIREFOX, IE, CHROME};

    public static WebDriver getDriver(){
        if(System.getenv("RANDOMIZE").trim().toLowerCase().equals("true")){
            return randomBrowser();
        }
        String browser = System.getenv("BROWSER");

        return getWebDriver(browser);
    }

    public static WebDriver getWebDriver(String browser) {
        browser = (browser == null) ? "FIREFOX": browser;
        browser = browser.trim().toUpperCase();

        switch (browser) {
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            //case "PHANTOMJS":
                //driver = new PhantomJsDriver();
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();

	            ChromeOptions options = new ChromeOptions();
	            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
	                options.addArguments("--headless");
	                options.addArguments("--disable-gpu");
	            }

                return new ChromeDriver(options);
        }
    }

    private static WebDriver randomBrowser() {return getWebDriver(BROWSERS[(int)Math.floor(Math.random()*4)]);}
}

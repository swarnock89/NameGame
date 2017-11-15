package utilities;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	
	public static WebDriver open(String browserType) {
		if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shawn.warnock\\Documents\\Automation\\chromedriver.exe");
			return new ChromeDriver();
		}
		else if (browserType.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\shawn.warnock\\Documents\\Automation\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		else {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\shawn.warnock\\Documents\\Automation\\geckodriver.exe");
			return new FirefoxDriver();
		}	
	}
}

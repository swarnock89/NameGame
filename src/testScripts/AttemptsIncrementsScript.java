package testScripts;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AttemptsIncrementsScript {
	WebDriver driver;
	
	@Test
	public void AttemptsIncrementTest() {
		WebElement initialTriesElement = driver.findElement(By.xpath("//*[@id=\"stats\"]/span[1]"));
		WebElement pictureOneElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div[1]/div[1]"));
		int initialTries = Integer.parseInt(initialTriesElement.getText());
		
		boolean triesIncrementedCorrectly = false;
		
		pictureOneElement.click();

		int finalTries = Integer.parseInt(driver.findElement(By.cssSelector("span[class='attempts']")).getText());
		
		if(finalTries == (initialTries + 1)) {
			triesIncrementedCorrectly = true;
		}
		Assert.assertTrue(triesIncrementedCorrectly);
		System.out.println("Attempts counter increments correctly");
		System.out.println("Initial Tries: " + initialTries);
		System.out.println("Final Tries: " + finalTries);
	
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String webURL = "http://www.ericrochester.com/name-game/";
		
		driver.get(webURL);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}

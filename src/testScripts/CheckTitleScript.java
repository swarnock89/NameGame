package testScripts;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class CheckTitleScript {
	WebDriver driver;
	
	@Test
	public void TitlePresentScript() {
		WebElement nameElement = driver.findElement(By.cssSelector("h1[class='text-muted']"));
		boolean namePresent = false;
		
		if(nameElement.isDisplayed()) {
			namePresent = true;
		}
		Assert.assertTrue(namePresent);
		System.out.println("Title is present");
		System.out.println(nameElement.getText());
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

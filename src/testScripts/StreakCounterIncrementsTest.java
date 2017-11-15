//This test will check to see if
//the streak coutner increments
//for correct selections

package testScripts;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class StreakCounterIncrementsTest {
	WebDriver driver;
	
	@Test
	public void StreakCounterTest() {
		//define variables
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		WebElement initialStreakElement = driver.findElement(By.cssSelector("span[class='streak']"));	
		String name = nameElement.getAttribute("textContent");
		boolean streakCounterIncremented = false;
		
		//Get the initial streak
		int initialStreak = Integer.parseInt(initialStreakElement.getText());


		//Select the correct picture
		for(int i = 1; i<=5; i++) {
			WebElement pictureNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[2]")));
			WebElement pictureClickElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[1]"));
			String pictureName = pictureNameElement.getText();
			if(pictureName.equals(name)) {
				pictureClickElement.click();
				break;
			}
		}
		
		//Get the final streak
		WebElement finalStreakElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='streak']")));
		int finalStreak = Integer.parseInt(finalStreakElement.getText());
		
		//Check to see if streak counter was incremented
		if(finalStreak == (initialStreak + 1)) {
			streakCounterIncremented = true;
		}
		Assert.assertTrue(streakCounterIncremented);
		System.out.println("Streak counter incremented correctly");
		System.out.println("Initial Streak: " + initialStreak);
		System.out.println("Final Streak: " + finalStreak);
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

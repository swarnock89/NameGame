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

public class StreakBreakerTest {
	WebDriver driver;
	
	@Test
	public void TestStreakBreak() {
		//define variables
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean streakBroken = false;
		
		
		
		//Get the initial streak

		//Get a streak of 4
		for(int x = 1; x<5; x++) {
			WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
			String name = nameElement.getAttribute("textContent");
			
			//Iterate through the loop until we find the correct picture
			for(int i = 1; i<=5; i++) {
			WebElement pictureNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[2]")));
			WebElement pictureClickElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[1]"));
			String pictureName = pictureNameElement.getText();
			
			//Check to see if the we are at the correct picture if we do, click it
			if(pictureName.equals(name)) {
				pictureClickElement.click();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
				}
			}
			
		}
		
		//Select an incorrect picture
		WebElement nameElementFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		String finalName = nameElementFinal.getAttribute("textContent");
		for(int i = 1; i<=5; i++) {
			WebElement pictureNameElementFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[2]")));
			WebElement pictureClickElementFinal = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[1]"));
			String pictureNameFinal = pictureNameElementFinal.getText();
			
			if(!pictureNameFinal.equals(finalName)) {
				pictureClickElementFinal.click();
				break;
			}
		}
		
		//Check to see if the streak was reset
		WebElement streakElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='streak']")));
		int streakCount = Integer.parseInt(streakElement.getText());
		if(streakCount == 0) {
			streakBroken = true;
		}
		Assert.assertTrue(streakBroken);
		System.out.println("Streak reset correctly");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String webURL = "http://www.ericrochester.com/name-game/";
		
		driver.get(webURL);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}

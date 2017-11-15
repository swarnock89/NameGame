package testScripts;

import org.testng.annotations.Test;  
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

	public class CheckCountersTest {
	WebDriver driver;
	
	@Test
	public void CountersTest() {
		//Setup our variables
		Random rand = new Random();
		int tries = 0;
		int correct = 0; 
		int streak = 0;
		boolean countersAreCorrect = true;
		for(int i = 1; i <=10; i++) {
			int x = 1;
			boolean correctPictureSelected = false;
			WebDriverWait wait = new WebDriverWait(driver, 10);
			//Check to see whether we selected the correct picture or not
			while(!correctPictureSelected) {
				WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
				WebElement pictureElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ x +"]/div[1]")));
				WebElement pictureNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ x +"]/div[2]")));
				String name = nameElement.getText();
				String pictureName = pictureNameElement.getText();
				if(name.equals(pictureName)) {
					tries++;
					correct++;
					streak++;
					correctPictureSelected = true;
				}
				else {
					tries++;
					streak = 0;
					x++;
				}
				pictureElement.click();
			}
			
			//See if our expected counters match the displayed counters
			WebElement triesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='attempts']")));
			WebElement correctElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='correct']")));
			WebElement streakElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='streak']")));
			int actualTries = Integer.parseInt(triesElement.getText());
			int actualCorrect = Integer.parseInt(correctElement.getText());
			int actualStreak = Integer.parseInt(streakElement.getText());
			if(actualTries != tries || actualCorrect != correct || actualStreak != streak) {
				countersAreCorrect = false;
			}
			Assert.assertTrue(countersAreCorrect);
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Final Counts:\nTries: " + tries + "\nCorrect: " + correct + "\nStreak: " + streak);
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

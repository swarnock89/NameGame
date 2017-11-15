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

public class PhotosChangeTest {
	WebDriver driver;

	@Test
	public void ChangeTest() {
		//Setup variables
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String initialNames[] = new String[6];
		boolean namesChanged = false;
		WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		initialNames[0] = nameElement.getText();
		
		//Add each picture name to the initialNames array
		for(int i = 1; i <= 5; i++) {
			WebElement pictureNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[2]")));
			initialNames[i] = pictureNameElement.getText();
		}
		
		//Find the name that is the same as the name in the question then click it
		for(int i = 1; i <= 5; i++) {
			if(initialNames[0].equals(initialNames[i])) {
				WebElement pictureClickElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[1]"));
				pictureClickElement.click();
				break;
			}
		}
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Put the names on the second page into an array
		String finalNames[] = new String[6];
		WebElement finalNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		finalNames[0] = finalNameElement.getText();
		for(int i = 1; i <= 5; i++) {
			WebElement pictureNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gallery\"]/div/div["+ i +"]/div[2]")));
			finalNames[i] = pictureNameElement.getText();
		}
		
		//check to see if the name in the question is different
		if(!initialNames[0].equals(finalNames[0])) {
			//check to see if any of the pictures have changed
			for(int i = 1; i <= 5; i++) {
				if(!initialNames[i].equals(finalNames[i])) {
					namesChanged = true;
					break;
				}
			}
		}
		Assert.assertTrue(namesChanged);
		System.out.println("The pictures and names have changed");
		System.out.println("Initial Name: " + initialNames[0]);
		System.out.println("Final Name: " + finalNames[0]);
		System.out.println("Initial Picture Names: ");
		for(int i = 1; i <= 5; i++) {
			System.out.println(initialNames[i]);
		}
		System.out.println("Final Picture Names:");
		for(int i = 1; i <= 5; i++) {
			System.out.println(finalNames[i]);
		}
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver = utilities.DriverFactory.open("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String webURL = "http://www.ericrochester.com/name-game/";
		
		driver.get(webURL);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}

package testScripts;

import org.testng.annotations.Test;

import exceptions.SleepTooShortException;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class MinimumSleepTest {
	WebDriver driver;
	
  @Test
  public void f() {
	  for (int i = 3000; i > 0; i -= 10) {
		WebElement nameElement = driver.findElement(By.id("name"));
		String name = nameElement.getText();
		try {
			Thread.sleep(i);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if(!driver.findElement(By.className("attempts")).isDisplayed()){
				throw new SleepTooShortException();
			} 
		}catch (SleepTooShortException e){
				System.out.println("Program failed at a sleep of " + i + " milliseconds");
			}
		innerLoop:
		for (int x = 1; x <= 5; x++) {

			int count = Integer.parseInt(driver.findElement(By.className("attempts")).getText());
			WebElement clickElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ x +"]"));
			clickElement.click();
			
			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    
			try {
				if(!driver.findElement(By.className("attempts")).isDisplayed()) {
					throw new SleepTooShortException();
				}
			} catch (SleepTooShortException e) {
				System.out.println("Program failed at a sleep of " + i + " milliseconds");
			}
			WebElement nameOfClickElement = driver.findElement(By.xpath("//*[@id=\"gallery\"]/div/div["+ x +"]/div[2]"));
			if (name.equals(nameOfClickElement.getText())) {
				break innerLoop;
			}

	    int countAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());
	    if(countAfter <= count) {
	    	System.out.println("Program failed at a sleep of " + i + " milliseconds");
	    	break;
		}
	  }
	  }
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


package learnTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_05_DataProvider {
	WebDriver driver;
	
	@DataProvider(name="loginData")
	public Object[][] UserAccount() {
		return new Object[][] { 
			{"selenium_11_01@gmail.com", "111111"},
			{"selenium_11_02@gmail.com", "222222"},
			{"selenium_11_03@gmail.com", "333333"},
		};
	}
	
	@BeforeMethod
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test(dataProvider = "loginData")
	public void TC_01_LoginFacebook(String userName, String password) {
		driver.findElement(By.cssSelector("#email")).sendKeys(userName);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		sleepInSeconds(3);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
//	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("abcxyz");
		sleepInSeconds(5);	
	}
	
	@Test
	public void TC_02_Class() {
//		driver.findElement(By.className("page-title"));
//		driver.findElement(By.name("aaaa"));
		driver.findElement(By.partialLinkText("temap"));
		driver.findElement(By.tagName("div"));
		driver.findElement(By.xpath(""));
		driver.findElement(By.cssSelector(""));
	}
	
	public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

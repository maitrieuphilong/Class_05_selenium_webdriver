package learnTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dependencies {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	public void TC_01() {
		driver.get("http://live.techpanda.org/");

		// Chạy fail TC_01, thì TC_02 sẽ không được chạy vì nó depend (phụ thuộc) lên
		// nhau
		Assert.assertTrue(driver.findElement(By.xpath("//h3")).isDisplayed());
	}

	@Test(dependsOnMethods = "TC_01")
	public void TC_02() {
		System.out.println("Run TC_02");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

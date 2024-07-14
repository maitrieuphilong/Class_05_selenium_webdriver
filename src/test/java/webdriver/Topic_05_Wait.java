package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Wait {
	WebDriver driver;
	By emailTxtbox = By.xpath("//input[@name='reg_email__']");
	By confirmEmailTxtbox = By.xpath("//input[@name='reg_email_confirmation__']");
	By registrationButton = By.xpath("//a[@data-testid='open-registration-form-button']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginFacebook() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// Dùng static wait
//		sleepInSeconds(5);

		// Dùng implicit wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Dùng explicit wait
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='reg_email__']")));

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("abc@gmail.com");
	}
	
	@Test
	public void TC_02_Explicit_Visible_Invisible() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		driver.findElement(registrationButton).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTxtbox));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTxtbox));
		driver.findElement(emailTxtbox).sendKeys("abc@gmail.com");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTxtbox));
	}
	
	@Test
	public void TC_03_Explicit_Presence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		driver.findElement(registrationButton).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTxtbox));
		driver.findElement(emailTxtbox).sendKeys("abc@gmail.com");
		driver.findElement(emailTxtbox).clear();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTxtbox));
	}
	
	@Test
	public void TC_03_Explicit_Staleness() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		driver.findElement(registrationButton).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTxtbox));
		driver.findElement(emailTxtbox).sendKeys("abc@gmail.com");
		
		WebElement confirmEmailElement = driver.findElement(confirmEmailTxtbox);
		// Đóng form Đăng ký
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailElement));
	}
	
	

	

	@AfterClass
	public void afterClass() {
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

package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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

	@Test
	public void TC_04_FluentWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Chờ cho chữ Hello world xuất hiện trong 10s, polling 0.1s
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		String helloText = fluentDriver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver webDriver) {
				String text = webDriver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
				System.out.println("Get text = " + text);
				return text;
			}
		});
		Assert.assertEquals(helloText, "Hello World!");
	}

	@Test
	public void TC_05_Explicit_Function() {
		// Dùng explicit wait
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='reg_email__']")));

		// Chờ cho 1 Alert presence trong HTML/DOM trước khi thao tác lên
		explicitWait.until(ExpectedConditions.alertIsPresent());

		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("")),
				driver.findElement(By.xpath(""))));

		// Kết hợp nhiều điều kiện - 2 điều kiện đều đúng
		explicitWait.until(ExpectedConditions.and(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")),
				ExpectedConditions.visibilityOfElementLocated(By.xpath(""))));

		// Kết hợp nhiều điều kiện - 1 trong 2 điều kiện đúng
		explicitWait.until(ExpectedConditions.or(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("")),
				ExpectedConditions.visibilityOfElementLocated(By.xpath(""))));

		// Chờ cho 1 element có attribute chứa giá trị mong đợi - Tương đối
		explicitWait.until(
				ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire"));

		// Chờ cho 1 element có attribute chứa giá trị mong đợi - Tuyệt đối
		explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder",
				"Search entire store here..."));
		
		// Chờ cho 1 element được seleted thành công
        // Checkbox/ Radio/ Dropdown Item (Default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        
        
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

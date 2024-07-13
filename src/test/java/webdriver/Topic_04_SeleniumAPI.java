package webdriver;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_SeleniumAPI {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
//        driver = new EdgeDriver();
//        driver = new ChromeDriver();
//        driver = new SafariDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public void TC_01_URL() {
		// Mở ra 1 page URL bất kỳ
		driver.get("https://www.facebook.com/");
		
		// Đóng tab đang thao tác
//		driver.close();
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println(driver.getPageSource());
	}
	
//	@Test
	public void TC_02_Textbox_TextArea() {
		// Mở ra 1 page URL bất kỳ
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("abc@gmail.com");
		driver.navigate().to("https://www.webfx.com/blog/demo/elastic_textarea/");
		driver.findElement(By.xpath("//textarea[@id='foo']")).clear();
		driver.findElement(By.xpath("//textarea[@id='foo']")).sendKeys("automation\ntester");
		sleepInSeconds(5);
		
		
	}
	
//	@Test
	public void TC_03_Textbox_TextArea_GetAttribute() {
		driver.get("https://www.webfx.com/blog/demo/elastic_textarea/");
		String style = driver.findElement(By.xpath("//textarea[@id='foo']")).getAttribute("style");
		System.out.println(style);
	}
	
//	@Test
	public void TC_04_CheckEnable() {
		driver.get("https://www.fahasa.com/customer/account/create");
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-register']")).isEnabled());
	}
	
//	@Test
	public void TC_05_DropDownList() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		Select date = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		date.selectByIndex(3);
		sleepInSeconds(3);
		date.selectByValue("10");
		sleepInSeconds(3);
		date.selectByVisibleText("20");
		sleepInSeconds(3);
		
	}
	
	@Test
	public void TC_06_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("automation tester");
		sleepInSeconds(3);
		alert.accept();
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: automation tester");
		
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickButton).perform();
		sleepInSeconds(3);
	}
	
	@Test
	public void TC_07_DragAndDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallcicle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigcicle = driver.findElement(By.xpath("//div[@id='droptarget']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(smallcicle, bigcicle).perform();
        sleepInSeconds(3);
        Assert.assertEquals("You did great!", bigcicle.getText());
	}
	
	

	@AfterClass
	public void afterClass() {
		// Đóng browser
		driver.quit();
	}
	
	public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

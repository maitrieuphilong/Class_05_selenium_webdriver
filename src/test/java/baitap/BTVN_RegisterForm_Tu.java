package baitap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BTVN_RegisterForm_Tu {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() throws InterruptedException {
		// Mở link
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Flogin%3F");
		
		//Gender
		driver.findElement(By.id("gender-female")).click();
		
		//First name
		driver.findElement(By.id("FirstName")).sendKeys("Tu");
		
		//Last name
		driver.findElement(By.id("LastName")).sendKeys("Tran");
		
		//Date of birth
		Select selectDay =new Select(driver.findElement(By.name("DateOfBirthDay")));
		selectDay.selectByValue("23");
		Select selectMonth =new Select(driver.findElement(By.name("DateOfBirthMonth")));
		selectMonth.selectByValue("10");
		Select selectYear =new Select(driver.findElement(By.name("DateOfBirthYear")));
		selectYear.selectByValue("1997");
		
		//Email
		LocalDateTime now = LocalDateTime.now();
		int nowMinute = now.getMinute();
		int nowSecond = now.getSecond();
		driver.findElement(By.id("Email")).sendKeys("tutran"+ nowMinute + nowSecond + "@gmail.com");
		
		//Company name
		driver.findElement(By.id("Company")).sendKeys("Tiki");
		
		//Newsletter
		WebElement checkbox = driver.findElement(By.id("Newsletter"));
		if (checkbox.isSelected()) {
			checkbox.click();
		}
		
		//Password
		driver.findElement(By.id("Password")).sendKeys("123456@a");
		
		//Confirm password
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456@a");
		
		//Send form
		WebElement button = driver.findElement(By.id("register-button"));
		button.click();
		
		//Check kết quả
		String result = driver.findElement(By.xpath("//div[@class='result']")).getText();
		System.out.println(result);
		Assert.assertEquals("Your registration completed", result);
//		Thread.sleep(5000);
	}
	
	@Test
	public void Drag_n_Drop() throws InterruptedException {
		//Mở link
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		//Action
        WebElement smallcicle = driver.findElement(By.id("draggable"));
        WebElement bigcicle = driver.findElement(By.id("droptarget"));
        Actions action = new Actions(driver);
        action.dragAndDrop(smallcicle, bigcicle).perform();
//        Thread.sleep(3000);
        
        //Check kq
        Assert.assertEquals("You did great!", bigcicle.getText());
	}
	
	@Test
	public void TC_03_Multi_Select() {
		// Mở link
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Actions
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.COMMAND).perform();
		elements.get(1).click();
		elements.get(2).click();
		action.keyUp(Keys.COMMAND).perform();
		sleepInSeconds(3);
	}
	
	public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}



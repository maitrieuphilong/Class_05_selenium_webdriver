package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Relative_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
    public void TC_01_Relative() {
        //Login button
        By loginbuttonBy = By.cssSelector("button.login-button");
        //Remember Me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");
        //Forgot password link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));
        // Password textbox
        By pwtextBox = By.cssSelector("input.password");
        
        WebElement rememberMeTextElement = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginbuttonBy)
                .toRightOf(rememberMeCheckboxBy)
                .toLeftOf(forgotPasswordElement)
                .below(pwtextBox)
                .near(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());
    }
}

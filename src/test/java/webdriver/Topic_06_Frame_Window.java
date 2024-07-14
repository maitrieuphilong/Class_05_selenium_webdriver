package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Frame_Window {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test
    public void TC_01_() {
        // Trang A, domain: formsite.com
        driver.get("");
        // Chua iframe trang B
        // Tu A vao B
        driver.switchTo().frame("");
        driver.findElement(By.cssSelector("")).click();

        // Tu B vao C
        driver.switchTo().frame("");
        driver.findElement(By.cssSelector("")).click();

        // Tu C quay lai B
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("")).click();

        // Tu B quay lai A
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("")).click();
    }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}

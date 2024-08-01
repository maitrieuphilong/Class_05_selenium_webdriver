package learnTestNG;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter_Optional {
	@Parameters("browser")
	@Test
	public void runWithBrowser(@Optional("firefox") String browserName) {
		System.out.println("Run with browser:" + browserName);
	}
}

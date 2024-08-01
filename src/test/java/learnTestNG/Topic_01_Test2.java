package learnTestNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_Test2 {
	@Test
	public void test03() {
		System.out.println("Chạy test case 03");
	}

	@Test
	public void test04() {
		System.out.println("Chạy test case 04");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Chạy before Method TEST 2");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Chạy after Method TEST 2");
	}
}

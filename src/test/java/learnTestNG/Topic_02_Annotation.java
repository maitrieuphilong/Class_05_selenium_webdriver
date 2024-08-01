package learnTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Annotation {
	@Test(groups = "smoke")
	public void testcase1() {
		System.out.println("Chạy Test Case 01");
	}

	@Test
	public void testcase2() {
		System.out.println("Chạy Test Case 02");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Chạy before method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Chạy after method");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Chạy before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Chạy after class");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		System.out.println("Chạy before test");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("Chạy after test");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Chạy before suit");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Chạy after suit");
	}
}

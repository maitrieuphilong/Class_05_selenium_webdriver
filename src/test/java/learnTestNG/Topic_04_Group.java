package learnTestNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Group {

	@Test(groups = "smoke", description = "Ticket Jira QA-11235")
	public void test01() {
		System.out.println("Test case smoke 01");
	}

	@Test(groups = "smoke")
	public void test02() {
		System.out.println("Test case smoke 02");
	}

	@Test(groups = "smoke", enabled = false)
	public void test03() {
		System.out.println("Test case smoke 03");
	}

	@Test(groups = "regression")
	public void test04() {
		System.out.println("Test case regression");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("before class");
	}

}

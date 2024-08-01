package learnTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
	@Test
	public void Test01() {
		String myText = "abc";
		// Assert True a false condition => failed test case, quăng ra message lỗi "myText is error"
		Assert.assertTrue(myText.equals("abx"), "myText is error");
	}
}

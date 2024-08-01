package learnTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Topic_01_Test1 {
	
  @Test
  public void test01() {
	  System.out.println("Chạy test case 01");
  }
  
  @Test
  public void test02() {
	  System.out.println("Chạy test case 02");
  }
  
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Chạy before TEST 1");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Chạy after TEST 1");
  }

}

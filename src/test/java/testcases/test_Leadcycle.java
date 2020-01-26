package testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.utils;

public class test_Leadcycle extends TestBase {

	@Test
	public void test_create_user() throws InterruptedException {
		System.out.println("---------------  TEST Starts: Create user  --------------- ");

		utils UtilFunction = new utils();
		UtilFunction.Login_TestSite(driver);
		Thread.sleep(2000);
		UtilFunction.TestSite_createuser(driver);
		UtilFunction.Logout(driver);
		System.out.println("*********  TEST ENDS ********* ");

	}

}
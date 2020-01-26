package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import base.TestBase;

public class utils extends TestBase {

	public static String Login_username;
	public static String Login_password;

	public static String screenshotPath;
	public static String screenshotName;

	public static void Login_TestSite(WebDriver driver) {
		type("xpath", "username", "Luke");
		type("xpath", "password", "Skywalker");
		click("xpath", "signIn");
	}

	public static void TestSite_createuser(WebDriver driver) throws InterruptedException {
		click("link", "link_create");
		String FirstName = "TestDominika" + utils.generateRandomNumber(111, 999);
		String LastName = "Korol";
		String emailID = FirstName + "@test.com";
		type("xpath", "firstname", FirstName);
		type("xpath", "lastname", LastName);
		type("xpath", "start_date", "2020-11-11");
		type("xpath", "email_address", "test991@gmailcom");
		click("xpath", "button_create_user");
		String full_name = FirstName + " " + LastName;
		Reporter.log("User -->  " + full_name + " is created in the system");
		System.out.println("full_name: " + full_name);
		Thread.sleep(5000);

	}

	public static void Logout(WebDriver driver) {
		click("xpath", "logout_button");
	}

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

	public static int generateRandomNumber(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	// File upload by Robot Class
	public void uploadFileWithRobot(String imagePath) {
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
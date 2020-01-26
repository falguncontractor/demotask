package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	// public static Logger log = Logger.getLogger("initLogger");
	public static WebDriverWait wait;
	public static String browser;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String rootDir = System.getProperty("user.dir");

	@BeforeSuite
	public void setUp() {
		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\resource_data\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\resource_data\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\resource_data\\executables\\chromedriver_79.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\resource_data\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}

	public void clickLocator(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public static void click(String locatorType, String locator) {

		if (locatorType.equalsIgnoreCase("css")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();

		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locatorType.equalsIgnoreCase("link")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
	}

	public String GetTextBy(String locatorType, String location) {

		String Value = null;
		try {
			if (locatorType.equalsIgnoreCase("css")) {
				Value = driver.findElement(By.cssSelector(location)).getText();
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				Value = driver.findElement(By.xpath(location)).getText();
			} else if (locatorType.equalsIgnoreCase("id")) {
				Value = driver.findElement(By.id(location)).getText();
			} else if (locatorType.equalsIgnoreCase("name")) {
				Value = driver.findElement(By.name(location)).getText();
			} else if (locatorType.equalsIgnoreCase("link")) {
				Value = driver.findElement(By.linkText(location)).getText();
			}
		} catch (Exception e) {
			System.out.println("element type - " + locatorType + " - not found : " + location);
		}
		return Value.trim();

	}

	public static void type(String locatorType, String locator, String value) {

		if (locatorType.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		} else if (locatorType.equalsIgnoreCase("link")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		}

	}

	public void typeLocator(String locator, String value) {

		if (locator.endsWith("css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

	}

	static WebElement dropdown;

	public void scroll(WebDriver driver, String type) {
		if (type.equalsIgnoreCase("down")) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0, 500)");
		} else if (type.equalsIgnoreCase("up")) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scorllBy(500, 0)");
		}

	}

	public void select(String locatorType, String locator, String value) {

		if (locatorType.equalsIgnoreCase("css")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("test execution completed !!!");
	}
}
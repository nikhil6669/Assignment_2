package com.assigntest;

import org.testng.annotations.Test;

/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(com.assigntest.ListenerDemo.class)
public class TestCase extends TestBase {
	
	
	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest(String browser) {
		if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("FireFox Browser Starts");
			driver = new FirefoxDriver();
			setDriver(driver);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Chrome Browser Starts");
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\nikhil.bhalerao\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			setDriver(driver);
		}
	}

	@Test
	public void flightBooking() {
		test = extent.createTest("Flight Booking");
		waitImplictly();

		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\config\\config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.get(prop.getProperty("url"));
		// maximum();

		// screenShot();

		String title = driver.getTitle();

		Assert.assertEquals(title, "ORBITZ.com – Best Travel Deals");

		driver.findElement(By.id(prop.getProperty("loc_origin"))).sendKeys(prop.getProperty("input_origin"));
		
		driver.findElement(By.id(prop.getProperty("loc_destination"))).sendKeys(prop.getProperty("input_destination"));
		
		driver.findElement(By.id(prop.getProperty("loc_departing"))).click();
		driver.findElement(By.xpath(prop.getProperty("select_date_departing"))).click();

		driver.findElement(By.id(prop.getProperty("loc_returning"))).click();
		driver.findElement(By.xpath(prop.getProperty("select_date_returning"))).click();

		dorpDowmSelectVisibleText(prop.getProperty("sel_child"), "1");

		dorpDowmSelectIndex(prop.getProperty("sel_child_age"), 7);

		dorpDowmSelectValue(prop.getProperty("sel_adv_opt"), "f");

		driver.findElement(By.id(prop.getProperty("but_search"))).click();

		checkPageIsReady();
		WebElement element = driver.findElement(By.xpath(".//*[@id='rewardsHeader']/a/div"));
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(element));
		// screenShot();
	}

	
	 /* @Test 
	  public void testToFail() {
	  System.out.println("This method to test fail"); Assert.assertTrue(false); 
	  }*/
	 

	@AfterTest
	public void browserClose() {
		driver.close();
	}
}

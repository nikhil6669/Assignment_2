package com.assigntest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {
	public WebDriver driver = null;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void waitImplictly() {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
	}

	public void maximum() {
		driver.manage().window().maximize();
	}

	public void dorpDowmSelectIndex(String element, int i) {
		Select select = new Select(driver.findElement(By.id(element)));
		select.selectByIndex(i);
	}

	public void dorpDowmSelectVisibleText(String element, String text) {
		Select select = new Select(driver.findElement(By.id(element)));
		select.selectByVisibleText(text);
	}

	public void dorpDowmSelectValue(String element, String value) {
		Select select = new Select(driver.findElement(By.id(element)));
		select.selectByValue(value);
	}

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
		}
	}

	public void screenShot() {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File DestFile = new File("D:\\Assignment\\Assignment\\ScreenShots");
			System.out.println(scrFile);
			System.out.println(DestFile);
			FileUtils.copyFileToDirectory(scrFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@BeforeSuite
	public void startReport()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Host Name", "Nikhil");
        extent.setSystemInfo("Environment", "QA - Automation");
        extent.setSystemInfo("User Name", "Nikhil Bhalerao");
         
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }
	
	@AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
     
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
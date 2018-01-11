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

public class TestBase {
	public WebDriver driver = null;

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
}
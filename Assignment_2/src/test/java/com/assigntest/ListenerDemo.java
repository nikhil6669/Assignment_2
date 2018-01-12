package com.assigntest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerDemo extends TestBase implements ITestListener
{
	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("onTestStart"+result.getName());
		//test = extent.createTest(result.getName());
		/*htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
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
        htmlReporter.config().setTheme(Theme.DARK);	*/
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("onTestSuccess");
		/*if(result.getStatus() == ITestResult.SUCCESS)
        {
			test = extent.createTest(result.getName());
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }*/
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("onTestFailure");		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		System.out.println("onTestSkipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) 
	{
		System.out.println("onStart");
	}

	@Override
	public void onFinish(ITestContext context)
	{	
		System.out.println("onFinish");
	}
}

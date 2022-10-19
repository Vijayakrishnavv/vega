package com.vega.pages;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vega.listners.TestListener;
import com.vega.report.ExtentManager;
import com.vega.report.ExtentTestManager;
import com.vega.utility.BrowserFactory;
import com.vega.utility.ConfigDataProvider;
import com.vega.utility.ExcelDataProvider;
import com.vega.utility.Helper;




public class BaseTest  {

	
	public WebDriver driver ;
	public ConfigDataProvider config;
	public ExcelDataProvider excel;

	@BeforeSuite
	public void setUp() {

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
	//	ExtentManager.getInstance();
		
	}

	@BeforeTest
	@Parameters(value = {"browser","appURL"})
	public void BrowserStart(String browser , String appURL ) {
		driver = BrowserFactory.startApplication(driver, browser ,  appURL);
	}

	@AfterSuite
	public void onFinish(ITestContext context) {		
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
	//	ExtentManager.getInstance().flush();
	}
	
	@AfterMethod
	public void garbageCollector() {
		System.runFinalization();
		Runtime.getRuntime().gc();
		System.gc();
	}

	
	//@AfterSuite
	@AfterTest
	public void closeBrowser() {	
		BrowserFactory.closeBrowser(driver);
	}

	@BeforeSuite
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}
	
	


	
	
	@BeforeMethod
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		
	}
	
	@AfterMethod
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		if(result.getStatus()==ITestResult.FAILURE )
		{
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			ExtentTestManager.getTest().fail("<details><summary><b><font color = red>Exception Occured , Click to see details:" + "</font></b></summary>" + exceptionMessage.replaceAll("," ,"<br>") + "</details> \n");
			
		//	ExtentTestManager.startTest(result.getMethod().getMethodName()).fail("<details><summary><b><font color = red>Exception Occured , Click to see details:" + "</font></b></summary>" + exceptionMessage.replaceAll("," ,"<br>") + "</details> \n");

			String path = takeScreenshot(result.getMethod().getMethodName());
			try {
				ExtentTestManager.getTest().fail("<b><font color=red>" + "ScreenShot of Failed" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(Exception e) {
				ExtentTestManager.getTest().fail("Cannot attach screenshot");
			}
			String logText = "<b>Test Method_" + methodName + "Failed </b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			ExtentTestManager.getTest().log(Status.FAIL, m);
		//	ExtentTestManager.getTest().getExtent();
		//	ExtentTestManager.endTest();
		}	
	}
	
	@AfterMethod
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		if(result.getStatus()==ITestResult.SUCCESS)
		{
		/*	String path = takeScreenshot(result.getMethod().getMethodName());
			try {
				ExtentTestManager.getTest().pass("<b><font color=green>" + "ScreenShot of Passed" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			//	ExtentTestManager.startTest(result.getMethod().getMethodName()).pass("<b><font color=green>" + "ScreenShot of Passed" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(Exception e) {
				ExtentTestManager.getTest().pass("Cannot attach screenshot");
			}*/
			String logText = "<b>Test Method_" + methodName + "Success </b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			ExtentTestManager.getTest().log(Status.PASS, m);
		//	ExtentTestManager.getTest().getExtent();
		//	ExtentTestManager.endTest();
		}
	}
	
	@AfterMethod
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		if(result.getStatus()==ITestResult.SKIP)
		{
		/*	String path = takeScreenshot(result.getMethod().getMethodName());
			try {
				ExtentTestManager.getTest().skip("<b><font color=gray>" + "ScreenShot of Skipped" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			//	ExtentTestManager.startTest(result.getMethod().getMethodName()).skip("<b><font color=gray>" + "ScreenShot of Skipped" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(Exception e) {
				ExtentTestManager.getTest().skip("Cannot attach screenshot");
			}*/
			String logText = "<b>Test Method_" + methodName + "Skipped </b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREY);
			ExtentTestManager.getTest().log(Status.SKIP, m);
		//	ExtentTestManager.getTest().getExtent();
		//	ExtentTestManager.endTest();
		}	
	}
	
	public String takeScreenshot(String methodName) {
		String fileName = getScreenShotName(methodName);
		String dir = System.getProperty("user.dir") + "/screenshots/" ;
		new File(dir).mkdirs();
		String path = dir + fileName ;
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot , new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenShotName(String methodName) {
		Date d = new Date(); 
		String fileName = methodName + "_" + d.toString().replace(":","_").replace(" ","_") + ".png" ;
		return fileName;
	}
}







/*
		@AfterMethod
		public void tearDownMethod(ITestResult result)
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				//	result.getMethod().getMethodName(); 
				Helper.captureScreenshotOnFailure(driver , result.getMethod().getMethodName());
			}
		}


		@AfterMethod
		public void screenOnSuccess(ITestResult result) {
			if(result.getStatus()==ITestResult.SUCCESS)
			{
				result.getMethod().getMethodName(); 
				Helper.captureScreenshotOnSuccess(driver , result.getMethod().getMethodName());
			}
		}

 */

/*

		@AfterMethod
		public void onTestSuccess(ITestResult result) {
			System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");

			if(result.getStatus()==ITestResult.SUCCESS)
			{
				result.getMethod().getMethodName(); 
				Helper.captureScreenshotOnSuccess(driver , result.getMethod().getMethodName());
				ExtentTestManager.getTest().log(Status.PASS, "Test passed");
			}


		}



		@AfterMethod
		public void onTestFailure(ITestResult result) {
			System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");

			if(result.getStatus()==ITestResult.FAILURE)
			{
				//	result.getMethod().getMethodName(); 
				Helper.captureScreenshotOnFailure(driver , result.getMethod().getMethodName());

				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			}

		}
 */

/*	
		@AfterSuite
		public void onFinish(ITestContext context) {
			System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
		}



		@BeforeMethod
		public void onTestStart(ITestResult result) {
			System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
			ExtentTestManager.startTest(result.getMethod().getMethodName());
		}



		@AfterMethod
		public void onTestSkipped(ITestResult result) {
			System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");

			if(result.getStatus()==ITestResult.SKIP)
			{
				//	result.getMethod().getMethodName(); 
				//Helper.captureScreenshotOnFailure(driver , result.getMethod().getMethodName());

				ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
			}
		}

*/

 

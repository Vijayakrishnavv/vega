package com.vega.listners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.vega.report.ExtentManager;
import com.vega.report.ExtentTestManager;

public class TestListener implements ITestListener {
	/*
	public WebDriver driver ;

	public void onTestSuccess(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}
		
	}

	public void onTestFailure(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
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
			ExtentTestManager.getTest().getExtent();
			ExtentTestManager.endTest();
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

	public void onTestSkipped(ITestResult result) {
		if(result.getStatus()==ITestResult.SKIP) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
	}
	
	
	*/
}
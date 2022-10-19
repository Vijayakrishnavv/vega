package com.vega.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory 
{
	
	
	
	@Parameters(value= {"browser","appURL"})
	
	public static WebDriver startApplication(WebDriver driver, String browser, 	String appURL) {
		if (browser.equals("Chrome")) {
			
			if(driver==null) {
				
	//		WebDriverManager.chromedriver().setup();
	//	    driver = new ChromeDriver();
				
			    // before using the web driver manager code
			    
				try {
				//	System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver_94.exe");
					WebDriverManager.chromedriver().setup();
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--whitelist-ip *");
					chromeOptions.addArguments("headless");
					chromeOptions.addArguments("start-maximized");
					chromeOptions.addArguments("--window-size=1920,1080");
					chromeOptions.addArguments("--proxy-server='direct://'");
					chromeOptions.addArguments("--proxy-bypass-list=*");		
					driver = new ChromeDriver(chromeOptions);
					driver.manage().deleteAllCookies();}
				catch(Exception e){
					e.printStackTrace();
					e.getMessage();
					e.getCause();
					e.getLocalizedMessage();
				}
			}
		

		} else if (browser.equals("Firefox")) {
			
			if(driver == null) {
				
		//	WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver();
				// before using the web driver manager code
				try {
				 // System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
					WebDriverManager.firefoxdriver().setup();
				  driver = new FirefoxDriver();
				  driver.manage().deleteAllCookies();}
				catch(Exception e){
					e.printStackTrace();
					e.getMessage();
					e.getCause();
					e.getLocalizedMessage();
				}
				
			}
			
			
		} else {
			System.out.println("We do not support this browser");

		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Get the URL or hit the URL
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;

	}
	
	
	public static void closeBrowser(WebDriver driver) {

		driver.close();
		

	}

}

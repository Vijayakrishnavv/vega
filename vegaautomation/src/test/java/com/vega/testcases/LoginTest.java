package com.vega.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vega.pages.BaseTest;
import com.vega.pages.ChargingPage;
import com.vega.pages.LoginPage;
import com.vega.report.ExtentTestManager;




public class LoginTest extends BaseTest{


	//LoginPage lpage;

	WebDriverWait wait ;

//	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void verifyLoginTitle(){
		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		lpage.getTitleOfPage();
		SoftAssert softassert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site managemen");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertAll();
	}

	// valid username and password
	@Test(priority = -1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void login_with_Valid_Username_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);			
			lpage.selectEnglishLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lpage.clearLocators();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//	lpage.loginToVega1();
			lpage.loginToVega(excel.getStringData("LoginSheet", 3, 0), excel.getStringData("LoginSheet", 3, 1));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			SoftAssert softassert = new SoftAssert();
			wait.until(ExpectedConditions.titleContains("Site management"));
			softassert.assertEquals(driver.getTitle(), "Site management"); 
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
			hpage.clickOnLogout();
			WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.visibilityOf(loginbtn));
			boolean loginBtnPresent = loginbtn.isDisplayed();
			softassert.assertEquals(loginBtnPresent, true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		softassert.assertAll();
	}

	// invalid username and password
	@Test(priority = 2, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void login_with_Invalid_Username_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			lpage.selectEnglishLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			lpage.clearLocators();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			SoftAssert softassert = new SoftAssert();
			lpage.loginToVega(excel.getStringData("LoginSheet", 0, 0), excel.getStringData("LoginSheet", 0, 1));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String errorTextForInvalidCredentials = driver.findElement(By.xpath("//span[contains(text(),'Your username or password is incorrect!')]")).getText();
			softassert.assertEquals(errorTextForInvalidCredentials, "Your username or password is incorrect!");
		softassert.assertAll();
	}

	//With only user name
	@Test(priority = 3, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void username_without_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			lpage.selectEnglishLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			lpage.clearLocators();
			SoftAssert softassert = new SoftAssert();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.loginToVegaWithUsername(excel.getStringData("LoginSheet", 1, 0));
			String errorTextForPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']")).getText();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			softassert.assertEquals(errorTextForPassword, "Please enter your password");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertAll();
	}

	// With only user password
	@Test(priority = 4, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void password_without_username() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			lpage.selectEnglishLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			lpage.clearLocators();
			SoftAssert softassert = new SoftAssert();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.loginToVegaWithPassword(excel.getStringData("LoginSheet", 2, 0));
			String errorTextForUsername = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']")).getText();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			softassert.assertEquals(errorTextForUsername, "Please enter your username");
		softassert.assertAll();
	}

	// click on login without username and password


	@Test(priority = 5, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void click_on_login_without_username_password()  {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			lpage.clearLocators();
			lpage.selectEnglishLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.loginToVega();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			SoftAssert softassert = new SoftAssert();
			WebElement errorTextOfUsername = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']"));
			wait.until(ExpectedConditions.visibilityOf(errorTextOfUsername));
			WebElement errorTextOfPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']"));
			wait.until(ExpectedConditions.visibilityOf(errorTextOfPassword));
			String errorTextForUsername = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']")).getText();
			String errorTextForPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']")).getText();
			softassert.assertEquals(errorTextForUsername, "Please enter your username");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			softassert.assertEquals(errorTextForPassword, "Please enter your password");
		softassert.assertAll();
	}
	 
	// change language to Hebrew and click on login without username and password
	@Test(priority = 8, retryAnalyzer = com.vega.listners.RetryAnalyzer.class ,  groups = "Not implemented in Firefox")
	public void He_click_on_login_without_username_password() {
		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.selectHebrewLanguage();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lpage.clearLocators();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.loginToVega();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		SoftAssert softassert = new SoftAssert();
		WebElement errorTextOfUsername = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']"));
		wait.until(ExpectedConditions.visibilityOf(errorTextOfUsername));
		WebElement errorTextOfPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']"));
		wait.until(ExpectedConditions.visibilityOf(errorTextOfPassword));
		String errorTextForUsername = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']")).getText();
		String errorTextForPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']")).getText();
		softassert.assertEquals(errorTextForUsername, excel.getStringData("LoginSheet", 9, 1));
		softassert.assertEquals(errorTextForPassword, excel.getStringData("LoginSheet", 10, 1));
		softassert.assertAll();
	}



	// change language to Hebrew and login without entering username
	@Test(priority = 9, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups = "Not implemented in Firefox")
	public void he_password_without_username() {
		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.selectHebrewLanguage();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lpage.clearLocators();
		SoftAssert softassert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.loginToVegaWithPassword(excel.getStringData("LoginSheet", 2, 0));
		String errorTextForPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='UserName']")).getText();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(errorTextForPassword, excel.getStringData("LoginSheet", 9, 1));
		softassert.assertAll();
	}


	// change language to Hebrew and login without entering password	
	@Test(priority = 10, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups = "Not implemented in Firefox")
	public void he_username_without_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.selectHebrewLanguage();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			lpage.clearLocators();
			SoftAssert softassert = new SoftAssert();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.loginToVegaWithUsername(excel.getStringData("LoginSheet", 1, 0));
			String errorTextForPassword = driver.findElement(By.xpath("//span[@data-valmsg-for='Password']")).getText();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			softassert.assertEquals(errorTextForPassword, excel.getStringData("LoginSheet", 10, 1) );
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertAll();
	}

	
	// change language to Hebrew and login with invalid username and password
	@Test(priority = 11, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups =  "Not implemented in Firefox"  )
	public void he_login_with_Invalid_Username_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.selectHebrewLanguage();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lpage.clearLocators();
			SoftAssert softassert = new SoftAssert();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.loginToVega(excel.getStringData("LoginSheet", 0, 0), excel.getStringData("LoginSheet", 0, 1));
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement errorText = driver.findElement(By.xpath("//input[@type='submit']//preceding::div//*[contains(text(),'שגיאה בשם המשתמש או הססמא')]"));
			wait.until(ExpectedConditions.visibilityOf(errorText));
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String errorTextForInvalidCredentials = errorText.getText();
			softassert.assertEquals(errorTextForInvalidCredentials, excel.getStringData("LoginSheet", 11, 1));
		softassert.assertAll();
	}
	
	
	
	// change language to Hebrew and login with valid username and password
	
	@Test(priority = 99, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups = "Not implemented in Firefox")
	public void he_login_with_Valid_Username_password() {
			LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			lpage.selectHebrewLanguage();


			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			lpage.pageRefresh();
			lpage.clearLocators();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//	lpage.loginToVega1();
			lpage.loginToVega(excel.getStringData("LoginSheet", 3, 0), excel.getStringData("LoginSheet", 3, 1));

		//	Alert alert = driver.switchTo().alert();
		//	String alertText = alert.getText();
		//	System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
		//	alert.accept();


			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.titleContains("Site management"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
			hpage.clickOnLogout();

			WebElement loginbtn = driver.findElement(By.xpath("//input[@type='submit']"));
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.visibilityOf(loginbtn));
			SoftAssert softassert = new SoftAssert();
			boolean loginBtnPresent = loginbtn.isDisplayed();
			softassert.assertEquals(loginBtnPresent, true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		softassert.assertAll();
		
	}
	
	

}

package com.vega.testcases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vega.pages.BaseTest;
import com.vega.pages.ChargingPage;
import com.vega.pages.DashboardPage;
import com.vega.pages.LoginPage;

import io.restassured.path.json.JsonPath;

public class DasboardPageTest extends BaseTest {
	
	WebDriverWait wait ;

	

	SoftAssert softassert = new SoftAssert();
	
	
	
	


	//	Login to the application
	@Test(priority = 1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void login_with_Valid_Username_password() {

		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		//	lpage.clearLocators();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	lpage.loginToVega1();
		lpage.loginToVega(excel.getStringData("LoginSheet", 3, 0), excel.getStringData("LoginSheet", 3, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site management"); 
		softassert.assertAll();

	}



	//Logout scenario 

	@Test(priority = 999 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void logout_Of_User()
	{
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.clickOnLogout();

		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
		boolean loginBtnPresent = loginbtn.isDisplayed();
		softassert.assertEquals(loginBtnPresent, true);

		softassert.assertAll();

	}
	
	//Dashboard test
	
	@Test(priority = 2 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void dashboardTabView() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement dashboardHeading = driver.findElement(By.xpath("//div[@id='divMainContent']//following-sibling::h3[contains(text(),'Dashboard')]"));
		String dashboardHeadingText = dashboardHeading.getText();
		softassert.assertEquals(dashboardHeadingText, "Dashboard");
		
		softassert.assertAll();
	}
	
	@Test(priority = 3 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void chargeSpotInfoDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement chargeSpotHeading = driver.findElement(By.xpath("//span[contains(text(),'Charge spot info')]"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(chargeSpotHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
		int chargerBoxInfoTotal =  json.getInt("ChargeBoxInfo.TotalCount");
		int chargerBoxInfoOffline =  json.getInt("ChargeBoxInfo.Offline");
		int chargerBoxInfoOnline =  json.getInt("ChargeBoxInfo.Online");
		System.out.println("CBInfo of Total records :" +chargerBoxInfoTotal);
		System.out.println("CBInfo of Offline :" +chargerBoxInfoOffline);
		System.out.println("CBInfo of Online :" +chargerBoxInfoOnline);
		
		// Finding the total number of charge box info which are online and Offline 
		
		String chargeBoxUITotal = driver.findElement(By.xpath("//span[@id='totalChargeBoxInfo']")).getText();
		int converttotalText = Integer.valueOf(chargeBoxUITotal);
		
		softassert.assertEquals( converttotalText , json.getInt("ChargeBoxInfo.TotalCount"));
		
		softassert.assertAll();
	}
	
	@Test(priority = 4 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void failedTransactionDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement failedTransactionHeading = driver.findElement(By.xpath("//span[contains(text(),'Failed transactions')]"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(failedTransactionHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
		//Finding total failed transaction per day
		
		String failedUITotal = driver.findElement(By.xpath("//p[contains(text(),'Total')]//following-sibling::span[@id='totalFailedTransaction']")).getText();
		int failedUITotalText = Integer.valueOf(failedUITotal);	
		softassert.assertEquals( failedUITotalText , json.getInt("TransactionInfo.TotalFailedTransactionsByDay"));
		
		//Finding invalid failed transaction per day
		
		String invalidTransctionUITotal = driver.findElement(By.xpath("//span[@id='totalInvalidTransaction']")).getText();
		int invalidTransctionUITotalText = Integer.valueOf(invalidTransctionUITotal);	
		softassert.assertEquals( invalidTransctionUITotalText , json.getInt("TransactionInfo.TotalInvalidTransactionsByDay"));
		
		softassert.assertAll();
	}
	
	@Test(priority = 5 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void activeTransactionDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement activeTransactionHeading = driver.findElement(By.xpath("//span[@id='totalActiveTransaction']"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(activeTransactionHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
	
		//Finding active transaction details
		
		String activeTransctionUITotal = driver.findElement(By.xpath("//span[@id='totalActiveTransaction']")).getText();
		int activeTransctionUITotalText = Integer.valueOf(activeTransctionUITotal);	
		softassert.assertEquals( activeTransctionUITotalText , json.getInt("TransactionInfo.ActiveTransaction"));
		
		softassert.assertAll();
	}
	
	
	@Test(priority = 6 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void reservationDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement reservationHeading = driver.findElement(By.xpath("//span[@id='totalReservation']"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(reservationHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
	
		//Finding RESERVATIONS details
		
		String reservationHeadingUI = driver.findElement(By.xpath("//span[@id='totalReservation']")).getText();
		int reservationHeadingUITotalText = Integer.valueOf(reservationHeadingUI);	
		softassert.assertEquals( reservationHeadingUITotalText , json.getInt("ReservationInfo.TotalReservations"));
		
		softassert.assertAll();
	}
	
	
	@Test(priority = 7 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void chargingProfileDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement chargingProfileHeading = driver.findElement(By.xpath("//span[@id='totalChargingProfile']"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(chargingProfileHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
	
		//Finding RESERVATIONS details
		
		String chargingProfileHeadingUI = driver.findElement(By.xpath("//span[@id='totalChargingProfile']")).getText();
		int chargingProfileUITotalText = Integer.valueOf(chargingProfileHeadingUI);	
		softassert.assertEquals( chargingProfileUITotalText , json.getInt("ChargingProfileInfo.TotalChargingProfiles"));
		
		softassert.assertAll();
	}
	
	
	@Test(priority = 8 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void connectorInfoDetails() {
		
		DashboardPage db = PageFactory.initElements(driver, DashboardPage.class);
		
		db.clickOnDashboardTab();
		
		WebElement connectorInfoHeading = driver.findElement(By.xpath("//span[contains(text(),'Connector info')]"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(connectorInfoHeading));
		
		
		Map<String , String> headers = new HashMap<String, String>();
		headers.put("accept","application/json");
		//headers.put("UserId","2512");
		headers.put("AccessToken", "b3d78127-fdc5-48c4-a98d-b79ad9f8ff60");

		String s =given()
				.headers(headers)
				.when()
				.get("http://ocpp2.gnrgy.com:1020/sirius/portal/Dashboard/Status?VendorId=2")
				.thenReturn()
				.asString() ;


		JsonPath json = new JsonPath(s);
		
		int connectorInfoTotal =  json.getInt("ConnectorInfo.TotalCount");
		int connectorInfoAvailable =  json.getInt("ConnectorInfo.Available");
		int connectorInfoUnAvailable =  json.getInt("ConnectorInfo.Unavailable");
		int connectorInfoFaulted =  json.getInt("ConnectorInfo.Faulted");
		int connectorInfoCharging =  json.getInt("ConnectorInfo.Charging");
		int connectorInfoOccupied =  json.getInt("ConnectorInfo.Occupied");
		int connectorInfoReserve =  json.getInt("ConnectorInfo.Reserved");
		int connectorInfoOthers =  json.getInt("ConnectorInfo.Others");
		System.out.println("Connector info Total records :" +connectorInfoTotal);
		System.out.println("Connector info available records :" +connectorInfoAvailable);
		System.out.println("Connector info unavailable records :" +connectorInfoUnAvailable);
		System.out.println("Connector info faulted records :" +connectorInfoFaulted);
		System.out.println("Connector info charging records :" +connectorInfoCharging);
		System.out.println("Connector info occupied records :" +connectorInfoOccupied);
		System.out.println("Connector info reserve records :" +connectorInfoReserve);
		System.out.println("Connector info others records :" +connectorInfoOthers);
		
		
		// Finding the total number of connector info  
		
		String connectorUITotal = driver.findElement(By.xpath("//span[@id='totalConnectorInfo']")).getText();
		int connectorTotalText = Integer.valueOf(connectorUITotal);
		
		softassert.assertEquals( connectorTotalText , json.getInt("ConnectorInfo.TotalCount"));
		
		softassert.assertAll();
	}
	
	
	
	
	
	

}

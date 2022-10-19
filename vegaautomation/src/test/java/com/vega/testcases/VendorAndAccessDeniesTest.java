package com.vega.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vega.pages.BaseTest;
import com.vega.pages.ChargingPage;
import com.vega.pages.LoginPage;
import com.vega.pages.VendorAndAccessDeniedPage;

public class VendorAndAccessDeniesTest extends BaseTest {
	
	
	WebDriverWait wait ;


	SoftAssert softassert = new SoftAssert();
	
	
	//	Login to the application as Gnrgy vendor 
	@Test(priority = 1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups= {"sanity"})
	public void login_with_GNRGY_vendor() {

		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		lpage.clearLocators();
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.loginToVega(excel.getStringData("VendorBasedUserDetailsSheet", 0, 0), excel.getStringData("VendorBasedUserDetailsSheet", 0, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site management");
	
		hpage.clickOnLogout();
		
		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
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
	
	
//	Login to the application as talcar vendor without rights
	@Test(priority = 2, retryAnalyzer = com.vega.listners.RetryAnalyzer.class ,  groups= {"sanity"})
	public void login_with_other_vendor() {

		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		lpage.clearLocators();
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.loginToVega(excel.getStringData("VendorBasedUserDetailsSheet", 1, 0), excel.getStringData("VendorBasedUserDetailsSheet", 1, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site management");
	
		VendorAndAccessDeniedPage vad = PageFactory.initElements(driver, VendorAndAccessDeniedPage.class);	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		vad.isAccessDenied();
		vad.isPermissionTextDisplayed();
		boolean permissionheader = driver.findElement(By.xpath("//p[contains(text(),'Access denied')]")).isDisplayed();
		softassert.assertEquals(permissionheader, true);
		boolean permissionText = driver.findElement(By.xpath("//p[contains(text(),'You don')]")).isDisplayed();
		softassert.assertEquals(permissionText, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.clickOnLogout();
		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
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
	
	
	
//	Login to the application as other  vendor which doesn't have rights 
	@Test(priority = 3, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void login_with_other_vendor_without_sites() {

		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		lpage.clearLocators();
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		lpage.loginToVega(excel.getStringData("VendorBasedUserDetailsSheet", 2, 0), excel.getStringData("VendorBasedUserDetailsSheet", 2, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site management");
		VendorAndAccessDeniedPage vad = PageFactory.initElements(driver, VendorAndAccessDeniedPage.class);	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		vad.isChargingStationDisplayed();
		vad.isChargingTransactionDisplayed();
		vad.isDashboardDisplayed();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean chargingstation = driver.findElement(By.id("navChargingStationId")).isDisplayed();
		softassert.assertEquals(chargingstation, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean chargingtransaction = driver.findElement(By.id("navChargerTransactionId")).isDisplayed();
		softassert.assertEquals(chargingtransaction, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean noresultText = driver.findElement(By.xpath("//span[contains(text(),'Charging station info')]")).isDisplayed();
		softassert.assertEquals(noresultText, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.clickOnLogout();
		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
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
	

	
	
//	Login to the application as gnrgy vendor to display customer based sites , this user has customer id 6569 
	@Test(priority = 4, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void login_with_gnrgy_vendor_to_display_customer_based_sites() {

		LoginPage lpage = PageFactory.initElements(driver, LoginPage.class);
		lpage.clearLocators();
		
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		lpage.loginToVega(excel.getStringData("VendorBasedUserDetailsSheet", 3, 0), excel.getStringData("VendorBasedUserDetailsSheet", 3, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(driver.getTitle(), "Site management");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		VendorAndAccessDeniedPage vad = PageFactory.initElements(driver, VendorAndAccessDeniedPage.class);	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		vad.isChargingStationDisplayed();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean chargingstation = driver.findElement(By.id("navChargingStationId")).isDisplayed();
		softassert.assertEquals(chargingstation, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		vad.isChargingTransactionDisplayed();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean chargingtransaction = driver.findElement(By.id("navChargerTransactionId")).isDisplayed();
		softassert.assertEquals(chargingtransaction, true);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		vad.clickChargingStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement siteDisplayed = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'Gnrgy IN Office')]"));
		String siteText = siteDisplayed.getText();
		softassert.assertEquals(siteText, "Gnrgy IN Office - 2");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		hpage.clickOnLogout();
		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
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

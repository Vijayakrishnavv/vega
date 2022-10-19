package com.vega.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vega.pages.BaseTest;
import com.vega.pages.ChargingPage;
import com.vega.pages.LoginPage;
import com.vega.report.ExtentTestManager;

public class ChargingPageTest extends BaseTest {
	
	

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
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean loginBtnPresent = loginbtn.isDisplayed();
		softassert.assertEquals(loginBtnPresent, true);

		softassert.assertAll();



	}




	//Check the default select elements and validate the result
	@Test(priority = 2 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void default_elements_in_chargingStation() {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.check_default_selected_elements();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String chargerStationText= driver.findElement(By.id("navChargingStationId")).getText();
		softassert.assertEquals(chargerStationText,"Charging stations");	
		softassert.assertAll();


	}




	//Select private charger and validate the customer name for private charger 
	@Test(priority = 3 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void select_Private_Charger() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_private_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement customerTextToPresent = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//tr//td[contains(text(),'rockstar demo')]"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String customerText = customerTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(customerTextToPresent));
		softassert.assertEquals(customerText,"rockstar demo");
		hpage.private_charger_siteDetails();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement chargerID = driver.findElement(By.xpath("//p[@id='ChargeBoxSerialNumber_FLS11120889']"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "FLS11120889");
		softassert.assertAll();


	}



	//Select public charger and validate the customer name for private charger 
	@Test(priority = 4 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void select_Public_Charger() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.search_public_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 1));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement customerTextToPresent = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//tr//td[contains(text(),'Yogeswaran Kamalakannan')]"));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		String customerText = customerTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(customerTextToPresent));
		softassert.assertEquals(customerText,"Yogeswaran Kamalakannan");
		hpage.public_charger_siteDetails();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement chargerID=driver.findElement(By.xpath("//p[@id='ChargeBoxSerialNumber_GFT20150030']"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "GFT20150030"); 
		softassert.assertAll();


	}



	//Select business charger and validate the customer name for business charger 
	@Test(priority = 5 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void select_Business_Charger() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 2));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement customerTextToPresent = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//tr//td[contains(text(),'demoshwetha chargertesting')]"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		String customerText = customerTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(customerTextToPresent));
		softassert.assertEquals(customerText,"demoshwetha chargertesting");
		hpage.business_charger_siteDetails();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement chargerID = driver.findElement(By.id("ChargeBoxSerialNumber_HMCNF2000587"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "HMCNF2000587");
		softassert.assertAll();

	}



	//test by entering valid reference code , private type charger and validate the result 
	@Test(priority = 6 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void search_by_valid_referenceCode() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_referenceCode(excel.getStringData("HomePageSheet", 0, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 2));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement customerTextToPresent = driver.findElement(By.xpath("//tr//td[contains(text(),'demoshwetha chargertesting')]"));
		String customerText = customerTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(customerTextToPresent));
		softassert.assertEquals(customerText,"demoshwetha chargertesting");
		WebElement referenceTextToPresent = driver.findElement(By.xpath("//tr//td[contains(text(),'GN*IN*94')]"));
		String referenceText = referenceTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(referenceTextToPresent));
		softassert.assertEquals(referenceText,"GN*IN*94");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		hpage.business_charger_siteDetails();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement chargerID = driver.findElement(By.id("ChargeBoxSerialNumber_HMCNF2000587"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "HMCNF2000587");
		softassert.assertAll();

	}



	//test by entering invalid reference code and validate the result
	@Test(priority = 7 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_by_invalid_referenceCode() {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_referenceCode(excel.getStringData("HomePageSheet", 0, 2));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();

	}





	//Test by entering valid station name , private type charger and validate whether valid name is searched 
	@Test(priority = 8 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void search_by_valid_stationName() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_stationName(excel.getStringData("HomePageSheet", 1, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 4));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement customerTextToPresent = driver.findElement(By.xpath("//tr//td[contains(text(),'Gnrgy IL Office')]"));
		String customerText = customerTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(customerTextToPresent));
		softassert.assertEquals(customerText,"Gnrgy IL Office");
		WebElement nameTextToPresent = driver.findElement(By.xpath("//tr//td[contains(text(),'Gnrgy IL Office')]"));
		String nameText = nameTextToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(nameTextToPresent));
		softassert.assertEquals(nameText,"Gnrgy IL Office");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		hpage.all_charger_siteDetails();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement chargerID = driver.findElement(By.id("ChargeBoxId_GFT20150030"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "GFT20150030");
		softassert.assertAll();



	}



	//Test by entering invalid station name and validate the results 
	@Test(priority = 9 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_by_invalid_stationName() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_stationName(excel.getStringData("HomePageSheet", 1, 2));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();

	}


	//Test by entering valid station address , business type charger and validate whether valid address is searched 
	@Test(priority = 10 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void search_by_valid_stationAddress() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_stationAddress(excel.getStringData("HomePageSheet", 2, 1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement stationAddressToPresent = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'GN*IL*6')]//following::span[1]"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String stationText = stationAddressToPresent.getText();
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(stationAddressToPresent));
		softassert.assertEquals(stationText,"26 Raziel Street, Ramat Gan, Israel - , Israel");	
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		hpage.search_By_Station_charger_siteDetails();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement chargerID = driver.findElement(By.id("ChargeBoxSerialNumber_GT0000000129"));
		wait.until(ExpectedConditions.visibilityOf(chargerID));
		String chargerName = chargerID.getText();
		softassert.assertEquals(chargerName, "GT0000000129");
		softassert.assertAll();		

	}

	//Test by entering invalid station address and validate the same
	@Test(priority = 11 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_by_invalid_stationAddress() throws Exception {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_by_stationAddress(excel.getStringData("HomePageSheet", 2, 2));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	} 




	//Test by clicking on more filter button and default locators selected
	@Test(priority = 12 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void click_On_MoreFilters() {

		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		WebElement customerNameRadio = driver.findElement(By.id("MoreFilterOptName"));
		boolean customerNamebuttonS = driver.findElement(By.id("Owner")).isSelected();
		//	boolean customerNameRadioS = driver.findElement(By.id("MoreFilterOptName")).isSelected();
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		wait.until(ExpectedConditions.visibilityOf(customerNameRadio));
		softassert.assertEquals(customerNamebuttonS, true);
		//	softassert.assertEquals(customerNameRadioS, true);
		softassert.assertAll();	

	}


	//Test by searching the valid customer name in more filter
	@Test(priority = 13 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_CustomerName_In_MoreFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithName(excel.getStringData("HomePageSheet", 3, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement customerKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(customerKey));
		String customerVerify = customerKey.getText();
		softassert.assertEquals(customerVerify, "rockstar demo");
		try {
			hpage.click_On_CustomerName();
			Thread.sleep(5000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement customerNameDisplayed = driver.findElement(By.xpath("//div[contains(text(),'rockstar demo')]"));
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customerNameDisplayed));
		String customerNameText = customerNameDisplayed.getText();
		softassert.assertEquals(customerNameText , "rockstar demo");
		WebElement outletDetails = driver.findElement(By.id("outletsArrowFLS11120889"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);
		softassert.assertAll();

	}

	//Test by searching the invalid customer name in more filter
	@Test(priority = 14 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_CustomerName_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithName(excel.getStringData("HomePageSheet", 3, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}


	//Test by searching the valid customer address in more filter
	@Test(priority = 15 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_CustomerAddress_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_public_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithAddress(excel.getStringData("HomePageSheet", 4, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement customerKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IT*92')]//following::td[5]"));
		wait.until(ExpectedConditions.visibilityOf(customerKey));
		String customerVerify = customerKey.getText();
		softassert.assertEquals(customerVerify, excel.getStringData("HomePageSheet", 4, 3));
		try {
			hpage.click_On_CustomerAddress();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement customerAddressDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Carpenedolo')]"));
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customerAddressDisplayed));
		String customerAddressText = customerAddressDisplayed.getText();
		softassert.assertEquals(customerAddressText ,excel.getStringData("HomePageSheet", 4, 3) );
		WebElement outletDetails = driver.findElement(By.id("outletsArrowGT0000000118"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);	
		softassert.assertAll();

	}

	//Test by searching the invalid customer address in more filter
	@Test(priority = 16 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_CustomerAddress_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithAddress(excel.getStringData("HomePageSheet", 4, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}



	//Test by searching the valid customer email in more filter
	@Test(priority = 17 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_CustomerEmail_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 1));
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithEmail(excel.getStringData("HomePageSheet", 5, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement customerKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IL*5')]//following::td[5]"));
		wait.until(ExpectedConditions.visibilityOf(customerKey));
		String customerVerify = customerKey.getText();
		softassert.assertEquals(customerVerify, "yogeswarank@gnrgy.com");
		try {
			hpage.click_On_CustomerEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement customerAddressDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Email:yogeswarank@gnrgy.com')]"));
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customerAddressDisplayed));
		String customerAddressText = customerAddressDisplayed.getText();
		softassert.assertEquals(customerAddressText , "Email:yogeswarank@gnrgy.com");
		WebElement outletDetails = driver.findElement(By.id("outletsArrowGFT20150030"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);	
		softassert.assertAll();

	}

	//Test by searching the invalid customer email in more filter
	@Test(priority = 18 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_CustomerEmail_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithEmail(excel.getStringData("HomePageSheet", 5, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}


	//Test by searching the valid customer Mobile number in more filter
	@Test(priority = 19 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_CustomerMobile_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithMobile(excel.getStringData("HomePageSheet", 6, 3),excel.getStringData("HomePageSheet", 6, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement customerKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IL*46')]//following::td[5]"));
		wait.until(ExpectedConditions.visibilityOf(customerKey));
		String customerVerify = customerKey.getText();
		softassert.assertEquals(customerVerify, "+972-543383000");
		try {
			hpage.click_On_CustomerPhone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement customerAddressDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Mob:+972-543383000')]"));
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customerAddressDisplayed));
		String customerAddressText = customerAddressDisplayed.getText();
		softassert.assertEquals(customerAddressText , "Mob:+972-543383000");
		WebElement outletDetails = driver.findElement(By.id("outletsArrowHMCNF2000696"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);	
		softassert.assertAll();


	}

	//Test by searching the invalid customer mobile number in more filter
	@Test(priority = 20 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_CustomerMobile_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_customerButtonwithMobile(excel.getStringData("HomePageSheet", 6, 3),excel.getStringData("HomePageSheet", 6, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}




	//Test by searching the valid contact name in more filter
	@Test(priority = 21 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_ContactName_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_contactButtonwithName(excel.getStringData("HomePageSheet", 7, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement contactKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(contactKey));
		String contactVerify = contactKey.getText();
		softassert.assertEquals(contactVerify, excel.getStringData("HomePageSheet", 7, 1));
		try {
			hpage.click_On_ContactName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement contactNameDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Contact details')]//following::div[contains(text(),'Yogeswaran Kamalakannan')]"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(contactNameDisplayed));
		String customerNameText = contactNameDisplayed.getText();
		softassert.assertEquals(customerNameText , "Yogeswaran Kamalakannan");
		WebElement outletDetails = driver.findElement(By.id("outletsArrowFLS11120889"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);
		softassert.assertAll();

	}


	//Test by searching the invalid contact name in more filter
	@Test(priority = 22 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_ContactName_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		hpage.select_contactButtonwithName(excel.getStringData("HomePageSheet", 7, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}




	//Test by searching the valid contact address in more filter
	@Test(priority = 23 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_ContactAddress_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_contactButtonwithAddress(excel.getStringData("HomePageSheet", 8, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement contactKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]"));
		wait.until(ExpectedConditions.visibilityOf(contactKey));
		String contactVerify = contactKey.getText();
		softassert.assertEquals(contactVerify, excel.getStringData("HomePageSheet", 8, 3));
		try {
			hpage.click_On_ContactAddress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement contactAddressDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Contact details')]//following::div[contains(text(),'Krm')]"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(contactAddressDisplayed));
		String customerNameText = contactAddressDisplayed.getText();
		softassert.assertEquals(customerNameText , excel.getStringData("HomePageSheet", 8, 3) );
		WebElement outletDetails = driver.findElement(By.id("outletsArrowFLS11120889"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);
		softassert.assertAll();

	}

	//Test by searching the invalid contact address in more filter
	@Test(priority = 24 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_ContactAddress_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		wait = new WebDriverWait(driver,20);
		hpage.select_contactButtonwithAddress(excel.getStringData("HomePageSheet", 8, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}



	//Test by searching the valid customer email in more filter
	@Test(priority = 25 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_ContactEmail_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 4));
		wait = new WebDriverWait(driver,20);
		hpage.click_On_MoreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_contactButtonwithEmail(excel.getStringData("HomePageSheet", 9, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement contactKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]"));
		wait.until(ExpectedConditions.visibilityOf(contactKey));
		String contactVerify = contactKey.getText();
		softassert.assertEquals(contactVerify, "yogeswarank@gnrgy.com");
		try {
			hpage.click_On_ContactEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement contactEmailDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Contact details')]//following::div[contains(text(),'Mail:yogeswarank@gnrgy.com')]"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(contactEmailDisplayed));
		String customerEmailText = contactEmailDisplayed.getText();
		softassert.assertEquals(customerEmailText , "Mail:yogeswarank@gnrgy.com" );
		WebElement outletDetails = driver.findElement(By.id("outletsArrowFLS11120889"));
		outletDetails.click();
		boolean outletArrow = outletDetails.isDisplayed();
		softassert.assertEquals(outletArrow , true);
		softassert.assertAll();


	}

	//Test by searching the invalid customer email in more filter
	@Test(priority = 26 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Invalid_ContactEmail_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		wait = new WebDriverWait(driver,20);
		hpage.select_contactButtonwithEmail(excel.getStringData("HomePageSheet", 9, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}



	//Test by searching the valid contact Mobile number in more filter
	@Test(priority = 27 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_By_Valid_ContactMobile_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		hpage.click_On_MoreFilter();
		WebElement customerNamebutton = driver.findElement(By.id("customerBtn"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(customerNamebutton));
		hpage.select_contactButtonwithMobile(excel.getStringData("HomePageSheet", 10, 3), excel.getStringData("HomePageSheet", 10, 1));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			boolean ele = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']")).isDisplayed();


			if(ele==true) {

				WebElement contactKey = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]"));
				wait.until(ExpectedConditions.visibilityOf(contactKey));
				String contactVerify = contactKey.getText();
				softassert.assertEquals(contactVerify, "+91-7904074143");
				try {
					hpage.click_On_ContactPhone();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}




				WebElement contactPhoneDisplayed = driver.findElement(By.xpath("//div[contains(text(),'Contact details')]//following::div[contains(text(),'Ph:+91-7904074143')]"));
				wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(contactPhoneDisplayed));
				String customerPhoneText = contactPhoneDisplayed.getText();
				softassert.assertEquals(customerPhoneText , "Ph:+91-7904074143" );
				WebElement outletDetails = driver.findElement(By.id("outletsArrowFLS11120889"));
				outletDetails.click();
				boolean outletArrow = outletDetails.isDisplayed();
				softassert.assertEquals(outletArrow , true);
			}
			else{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				boolean noele = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]")).isDisplayed();
				softassert.assertEquals(noele,true );
			}}
		catch(Exception e) {
			e.getCause();
			e.getMessage();
		}


		softassert.assertAll();

	}

	//Test by searching the invalid contact mobile number in more filter
	@Test(priority = 28 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = {"login_with_Valid_Username_password"})
	public void search_By_Invalid_ContactMobile_In_MorerFilter() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		hpage.click_On_MoreFilter();
		wait = new WebDriverWait(driver,20);
		hpage.select_contactButtonwithMobile(excel.getStringData("HomePageSheet", 10, 3), excel.getStringData("HomePageSheet", 10, 2));
		hpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='SiteResults']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		softassert.assertAll();
	}






	//Test by searching the all valid values in all fields 
	@Test(priority = 29 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void all_valid_fields() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();

		hpage.select_shown_In_App_Value(excel.getStringData("HomePageSheet", 11, 2));
		hpage.search_by_referenceCode(excel.getStringData("HomePageSheet", 0, 4));
		hpage.operational_status_value(excel.getStringData("HomePageSheet", 12, 4));
		hpage.search_by_stationName(excel.getStringData("HomePageSheet", 1, 4));
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		hpage.search_by_stationAddress(excel.getStringData("HomePageSheet", 2, 1));
		hpage.click_On_MoreFilter();
		hpage.select_customerButtonwithName(excel.getStringData("HomePageSheet", 3, 4));
		hpage.applyClick();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']")).isDisplayed();


			if(ele==true) {

				WebElement cnameDisplay = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'Yariv Kraiem')]"));
				String cname = cnameDisplay.getText();
				softassert.assertEquals(cname,"Yariv Kraiem" );

				Actions a = new Actions(driver);
				a.doubleClick(cnameDisplay).build().perform();

				WebElement moreInfo = driver.findElement(By.id("MoreInfoBtn"));
				WebDriverWait w = new WebDriverWait(driver,30);
				w.until(ExpectedConditions.visibilityOf(moreInfo));
				moreInfo.click();

				WebElement outletArrow = driver.findElement(By.id("outletsArrowGT0000000129"));

				outletArrow.click();


			}
			else{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				boolean noele = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]")).isDisplayed();
				softassert.assertEquals(noele,true );
			}}
		catch(Exception e) {
			e.getCause();
			e.getMessage();
		}


	}


	//Test by searching the  invalid values in any one fields which results in no result found  
	@Test(priority = 30 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void one_invalid_value() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();

		hpage.select_shown_In_App_Value(excel.getStringData("HomePageSheet", 11, 3));
		hpage.search_by_referenceCode(excel.getStringData("HomePageSheet", 0, 4));
		hpage.operational_status_value(excel.getStringData("HomePageSheet", 12, 4));
		hpage.search_by_stationName(excel.getStringData("HomePageSheet", 1, 4));
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 3));
		hpage.search_by_stationAddress(excel.getStringData("HomePageSheet", 2, 1));
		hpage.click_On_MoreFilter();
		hpage.select_customerButtonwithName(excel.getStringData("HomePageSheet", 3, 4));
		hpage.applyClick();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']")).isDisplayed();


			if(ele==true) {

				WebElement cnameDisplay = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'Yariv Kraiem')]"));
				String cname = cnameDisplay.getText();
				softassert.assertEquals(cname,"Yariv Kraiem" );

				Actions a = new Actions(driver);
				a.doubleClick(cnameDisplay).build().perform();

				WebElement moreInfo = driver.findElement(By.id("MoreInfoBtn"));
				WebDriverWait w = new WebDriverWait(driver,30);
				w.until(ExpectedConditions.visibilityOf(moreInfo));
				moreInfo.click();

				WebElement outletArrow = driver.findElement(By.id("outletsArrowGT0000000129"));

				outletArrow.click();


			}
			else{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				boolean noele = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]")).isDisplayed();
				softassert.assertEquals(noele,true );
			}}
		catch(Exception e) {
			e.getCause();
			e.getMessage();
		}


	}


	//Test by searching the  disabled operational type  
	@Test(priority = 31 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_disabled_station() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();

		hpage.select_shown_In_App_Value(excel.getStringData("HomePageSheet", 11, 1));
		hpage.operational_status_value(excel.getStringData("HomePageSheet", 12, 3));
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 4));

		hpage.click_On_MoreFilter();

		hpage.applyClick();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']")).isDisplayed();


			if(ele==true) {

				WebElement statusDisplay = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'Disabled')]"));
				String status = statusDisplay.getText();
				softassert.assertEquals(status,"Disabled" );

				Actions a = new Actions(driver);
				a.doubleClick(statusDisplay).build().perform();

				WebElement moreInfo = driver.findElement(By.id("MoreInfoBtn"));
				WebDriverWait w = new WebDriverWait(driver,30);
				w.until(ExpectedConditions.visibilityOf(moreInfo));
				moreInfo.click();

				WebElement outletArrow = driver.findElement(By.id("outletsArrowFLS90000000"));

				outletArrow.click();


			}
			else{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				boolean noele = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]")).isDisplayed();
				softassert.assertEquals(noele,true );
			}}
		catch(Exception e) {
			e.getCause();
			e.getMessage();
		}


	}


	//Test by searching the  disabled operational type  
	@Test(priority = 32 ,retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password")
	public void search_awaiting_repair_station() {
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.click_On_ChargerStation();

		hpage.select_shown_In_App_Value(excel.getStringData("HomePageSheet", 11, 1));
		hpage.operational_status_value(excel.getStringData("HomePageSheet", 12, 2));
		hpage.search_business_chargers_InOperation(excel.getStringData("HomePageSheet", 13, 4));

		hpage.click_On_MoreFilter();

		hpage.applyClick();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']")).isDisplayed();


			if(ele==true) {

				WebElement statusDisplay = driver.findElement(By.xpath("//tbody[@id='tblSortingBody']//following::td[contains(text(),'Awaiting Repair')]"));
				String status = statusDisplay.getText();
				softassert.assertEquals(status,"Awaiting Repair" );

				Actions a = new Actions(driver);
				a.doubleClick(statusDisplay).build().perform();

				WebElement moreInfo = driver.findElement(By.id("MoreInfoBtn"));
				WebDriverWait w = new WebDriverWait(driver,30);
				w.until(ExpectedConditions.visibilityOf(moreInfo));
				moreInfo.click();




			}
			else{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				boolean noele = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]")).isDisplayed();
				softassert.assertEquals(noele,true );
			}}
		catch(Exception e) {
			e.getCause();
			e.getMessage();
		}


	}
}



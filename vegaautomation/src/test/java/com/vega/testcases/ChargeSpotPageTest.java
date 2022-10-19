package com.vega.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vega.pages.BaseTest;
import com.vega.pages.ChargeSpotPage;
import com.vega.pages.ChargingPage;
import com.vega.pages.LoginPage;

public class ChargeSpotPageTest extends BaseTest {


	
	
	WebDriverWait wait ;


	SoftAssert softassert = new SoftAssert();

	//	Login to the application
	@Test(priority = 1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class , groups = {"Sanity" , "ChargeSpot"})
	
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

	@Test(priority = 999 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})
	public void logout_Of_User()
	{
		ChargingPage hpage = PageFactory.initElements(driver, ChargingPage.class);
		hpage.clickOnLogout();

		WebElement loginbtn = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(loginbtn));
		boolean loginBtnPresent = loginbtn.isDisplayed();
		softassert.assertEquals(loginBtnPresent, true);

		softassert.assertAll();

	}
	
	

	//	Click on charge spot navigation button 

	@Test(priority = 2 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})
	public void Click_On_ChargeSpotNavButton()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();

		WebElement ele = driver.findElement(By.xpath("//h3[contains(text(),'Charge spots')]"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));

		String headerText = ele.getText();
		softassert.assertEquals(headerText, "Charge spots");
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		softassert.assertAll();

	}
	

	
	// Search by valid CBID

	@Test(priority = 3 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_CBID()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByCBID(excel.getStringData("ChargeSpotPageData", 1, 1));
		cpage.applyClick();

		try {
			Thread.sleep(5000);
			WebElement ele = driver.findElement(By.xpath("//div[@id='tableRows']"));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Vega/Images/sort-Ascending.png']"));
		
//		wait.until(ExpectedConditions.visibilityOf(ele1));
		
//		boolean ascbtn = ele1.isDisplayed();
		
//		softassert.assertEquals(ascbtn, true);

		WebElement arrowCollapse = driver.findElement(By.id("outletsArrowFLS11120889"));
		arrowCollapse.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement serialNumber= driver.findElement(By.xpath("//div[@class='col-3']//following::label[contains(text(),'Serial')]//following::label[@title='FLS11120889']"));

		String serialNumberVerification = serialNumber.getText();

		softassert.assertEquals(serialNumberVerification, "FLS11120889");


		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();

		System.out.println("Total count of CBID Search is : " +rows);


		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );
		
		
		
		

		softassert.assertAll();
	}


	// Search by invalid CBID

	@Test(priority = 4 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_Invalid_CBID()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByCBID(excel.getStringData("ChargeSpotPageData", 1, 2));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='ChargeSpotNoResult']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);

		softassert.assertAll();

	}
	
	

	// search by charge type public and get the index count of public chargers  

	@Test(priority = 5 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void searchPublicType() {

		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();

		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 2));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cpage.clickOnViewMore();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpage.tapToBackButton();
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();
		System.out.println("Total count of list displayed for public type charge is : " +rows);

		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );
		
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();

	}
	
	

	// search by charge type All and get the index count of all chargers 

	@Test(priority = 6 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void searchAllType() {

		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();

		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 1));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cpage.clickOnViewMore();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpage.tapToBackButton();
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();

		System.out.println("Total count of list displayed for All  charge is : " +rows);


		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();
		

	}

	

	// search by charge type Private and get the index count of private chargers 

	@Test(priority = 7 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void searchPrivateChargeType() {

		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();

		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cpage.clickOnViewMore();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpage.tapToBackButton();
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();

		System.out.println("Total count of list displayed for private  type charge is : " +rows);


		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );
		
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();

	}

	// search by charge type business and get the index count of business chargers 

	@Test(priority = 7 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void searchBusinessChargeType() {

		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();

		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 3));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cpage.clickOnViewMore();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpage.tapToBackButton();
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();

		System.out.println("Total count of list displayed for business type charge is : " +rows);


		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();

	}


	// Search by valid Station name

	@Test(priority = 8 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_StationName()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByStationName(excel.getStringData("ChargeSpotPageData", 2, 1));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 2));

		cpage.applyClick();

		try {
			Thread.sleep(5000);
			WebElement ele = driver.findElement(By.xpath("//div[@id='tableHeader']"));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();		
		System.out.println("Total count of list displayed for valid station search : " +rows);

		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );

		WebElement arrowCollapse = driver.findElement(By.id("outletsArrowGSN63000077"));
		arrowCollapse.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement serialNumber= driver.findElement(By.xpath("//div[@class='col-3']//following::label[contains(text(),'Serial')]//following::label[@title='GSN63000077']"));
		String serialNumberVerification = serialNumber.getText();
		softassert.assertEquals(serialNumberVerification, "GSN63000077");
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();
		

	}


	// Search by invalid Station name

	@Test(priority = 9 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_Invalid_StationName()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByStationName(excel.getStringData("ChargeSpotPageData", 2, 2));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 2));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='ChargeSpotNoResult']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);

		softassert.assertAll();

	}


	// Search by valid Model name

	@Test(priority = 10 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox" )

	public void search_By_ModelName()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByModel(excel.getStringData("ChargeSpotPageData", 3, 1));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));

		cpage.applyClick();

		try {
			Thread.sleep(5000);
			WebElement ele = driver.findElement(By.xpath("//div[@id='tableHeader']"));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();		
		System.out.println("Total count of list displayed for valid model search : " +rows);

		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );

		WebElement arrowCollapse = driver.findElement(By.id("outletsArrowGSN63000056"));
		arrowCollapse.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement serialNumber= driver.findElement(By.xpath("//div[@class='col-3']//following::label[contains(text(),'Serial')]//following::label[@title='GSN63000056']"));
		String serialNumberVerification = serialNumber.getText();
		softassert.assertEquals(serialNumberVerification, "GSN63000056");
		
		WebElement ele1 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele1));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();
		

	}


	// Search by invalid Model name 

	@Test(priority = 11 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_Invalid_ModelName()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByModel(excel.getStringData("ChargeSpotPageData", 3, 2));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='ChargeSpotNoResult']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);

		softassert.assertAll();

	}


	// Search by valid ip

	@Test(priority = 12 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox")

	public void search_By_ValidIP()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByIP(excel.getStringData("ChargeSpotPageData", 4, 1));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));

		cpage.applyClick();

		try {
			Thread.sleep(5000);
			WebElement ele = driver.findElement(By.xpath("//div[@id='tableHeader']"));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();		
		System.out.println("Total count of list displayed for valid IP search : " +rows);

		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );

		WebElement ele1 = driver.findElement(By.id("outletsArrowGT0000000129"));
		Actions a = new Actions(driver);
		a.moveToElement(ele1).click().build().perform();


		WebElement arrowCollapse = driver.findElement(By.id("outletsArrowGT0000000129"));
		arrowCollapse.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement serialNumber= driver.findElement(By.xpath("//div[@class='col-3']//following::label[contains(text(),'Serial')]//following::label[@title='GT0000000129']"));



		String serialNumberVerification = serialNumber.getText();
		softassert.assertEquals(serialNumberVerification, "GT0000000129");
		
		
		WebElement ele2 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele2));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();

	}


	// Search by invalid IP

	@Test(priority = 13 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_Invalid_IP()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByIP(excel.getStringData("ChargeSpotPageData", 4, 2));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 1));
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='ChargeSpotNoResult']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);

		softassert.assertAll();

	}
	

	// Search by all valid values 

	@Test(priority = 15 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = "Not implemented in Firefox" )

	public void search_By_All_Valid()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByCBID(excel.getStringData("ChargeSpotPageData", 6, 1));
		cpage.searchByModel(excel.getStringData("ChargeSpotPageData", 8, 1));
		cpage.searchByStationName(excel.getStringData("ChargeSpotPageData", 7, 1));
		cpage.searchByIP(excel.getStringData("ChargeSpotPageData", 9, 1));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));

		cpage.applyClick();

		try {
			Thread.sleep(5000);
			WebElement ele = driver.findElement(By.xpath("//div[@id='tableHeader']"));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(By.xpath("//div[starts-with(@id,'Connectors')]"));
		int rows = list.size();		
		System.out.println("Total count of list displayed for valid IP search : " +rows);

		WebElement currentSize = driver.findElement(By.id("currentSize"));
		String currentSizeText = currentSize.getText();
		int convertRowCount = Integer.valueOf(currentSizeText);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertRowCount );

		WebElement ele1 = driver.findElement(By.id("outletsArrowHMCNF2001055"));
		Actions a = new Actions(driver);
		a.moveToElement(ele1).click().build().perform();


		WebElement arrowCollapse = driver.findElement(By.id("outletsArrowHMCNF2001055"));
		arrowCollapse.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement serialNumber= driver.findElement(By.xpath("//div[@class='col-3']//following::label[contains(text(),'Serial')]//following::label[@title='HMCNF2001055']"));



		String serialNumberVerification = serialNumber.getText();
		softassert.assertEquals(serialNumberVerification, "HMCNF2001055");
		
		WebElement ele2 = driver.findElement(By.xpath("//img[@src='/Images/sort-Ascending.png']"));
		wait.until(ExpectedConditions.visibilityOf(ele2));
		
		boolean ascbtn = ele1.isDisplayed();
		
		softassert.assertEquals(ascbtn, true);
		

		softassert.assertAll();
	}


	// Search by one in valid values

	@Test(priority = 16 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" , groups = {"Sanity" , "ChargeSpot"})

	public void search_By_One_Invalid_Value()
	{
		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.clickOnChargeSpotTab();
		cpage.searchByCBID(excel.getStringData("ChargeSpotPageData", 6, 2));
		cpage.searchByModel(excel.getStringData("ChargeSpotPageData", 8, 2));
		cpage.searchByStationName(excel.getStringData("ChargeSpotPageData", 7, 2));
		cpage.searchByIP(excel.getStringData("ChargeSpotPageData", 9, 2));
		cpage.selectChargerType(excel.getStringData("ChargeSpotPageData", 5, 4));
		
		
		cpage.applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement warningPopUp = driver.findElement(By.xpath("//div[@id='ChargeSpotNoResult']//following::p"));
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(warningPopUp));
		boolean warningPopUpVerify = warningPopUp.isDisplayed();
		softassert.assertEquals(warningPopUpVerify, true);
		

		softassert.assertAll();

	}
	
	
	//Enable auto refresh and check for the expanded state 
	
	
	@Test(priority = 17 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "login_with_Valid_Username_password" )
	public void enableAutoRefresh() {

		ChargeSpotPage cpage = PageFactory.initElements(driver, ChargeSpotPage.class);
		cpage.enableAutoRefresh();
		
		
	
	}

	
}

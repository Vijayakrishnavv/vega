package com.vega.testcases;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vega.utility.*;
import com.vega.pages.BaseTest;
import com.vega.pages.ChargingPage;
import com.vega.pages.ChargingTransactionPage;
import com.vega.pages.LoginPage;

public class ChargingTransactionTest extends BaseTest {

	WebDriverWait wait ;

	

	SoftAssert softassert = new SoftAssert();


	//	Login to the application
	@Test(priority = 1, retryAnalyzer = com.vega.listners.RetryAnalyzer.class)
	public void CTransactionPage_login_with_Valid_Username_password() {

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

	@Test(priority = 999 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_logout_Of_User()
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




	// Test to verify user navigated to charging transaction tab by clicking on charging transaction nav button
	@Test(priority = 2 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_clickOnChargingTransactionTabAndVerifyResult() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		boolean chargingTransaction = chargingTransactionTab.isDisplayed();

		softassert.assertEquals(chargingTransaction, true);

		softassert.assertAll();


	}

	

	// valid Reference code
	@Test(priority = 3 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByReferenceCode() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.searchByReferenceCode(excel.getStringData("ChargingTransactionData", 3 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.clickOnApply();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();
			if(ele==true) {
				// Gets the web element of table row
				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				// Count the number of rows in page
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				// Index count at the page top 
				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );

				//Index count of total count 
				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				cpage.clickOnDownload();
				Thread.sleep(5000);
				
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

	

	// Test to verify user searching invalid reference code
	@Test(priority = 4 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByInValidReferenceCode() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));

		cpage.searchByReferenceCode(excel.getStringData("ChargingTransactionData", 3 , 2 ));
		cpage.clickOnApply();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);
				
				
				
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



	// test from valid station name
	@Test(priority = 5 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByStationName() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.searchByStationName(excel.getStringData("ChargingTransactionData", 4 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.clickOnApply();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List <WebElement> ele = driver.findElements(By.xpath("//label[@title='Gnrgy IL Office (Pvt.)']"));
		int rows = ele.size();
		System.out.println("Number of rows starts with station name Gnrgy IN Private is :" +rows );
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
		String countInShow = showingCount.getText();
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);




		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertCount );
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}

	// Test to verify user searching invalid station name code
	@Test(priority = 6 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByInValidStationName() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));

		cpage.searchByStationName(excel.getStringData("ChargingTransactionData", 4 , 2 ));
		cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement noResult = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]"));
		wait.until(ExpectedConditions.visibilityOf(noResult));

		boolean noResultText = noResult.isDisplayed();
		softassert.assertEquals(noResultText, true);
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		softassert.assertAll();

	}

	

	// test from valid CBID
	@Test(priority = 7 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
	public void CTransactionPage_searchByValidChargeSpotSerial() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.searchByChargeSpotName(excel.getStringData("ChargingTransactionData", 5 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement table = driver.findElement(By.xpath("//label[@title='HMCNF2000587']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOf(table));
		

		List <WebElement> ele = driver.findElements(By.xpath("//label[@title='HMCNF2000587']"));
		int rows = ele.size();
		System.out.println("Number of rows starts with CBID HMCNF2000587 is :" +rows );
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
		String countInShow = showingCount.getText();
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);


		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertCount );
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		softassert.assertAll();

	}

	// test from invalid CBID
	@Test(priority = 8 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByInValidChargeSpotSerial() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));

		cpage.searchByChargeSpotName(excel.getStringData("ChargingTransactionData", 5 , 2 ));
		cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement noResult = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]"));
		wait.until(ExpectedConditions.visibilityOf(noResult));

		boolean noResultText = noResult.isDisplayed();
		softassert.assertEquals(noResultText, true);

		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}


	// test from valid customer name
	@Test(priority = 9 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByValidCustomerName() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.searchByCustomerName(excel.getStringData("ChargingTransactionData", 6 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.clickOnApply();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List <WebElement> ele = driver.findElements(By.xpath("//label[@title='Ran Eloya']"));
		int rows = ele.size();
		System.out.println("Number of rows starts with customer name Ran Eloya is :" +rows );
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
		String countInShow = showingCount.getText();
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);



		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertCount );
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}

	// test invalid customer name
	@Test(priority = 10 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByInValidCustomerName() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));

		cpage.searchByCustomerName(excel.getStringData("ChargingTransactionData", 6 , 2 ));
		cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement noResult = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]"));
		wait.until(ExpectedConditions.visibilityOf(noResult));

		boolean noResultText = noResult.isDisplayed();
		softassert.assertEquals(noResultText, true);
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		softassert.assertAll();

	}


	// test from valid ID Tag label
	@Test(priority = 11 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByValidTdTag() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.searchByIdTagLabel(excel.getStringData("ChargingTransactionData", 7 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.clickOnApply();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List <WebElement> ele = driver.findElements(By.xpath("//label[@title='IL.002.000006452']"));
		int rows = ele.size();
		System.out.println(" RFID COUNT is :" +rows );
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
		String countInShow = showingCount.getText();
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);



		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(rows,convertCount );
		
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}

	// test invalid ID Tag label
	@Test(priority = 12 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchByInValidTdTag() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));

		cpage.searchByIdTagLabel(excel.getStringData("ChargingTransactionData", 7 , 2 ));
		cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement noResult = driver.findElement(By.xpath("//p[contains(text(),'No results found!')]"));
		wait.until(ExpectedConditions.visibilityOf(noResult));

		boolean noResultText = noResult.isDisplayed();
		softassert.assertEquals(noResultText, true);

		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}




	// test from last 100 transaction
	@Test(priority = 13 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySpecifyTransaction100() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectDropdownCount(excel.getStringData("ChargingTransactionData", 1 , 2 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
		String countInShow = showingCount.getText();
		System.out.println("Total count : " +countInShow);
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);




		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(100,convertCount );
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}



	// test from last 200 transaction
	@Test(priority = 14 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySpecifyTransaction200() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectDropdownCount(excel.getStringData("ChargingTransactionData", 1 , 3 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
		String countInShow = showingCount.getText();
		System.out.println("Total count : " +countInShow);
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);




		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(200,convertCount );
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}


	// test from last 50 transaction
	@Test(priority = 15 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySpecifyTransaction50() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectDropdownCount(excel.getStringData("ChargingTransactionData", 1 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement showingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
		String countInShow = showingCount.getText();
		System.out.println("Total count : " +countInShow);
		//converting string value of count  into int
		int convertCount = Integer.valueOf(countInShow);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		softassert.assertEquals(50,convertCount );
		
		cpage.clickOnDownload();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softassert.assertAll();

	}

	

	// test for yesterday's  transaction
	@Test(priority = 16 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriodYesterday() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 1 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				cpage.clickOnDownload();
				Thread.sleep(5000);

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


	// test for last 24 hours  transaction
	@Test(priority = 17 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriod24Hours() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 2 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);

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

	// test for last 72 hours  transaction
	@Test(priority = 18 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriod72Hours() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 3 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);

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


	// test for last 7 days  transaction
	@Test(priority = 19 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriod7Days() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 4 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);


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


	// test for last 30 days  transaction
	@Test(priority = 20 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriod30Days() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 5 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);


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
	
	

	// test for last 60 days  transaction
	@Test(priority = 21 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password")
	public void CTransactionPage_searchBySelectPeriod60Days() {

		ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
		cpage.clickOnChargingTransactionTab();
		WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.moreFilter();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTransactionRadio();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cpage.selectTimeDropdown(excel.getStringData("ChargingTransactionData", 2 , 6 ));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//	cpage.clickOnApply();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


			if(ele==true) {

				List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
				int rows = webele.size();
				System.out.println("Number of rows is  :" +rows );
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
				String rowcountInShow = showingRowCount.getText();
				System.out.println("Total count : " +rowcountInShow);
				//converting string value of count  into int
				int convertRowCount = Integer.valueOf(rowcountInShow);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				softassert.assertEquals(rows,convertRowCount );


				WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
				String totalCountInShow = totalShowingCount.getText();
				
				WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
				String totalCountInShowOf = totalShowingCountOf.getText();
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				softassert.assertEquals(totalCountInShow,totalCountInShowOf );
				
				
				cpage.clickOnDownload();
				Thread.sleep(5000);

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
		
	

		
		// test specifying period start date 05/05/2021 end date 05/12/2021
		@Test(priority = 22 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
		public void CTransactionPage_searchBySpecificPeriod() {

			ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
			cpage.clickOnChargingTransactionTab();
			WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			cpage.moreFilter();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			cpage.specifyTransactionRadio();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		//	cpage.SpecificStartTime(excel.getStringData("ChargingTransactionData", 8 , 1 ));
			cpage.SpecificStartTime(Helper.getCurrentDateTime());
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			
		//	cpage.SpecificEndTime(excel.getStringData("ChargingTransactionData", 9 , 1 ));
			cpage.SpecificEndTime(Helper.getCurrentDateTime());
			WebElement e1 = driver.findElement(By.cssSelector("input[value='Apply'][type='button']"));
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.elementToBeClickable(e1));
			cpage.clickOnApply();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				boolean ele = driver.findElement(By.xpath("//div[starts-with(@id,'table-row')]")).isDisplayed();


				if(ele==true) {

					List <WebElement> webele = driver.findElements(By.xpath("//div[starts-with(@id,'table-row')]"));
					int rows = webele.size();
					System.out.println("Number of rows is  :" +rows );
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

					WebElement showingRowCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[2]"));
					String rowcountInShow = showingRowCount.getText();
					System.out.println("Total count : " +rowcountInShow);
					//converting string value of count  into int
					int convertRowCount = Integer.valueOf(rowcountInShow);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					softassert.assertEquals(rows,convertRowCount );


					WebElement totalShowingCount = driver.findElement(By.xpath("//span[contains(text(),'Showing')]//following-sibling::b[3]"));
					String totalCountInShow = totalShowingCount.getText();
					
					WebElement totalShowingCountOf = driver.findElement(By.xpath("//span[contains(text(),'of')]//following::b[3]"));
					String totalCountInShowOf = totalShowingCountOf.getText();
					
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
					softassert.assertEquals(totalCountInShow,totalCountInShowOf );
					
					
					cpage.clickOnDownload();
					Thread.sleep(5000);

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
		
		
		// test specifying period with out start date and end date 
				@Test(priority = 23 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
				public void CTransactionPage_searchBySpecificPeriodWithOutDates() {

					ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
					cpage.clickOnChargingTransactionTab();
					WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.moreFilter();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.specifyTransactionRadio();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificStartTime(excel.getStringData("ChargingTransactionData", 8 , 1 ));
				//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificEndTime(excel.getStringData("ChargingTransactionData", 9 , 1 ));
				//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.clickOnApply();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					WebElement startDateError = driver.findElement(By.id("startDateErrorMsg"));
					String startDateErrorDisplay = startDateError.getText();
					wait.until(ExpectedConditions.textToBePresentInElement(startDateError, startDateErrorDisplay));
					
					softassert.assertEquals(startDateErrorDisplay, "Pick a start date");;
					

					softassert.assertAll();
	
				}
				
				
				
				// test specifying period without end date
				@Test(priority = 24 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
				public void CTransactionPage_searchBySpecificPeriodWithOutStartDate() {

					ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
					cpage.clickOnChargingTransactionTab();
					WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.moreFilter();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.specifyTransactionRadio();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificStartTime(excel.getStringData("ChargingTransactionData", 8 , 1 ));
				//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificEndTime(excel.getStringData("ChargingTransactionData", 9 , 2 ));
					cpage.SpecificEndTime(Helper.getCurrentDateTime());
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.clickOnApply();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					WebElement startDateError = driver.findElement(By.id("startDateErrorMsg"));
					String startDateErrorDisplay = startDateError.getText();
					wait.until(ExpectedConditions.textToBePresentInElement(startDateError, startDateErrorDisplay));
					
					softassert.assertEquals(startDateErrorDisplay, "Pick a start date");;
					

					softassert.assertAll();
	
				}
				
				
				
				
				// test specifying period without end date
				@Test(priority = 25 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
				public void CTransactionPage_searchBySpecificPeriodWithOutEndDate() {

					ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
					cpage.clickOnChargingTransactionTab();
					WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.moreFilter();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.specifyTransactionRadio();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificStartTime(excel.getStringData("ChargingTransactionData", 8 , 2 ));
					cpage.SpecificStartTime(Helper.getCurrentDateTime());
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//	cpage.SpecificEndTime(excel.getStringData("ChargingTransactionData", 9 , 2 ));
				//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.clickOnApply();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					WebElement endDateError = driver.findElement(By.id("endDateErrorMsg"));
					String endDateErrorDisplay = endDateError.getText();
					wait.until(ExpectedConditions.textToBePresentInElement(endDateError, endDateErrorDisplay));
					
					softassert.assertEquals(endDateErrorDisplay, "Pick an end date");;
					

					softassert.assertAll();
	
				}

				
				
				// test specifying period with start time less than end time
				@Test(priority = 26 , retryAnalyzer = com.vega.listners.RetryAnalyzer.class , dependsOnMethods = "CTransactionPage_login_with_Valid_Username_password" , groups = "Not implemented in Firefox")
				public void CTransactionPage_searchBySpecificPeriodWithStartDateLessThanEndDate() {

					ChargingTransactionPage cpage = PageFactory.initElements(driver, ChargingTransactionPage.class);
					cpage.clickOnChargingTransactionTab();
					WebElement chargingTransactionTab = driver.findElement(By.id("navChargerTransactionId"));
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOf(chargingTransactionTab));
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.moreFilter();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.specifyTransactionRadio();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.SpecificStartTime(Helper.getCurrentDateTime());
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.SpecificEndTime(excel.getStringData("ChargingTransactionData", 8, 1));
				//	cpage.SpecificEndTime(Helper.getCurrentDateOfFebOnCurrentYear());
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					cpage.clickOnApply();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					WebElement DateError = driver.findElement(By.id("dateErrorMsg"));
					String DateErrorDisplay = DateError.getText();
					wait.until(ExpectedConditions.textToBePresentInElement(DateError, DateErrorDisplay));
					
					softassert.assertEquals(DateErrorDisplay, "End date cannot be before the start date!");;
					
					
					softassert.assertAll();
	
				} 
				
		
	

	
	


}

package com.vega.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChargingPage {

	WebDriver driver ; 
	WebDriverWait wait ;


	public ChargingPage(WebDriver driver) {
		this.driver = driver ;

	}


	@FindBy(xpath = "//button[starts-with(@onclick,'logOut')]") WebElement logOutbtn;
	@FindBy(xpath = "//button[@id='PopUpBoxButton1']") WebElement popUpYes;
	private @FindBy(xpath = "//span[contains(text(),' Charging stations')]") WebElement chargingStationSection ;
	private @FindBy(id = "shownInApp") WebElement showInApp;
	private @FindBy(id = "operationStatus") WebElement operationStatus;
	private @FindBy(id = "Type") WebElement Chargertype;
	private @FindBy(id = "MoreFilterBtn") WebElement moreFilter ;
	private @FindBy(id = "shownInApp") WebElement ShownInApp;
	private @FindBy(css =  "input[type=button][value=Apply]") WebElement applybtn;
	@FindBy(id = "referenceCode") WebElement referencecodeTxt ;
	@FindBy(id = "navChargingStationId") WebElement chargerStationtab;
	@FindBy(id = "Name") WebElement stationNameTxt;
	@FindBy(id="Address") WebElement stationAddress;
	@FindBy(id= "customerBtn") WebElement customerBtn;
	@FindBy(id="contactBtn") WebElement contactBtn;
	@FindBy(id= "MoreFilterOptName") WebElement filterName;
	@FindBy(id= "MoreFilterOptAddress") WebElement filterAddress;
	@FindBy(id= "MoreFilterOptEmail") WebElement filterEmail;
	@FindBy(id= "MoreFilterOptPhoneNumber") WebElement filterMobile;
	@FindBy(id="searchValue") WebElement customercontactSearch;
	@FindBy(xpath="//button[@id='MoreInfoBtn']") WebElement moreInfo;
	@FindBy(xpath="//tbody[@id='tblSortingBody']//following::td[contains(text(),'rockstar demo')]") WebElement private_charger_site;
	@FindBy(xpath = "//p[@id='Ip_FLS11120889']") WebElement private_charger_IP;
	@FindBy(id="outletsArrowFLS11120889") WebElement private_outlet2_arrow;
	
	@FindBy(xpath="//tbody[@id='tblSortingBody']//following::td[contains(text(),'Yogeswaran Kamalakannan')]") WebElement public_charger_site;
	@FindBy(id="outletsArrowGFT20150030") WebElement public_outlet1_arrow1;
	@FindBy(xpath = "//p[@id='Ip_GFT20150030']") WebElement public_charger_IP;
	
	@FindBy(xpath="//tr//td[contains(text(),'demoshwetha chargertesting')]") WebElement business_charger_site;
	@FindBy(id="outletsArrowHMCNF2000587") WebElement business_outlet1_arrow1;
	@FindBy(xpath = "//p[@id='Ip_HMCNF2000587']") WebElement business_charger_IP;
	
	@FindBy(xpath="//td[contains(text(),'GN*IL*5')]//following-sibling::td[contains(text(),'Gnrgy IL Office')]") WebElement all_charger_site;
	@FindBy(id="outletsArrowGFT20150030") WebElement all_outlet1_arrow1;
	@FindBy(xpath = "//p[@id='Ip_GFT20150030']") WebElement all_charger_IP;
	
	@FindBy(xpath="//tbody[@id='tblSortingBody']//following::td[contains(text(),'GN*IL*6')]//following::span[1]") WebElement station_charger_site;
	@FindBy(id="outletsArrowGT0000000129") WebElement station_outlet1_arrow1;
	@FindBy(xpath = "//p[@id='Ip_GT0000000129']") WebElement station_charger_IP;
	
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]") WebElement customerNameBySearch;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IT*92')]//following::td[5]") WebElement customerNameByAddress;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IL*5')]//following::td[5]") WebElement customerNameByEmail;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IL*46')]//following::td[5]") WebElement customerNameByPhone;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]") WebElement contactNameBySearch;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]") WebElement contactAddressBySearch;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]") WebElement contactEmailBySearch;
	@FindBy(xpath = "//tbody[@id='tblSortingBody']//td[contains(text(),'GN*IN*8')]//following::td[5]") WebElement contactPhoneBySearch;
	
	@FindBy(xpath = "//input[@id='ddlCountryCode']") WebElement phoneNumberDD;
	@FindBy(xpath = "//input[@id='phoneFilter']") WebElement phoneNumber;
	

	public void clickOnLogout()
	{
		try {
			if(logOutbtn.isDisplayed()) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				logOutbtn.click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				popUpYes.click();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void check_default_selected_elements()
	{
		try {
			if(chargingStationSection.isSelected() && showInApp.isSelected() && operationStatus.isSelected() && Chargertype.isSelected()) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				moreFilter.click();
			}
		}
		catch(Exception e) {
			e.getMessage();
		}
	}

	public void applyClick() {
		try{
			applybtn.click();
		}catch(Exception e) {
			e.getMessage();
		}
	}

	public void search_private_chargers_InOperation(String str) {

		Select chargerType = new Select(Chargertype);
		chargerType.selectByVisibleText(str);
		applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void search_public_chargers_InOperation(String str) {

		Select chargerType = new Select(Chargertype);
		chargerType.selectByVisibleText(str);
		applyClick();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search_business_chargers_InOperation(String str) {

		Select chargerType = new Select(Chargertype);
		chargerType.selectByVisibleText(str);
		applyClick();	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search_by_referenceCode(String refCode) {
		referencecodeTxt.sendKeys(refCode);
	}

	public void click_On_ChargerStation() {
		chargerStationtab.click();
	}

	public void search_by_stationName(String stationName) {
		stationNameTxt.sendKeys(stationName);	
	}

	public void search_by_stationAddress(String stationAdd) {
		stationAddress.sendKeys(stationAdd);
	}

	public void click_On_MoreFilter() {
		moreFilter.click();
	}

	public void select_customerButtonwithName(String customerName) {
		customerBtn.click();
		filterName.click();
		customercontactSearch.sendKeys(customerName);
	}

	public void select_customerButtonwithAddress(String customerAddress) {
		customerBtn.click();
		filterAddress.click();
		customercontactSearch.sendKeys(customerAddress);
	}

	public void select_customerButtonwithEmail(String customerEmail) {
		customerBtn.click();
		filterEmail.click();
		customercontactSearch.sendKeys(customerEmail);
	}

	public void select_customerButtonwithMobile(String countryCode , String customerMobile) {
		customerBtn.click();
		filterMobile.click();
		Actions action = new Actions(driver);
		action.click(phoneNumberDD).sendKeys(countryCode).sendKeys(Keys.DOWN, Keys.RETURN);	
		phoneNumber.sendKeys(customerMobile);
	}

	public void select_contactButtonwithName(String customerName) {
		contactBtn.click();
		filterName.click();
		customercontactSearch.clear();
		customercontactSearch.sendKeys(customerName);
	}

	public void select_contactButtonwithAddress(String customerAddress) {
		contactBtn.click();
		filterAddress.click();
		customercontactSearch.clear();
		customercontactSearch.sendKeys(customerAddress);
	}

	public void select_contactButtonwithEmail(String customerEmail) {
		contactBtn.click();
		filterEmail.click();
		customercontactSearch.clear();
		customercontactSearch.sendKeys(customerEmail);
	}

	public void select_contactButtonwithMobile(String countryCode , String customerMobile) {
		contactBtn.click();
		filterMobile.click();
		Actions action = new Actions(driver);
	//	action.click(phoneNumberDD).sendKeys("+91");
		action.click(phoneNumberDD).sendKeys(countryCode).sendKeys(Keys.DOWN, Keys.RETURN);	
		phoneNumber.sendKeys(customerMobile);
	}


	public void moreInfoDetails() {
		moreInfo.click();

	}

	public void private_charger_siteDetails() throws Exception {
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(private_charger_site).build().perform();
		wait = new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
		if(private_charger_IP.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(private_outlet2_arrow));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			private_outlet2_arrow.click();
		}
	}

	public void public_charger_siteDetails() throws Exception {
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.elementToBeClickable(public_charger_site));
		Actions action = new Actions(driver);
		action.doubleClick(public_charger_site).build().perform();
		wait = new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		if(public_charger_IP.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(public_outlet1_arrow1));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			action.click(public_outlet1_arrow1).perform();
		}
	}

	public void business_charger_siteDetails() throws Exception {
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.elementToBeClickable(business_charger_site));
		Actions action = new Actions(driver);
		action.doubleClick(business_charger_site).build().perform();
		wait = new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		moreInfo.click();
		if(business_charger_IP.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(business_outlet1_arrow1));
			action.click(business_outlet1_arrow1).perform();
		}
	}
	
	public void all_charger_siteDetails() throws Exception {
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.elementToBeClickable(all_charger_site));
		Actions action = new Actions(driver);
		action.doubleClick(all_charger_site).build().perform();
		wait = new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		moreInfo.click();
		Thread.sleep(5000);
		if(all_charger_IP.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(all_outlet1_arrow1));
			action.click(all_outlet1_arrow1).perform();
		}
	}
	
	public void search_By_Station_charger_siteDetails() throws Exception {
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.elementToBeClickable(station_charger_site));
		Actions action = new Actions(driver);
		action.doubleClick(station_charger_site).build().perform();
		wait = new WebDriverWait(driver , 20);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		moreInfo.click();
		if(station_charger_IP.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(station_outlet1_arrow1));
			action.click(station_outlet1_arrow1).perform();
		}
	}

	public void click_On_CustomerName() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(customerNameBySearch).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}

	public void click_On_CustomerAddress() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(customerNameByAddress).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}


	public void click_On_CustomerEmail() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(customerNameByEmail).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}

	public void click_On_CustomerPhone() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(customerNameByPhone).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}
	
	public void click_On_ContactName() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(contactNameBySearch).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}
	
	public void click_On_ContactAddress() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(contactAddressBySearch).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}
	
	public void click_On_ContactEmail() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(contactEmailBySearch).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}
	

	public void click_On_ContactPhone() throws Exception {

		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.doubleClick(contactPhoneBySearch).perform();
		wait = new WebDriverWait(driver , 30);
		wait.until(ExpectedConditions.visibilityOf(moreInfo));
		Thread.sleep(5000);
		moreInfo.click();
	}
	

	public void select_shown_In_App_Value(String str) {

		Select shownInapp = new Select(ShownInApp);
		shownInapp.selectByVisibleText(str);
		applyClick();	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void operational_status_value(String str) {

		Select operationalStatus = new Select(operationStatus);
		operationalStatus.selectByVisibleText(str);
		applyClick();	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

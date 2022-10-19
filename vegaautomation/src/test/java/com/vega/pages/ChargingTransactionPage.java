package com.vega.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.vega.utility.Helper;

public class ChargingTransactionPage {
	
	WebDriver driver;

	public ChargingTransactionPage(WebDriver driver) {
		this.driver = driver;
	}
	
	Helper help;
	
	@FindBy(css = "input[value='Apply'][type='button']") WebElement applyBtn;
	@FindBy(id = "navChargerTransactionId") WebElement chargingTransactionTab ;
	@FindBy(css = "input[value='Download'][type='button']") WebElement downloadBtn;
	@FindBy(id = "MoreFilterBtn") WebElement moreFilterBtn ;
	@FindBy(id = "referenceCode") WebElement referencecode;
	@FindBy(id = "SiteName") WebElement stationName;
	@FindBy(id = "ChargerSerial") WebElement chargeSpotName;
	@FindBy(id = "CustomerName") WebElement customerName;
	@FindBy(id = "IdTagLebel") WebElement idTagLabel;
	@FindBy(id = "searchOptionCount") WebElement specifyTransactionCount;
	@FindBy(id = "TransactionCount") WebElement countdropdown;
	@FindBy(id = "searchOptionSpecifyPeriod") WebElement specifyTransactionradio;
	@FindBy(id = "searchOptionSelectPeriod") WebElement selectTransactionradio;
	@FindBy(id = "TimePeriod") WebElement timePeriodDropdown;
	@FindBy(xpath = "//input[@id='StartDate' and @type='date']") WebElement startTimePeriod;
	@FindBy(xpath = "//input[@id='EndDate' and @type='date']") WebElement endTimePeriod;
	
	
	
	public void clickOnDownload() {
		downloadBtn.click();
	}
	
	public void clickOnApply() {
		applyBtn.click();
	}
	
	public void clickOnChargingTransactionTab() {
		chargingTransactionTab.click();
	}
	
	public void searchByReferenceCode(String reference) {
		referencecode.sendKeys(reference);
		
	}
	
	public void searchByStationName(String station) {
		stationName.sendKeys(station);
		
	}
	
	public void searchByChargeSpotName(String ChargeSpot) {
		chargeSpotName.sendKeys(ChargeSpot);
		
	}
	
	public void searchByCustomerName(String customer) {
		customerName.sendKeys(customer);
		
	}
	
	public void searchByIdTagLabel(String idTag) {
		idTagLabel.sendKeys(idTag);
		
	}
	
	public void moreFilter() {
		moreFilterBtn.click();
		
	}
	
	public void specifyTransaction() {
		specifyTransactionCount.click();
		
	}
	
	public void selectDropdownCount(String str) {
		Select dd = new Select(countdropdown);
		dd.selectByVisibleText(str);
		clickOnApply();
	}
	
	public void specifyTransactionRadio() {
		specifyTransactionradio.click();
		
	}
	
	public void selectTransactionRadio() {
		selectTransactionradio.click();
		
	}
	
	public void selectTimeDropdown(String str) {
		Select dd = new Select(timePeriodDropdown);
		dd.selectByVisibleText(str);
		clickOnApply();
	}
	
	public void SpecificStartTime(String str) {
		//startTimePeriod.click();
		startTimePeriod.sendKeys(str);
	}
	
	public void SpecificEndTime(String str) {
		//endTimePeriod.click();
		endTimePeriod.sendKeys(str);
	}
	
	
	

}

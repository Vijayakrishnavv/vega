package com.vega.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChargeSpotPage {


	WebDriver driver ; 
	WebDriverWait wait ;


	public ChargeSpotPage(WebDriver driver) {
		this.driver = driver ;
	}


	private @FindBy(id="navChargerSpotsId") WebElement chargeSpotNavBtn;
	private @FindBy(id="cbid") WebElement cbidSearch;
	private @FindBy(id="station") WebElement stationSearch;
	private @FindBy(id="model") WebElement modelSearch;
	private @FindBy(id="ip") WebElement ipSearch;
	private @FindBy(id="Type") WebElement TypeSelectDropDown;
	private @FindBy(css="input[type='button'][value='Apply']") WebElement applyBtn;
	private @FindBy(xpath="//div[@id='viewMoreDiv']//button") WebElement viewMoreButton;
	private @FindBy(id="backToTop") WebElement tapToBackButton;
	private @FindBy(xpath="//input[@type='checkbox' and @id='AutoRefresh']//following-sibling::span") WebElement autoRefresh;



	public void applyClick() {
		applyBtn.click();
	}

	public void clickOnChargeSpotTab() {
		chargeSpotNavBtn.click();

	}

	public void searchByCBID(String s) {
		cbidSearch.sendKeys(s);
	}

	public void searchByStationName(String s) {
		stationSearch.sendKeys(s);
	}

	public void searchByModel(String s) {
		modelSearch.sendKeys(s);
	}

	public void searchByIP(String s) {
		ipSearch.sendKeys(s);
	}

	public void selectChargerType(String s) {

		Select dd = new Select(TypeSelectDropDown);
		dd.selectByVisibleText(s);

	}

	public void clickOnViewMore() {	


		boolean vmbtn = viewMoreButton.isDisplayed();
		if(vmbtn == true) {


			do {

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewMoreButton);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				Actions a = new Actions(driver);
				a.moveToElement(viewMoreButton).click().build().perform();
				//	a.moveToElement(viewMoreButton, 1920, 955).click().build().perform();
				//	WebDriverWait wait = new WebDriverWait(driver,20);
				//	wait.until(ExpectedConditions.visibilityOf(viewMoreButton));
				//	a.click(viewMoreButton).build();
				//	a.perform();	
			}
			while (vmbtn == false);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewMoreButton);
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(viewMoreButton));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			Actions a = new Actions(driver);
			a.moveToElement(viewMoreButton).click().build().perform();	

		}
	}

	public void tapToBackButton() {
		if(tapToBackButton.isDisplayed()) {
			tapToBackButton.click();
		}
	}

	
	public void enableAutoRefresh() {
		
		WebDriverWait w = new WebDriverWait(driver,30);
		w.until(ExpectedConditions.visibilityOf(autoRefresh));
		Actions a = new Actions(driver);
		a.click(autoRefresh).click().build().perform();
		
		
	}
	
	
}

package com.vega.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {
	
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id = "navDashboardId") WebElement dashboardTab;

	
	public void clickOnDashboardTab() {
		dashboardTab.click();
	}
	
	
	
	
	
	
}

package com.vega.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@id='UserName']")
	WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	WebElement pass;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//button[@id='langSwitch']") WebElement languageDropdown;

	@FindBy(xpath = "//input[@id='heb']") WebElement hebrewSelect;

	@FindBy(xpath = "//input[@id='eng']") WebElement englishSelect;


	public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}


	public void getTitleOfPage() {

		driver.getTitle();

	}

	public void loginToVega(String uname, String password)   {

		pageRefresh();
		
		if (isAlertPresent()) {			
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.visibilityOf(userName));
		w.until(ExpectedConditions.visibilityOf(pass));
		w.until(ExpectedConditions.visibilityOf(loginBtn));	
		if(userName.isDisplayed() && pass.isDisplayed() && loginBtn.isDisplayed()) {
			userName.clear();
			pass.clear();	
			userName.sendKeys(uname);
			pass.sendKeys(password);
			loginBtn.click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void clearLocators()
	{
		userName.clear();
		pass.clear();

	}

	public void loginToVegaWithUsername(String str)   {

		pageRefresh();
		
		if (isAlertPresent()) {			
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.visibilityOf(userName));
		w.until(ExpectedConditions.visibilityOf(pass));
		w.until(ExpectedConditions.visibilityOf(loginBtn));
		if(userName.isDisplayed() && pass.isDisplayed() && loginBtn.isDisplayed()) {
			userName.clear();
			pass.clear();
			userName.sendKeys(str);
			loginBtn.click();
		}

	}

	public void loginToVegaWithPassword(String str){
		pageRefresh();
		if (isAlertPresent()) {			
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.visibilityOf(userName));
		w.until(ExpectedConditions.visibilityOf(pass));
		w.until(ExpectedConditions.visibilityOf(loginBtn));
		if(userName.isDisplayed() && pass.isDisplayed() && loginBtn.isDisplayed()) {
			userName.clear();
			pass.clear();
		
		pass.sendKeys(str);
		loginBtn.click();
		}

	}

	public void loginToVega(){
		
		pageRefresh();
		if (isAlertPresent()) {			
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebDriverWait w = new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.visibilityOf(userName));
		w.until(ExpectedConditions.visibilityOf(pass));
		w.until(ExpectedConditions.visibilityOf(loginBtn));	
		if(userName.isDisplayed() && pass.isDisplayed() && loginBtn.isDisplayed()) {
			userName.clear();
			pass.clear();	
			loginBtn.click();
		}
		
		
	
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public void loginToVega1()   {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement loginClick = driver.findElement(By.xpath("//input[@type='submit']"));

		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeAsyncScript("document.getElementById('UserName').setAttribute('value', 'vijaytest@gnrgy')");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			JavascriptExecutor jse1 = (JavascriptExecutor)driver;
			jse1.executeAsyncScript("document.getElementById('password').setAttribute('value', '123')");
		}catch(Exception e) {
			e.printStackTrace();
		}

		Actions action = new Actions(driver);
		action.click(loginClick).click().perform();

	}

	public void selectHebrewLanguage() {

		languageDropdown.click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(hebrewSelect));
		Actions action = new Actions(driver);
		action.click(hebrewSelect).click().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void selectEnglishLanguage() {

		languageDropdown.click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(englishSelect));
		Actions action = new Actions(driver);
		action.click(englishSelect).click().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

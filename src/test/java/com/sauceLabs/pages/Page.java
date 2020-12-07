package com.sauceLabs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

	protected WebDriver driver;
	WebDriverWait wait;

	/**
	 * Defining class constructor and initializing values
	 * 
	 * @param driver
	 */
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);
	}

	// Creating Abstract Methods
	public abstract String getPageTitle();
	
	public abstract String getTextStringLocator(String xpath);
	
	public abstract void clickStringLocator(String xpath);
	
	public abstract int getLength(String xpath);
	
	public abstract WebElement getStringWebElement(String xpath);

	public abstract String getPageHeader(By locator);

	public abstract String getText(By locator);

	public abstract WebElement getElement(By locator);
	
	public abstract void waitForElementPresent(By locator);

	public abstract String readExcelData(String key, String sheetName);

	public abstract String getPath();

	// Non abstract generic method

	/**
	 * getInstance- (Generic Method) Whatever page class is passed at the runtime
	 * (Login page or home Page etc) first we are checking the declared constructor
	 * for the page classes and passing the WebDriver instance to it and creating
	 * the object of that pirticular class, so instead of creating the object with
	 * new key word we are creating it with newInstance method.
	 * 
	 * @param <TPage>
	 * @param pageClass
	 * @return
	 */
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {

		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}

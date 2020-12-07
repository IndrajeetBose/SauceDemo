package com.sauceLabs.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sauceLabs.pages.BasePage;

public class LoginPage extends BasePage {

	// LoginPage constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Reading Data from Excel file
	private String userName_ID = readExcelData("UserName_ID", "LoginPageKey");
	private String password_ID = readExcelData("Password_ID", "LoginPageKey");
	private String loginButton_ID = readExcelData("LoginButton_ID", "LoginPageKey");
	private String errorText_XPATH = readExcelData("ErrorText_XPATH", "LoginPageKey");

	// Declaring Page locators as private so that they cannot be accessed outside
	// the class thus fulfilling the requirements of Encapsulation concept
	private By userName = By.id(userName_ID);
	private By password = By.id(password_ID);
	private By loginButton = By.id(loginButton_ID);
	private By errorText = By.xpath(errorText_XPATH);

	// Getter Methods
	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return getElement(userName);
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return getElement(password);
	}

	/**
	 * @return the loginButton
	 */
	public WebElement getLoginButton() {
		return getElement(loginButton);
	}

	/**
	 * @return the errorText
	 */
	public WebElement getErrorText() {
		return getElement(errorText);
	}

	/**
	 * @return Page Title
	 */
	public String getLoginPageTitle() {
		return getPageTitle();
	}

	/**
	 * logIn- This method will login to the application using User Name and Password
	 * sent from the script (Positive Scenario)
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws InterruptedException 
	 */
	public SwaglabsHome logIn(String username, String pwd) throws InterruptedException {
		getUserName().clear();
		getUserName().sendKeys(username);
		getPassword().sendKeys(pwd);
		getLoginButton().click();

		return getInstance(SwaglabsHome.class);
	}

	/**
	 * Simply clicking the login button without entering creds (negetive test)
	 */
	public String logIn() {
		getLoginButton().click();
		String errortext = getText(errorText);

		return errortext;
	}

	/**
	 * logInBlankPassword- Entering only user name(Negative test)
	 * 
	 * @param userName
	 */
	public String logInBlankPassword(String userName) {
		getUserName().clear();
		getUserName().sendKeys(userName);
		getLoginButton().click();
		String errortext = getText(errorText);

		return errortext;
	}

	/**
	 * logInWrongCreds- Entering Wrong Credentials (Negative Test)
	 * 
	 * @param wrongUserName
	 * @param wrongPassword
	 */
	public String logInWrongCreds(String wrongUserName, String wrongPassword) {
		getUserName().clear();
		getUserName().sendKeys(wrongUserName);
		getPassword().sendKeys(wrongPassword);
		getLoginButton().click();
		// Using Keys class to clear the password field as clear() is not responding for this field
		getPassword().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		String errortext = getText(errorText);

		return errortext;
	}

}

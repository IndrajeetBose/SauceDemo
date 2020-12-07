package com.sauceLabs.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sauceLabs.pages.BasePage;

public class YourCart extends BasePage {

	public YourCart(WebDriver driver) {
		super(driver);
	}

	private String hamBurger = readExcelData("HamBurger_Icon", "YourCartPageTestData");
	private String logOutButton = readExcelData("LogOut", "YourCartPageTestData");

	private By hamBurgerIcon = By.xpath(hamBurger);
	private By logOut = By.id(logOutButton);

	/**
	 * @return the hamBurgerIcon
	 */
	public WebElement getHamBurgerIcon() {
		return getElement(hamBurgerIcon);
	}

	/**
	 * @return the logOut
	 */
	public WebElement getLogOut() {
		return getElement(logOut);
	}

	/**
	 * click hambruger icon
	 */
	public void clickHamBurgerIcon() {
		getHamBurgerIcon().click();
	}

	/**
	 * logOut- logout from application
	 * 
	 * @return
	 */
	public LoginPage logOut() {
		getLogOut().click();

		return getInstance(LoginPage.class);
	}

	/**
	 * validateItemHeading- Validate Item Heading
	 * 
	 * @param items
	 * @return
	 */
	public boolean validateItemHeading(String items) {
		boolean flag = false;
		String itemsXpath = "//div[@class='inventory_list']/div";
		int length = getLength(itemsXpath);
		for (int i = 1; i <= length; i++) {
			String backPackText = "//div[@class='inventory_list']/div[" + i + "]//div[@class='inventory_item_name']";
			if (getTextStringLocator(backPackText).equals(items)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * clickRomove- click on remove button and remove only one element
	 * 
	 * @param items
	 */
	public void clickRemove(String items) {
		String itemsXpath = "//div[@class='cart_list']//div[@class='cart_item']";
		int length = getLength(itemsXpath);
		for (int i = 1; i <= length; i++) {
			String backPackText = "//div[@class='cart_list']//div[@class='cart_item'][" + i + "]//button";
			if (getTextStringLocator(backPackText).equals(items)) {
				clickStringLocator(backPackText);
				break;
			}
		}
	}

	/**
	 * validateCartIconIsEmpty-To check if cart icon is empty
	 * 
	 * @return
	 */
	public boolean validateCartIconIsEmpty() {
		boolean flag = false;

		String cartIconValue = "//div[@class='cart_list']//div[@class='cart_item']";
		int length = getLength(cartIconValue);
		if (length == 0) {
			flag = true;
		}

		return flag;

	}

}

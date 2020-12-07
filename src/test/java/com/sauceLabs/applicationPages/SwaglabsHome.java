package com.sauceLabs.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sauceLabs.pages.BasePage;

public class SwaglabsHome extends BasePage {

	public SwaglabsHome(WebDriver driver) {
		super(driver);
	}
	
	private String hamBurGer = readExcelData("Hamburger_Icon_XPATH", "SwagLabsHomePageTestData");
	private String closeMenu = readExcelData("Close_Menu_Icon_XPATH", "SwagLabsHomePageTestData");
	private String allItemsManu = readExcelData("All_Items_ID", "SwagLabsHomePageTestData");
	private String aboutMenu = readExcelData("About_ID", "SwagLabsHomePageTestData");
	private String logOutMenu = readExcelData("Log_Out_ID", "SwagLabsHomePageTestData");
	private String restAppStateMenu = readExcelData("Reset_App_State_ID", "SwagLabsHomePageTestData");
	private String cartIcon = readExcelData("Cart_ID", "SwagLabsHomePageTestData");

	private By hamBurgerIcon = By.xpath(hamBurGer);
	private By closeMenuIcon = By.xpath(closeMenu);
	private By allItems = By.id(allItemsManu);
	private By about = By.id(aboutMenu);
	private By logOut = By.id(logOutMenu);
	private By resetAppState = By.id(restAppStateMenu);
	private By cart = By.id(cartIcon);

	/**
	 * @return the hamBurgerIcon
	 */
	public WebElement getHamBurgerIcon() {
		return getElement(hamBurgerIcon);
	}


	/**
	 * 
	 * @return get cart locator
	 */
	public WebElement getCart() {
		return getElement(cart);
	}

	/**
	 * 
	 * @return the close menu icon
	 */
	public WebElement getCloseMenuIcon() {
		return getElement(closeMenuIcon);
	}

	/**
	 * @return the allItems
	 */
	public WebElement getAllItems() {
		return getElement(allItems);
	}

	/**
	 * @return the about
	 */
	public WebElement getAbout() {
		return getElement(about);
	}

	/**
	 * @return the logOut
	 */
	public WebElement getLogOut() {
		return getElement(logOut);
	}

	/**
	 * @return the resetAppState
	 */
	public WebElement getResetAppState() {
		return getElement(resetAppState);
	}


	/**
	 * clickHamBurgerIcon- Click hamburger icon
	 */
	public void clickHamBurgerIcon() {
		getHamBurgerIcon().click();
	}

	/**
	 * validateAllItems- Validate all items menu
	 * @return
	 */
	public boolean validateAllItems() {
		boolean status = getAllItems().isDisplayed();
		return status;
	}

	/**
	 * validateAbout- Validate About manu
	 * @return
	 */
	public boolean validateAbout() {
		boolean status = getAbout().isDisplayed();
		return status;
	}

	/**
	 * validatelogOut- Validate Logout menu
	 * @return
	 */
	public boolean validatelogOut() {
		boolean status = getLogOut().isDisplayed();
		return status;
	}

	/**
	 * validateResetAppState- Validate Reset App State menu
	 * @return
	 */
	public boolean validateResetAppState() {
		boolean status = getResetAppState().isDisplayed();
		return status;
	}

	/**
	 * clickCloseMenuIcon- Click close Menu icon
	 */
	public void clickCloseMenuIcon() {
		getCloseMenuIcon().click();
	}

	/**
	 * cliclCartIcon- ckickCartIcon
	 * @return
	 */
	public YourCart clickCartIcon() {
		getCart().click();

		return getInstance(YourCart.class);
	}

	/**
	 * clickAddToCart- Click add to cart dynamically
	 * 
	 * @param items
	 */
	public void clickAddToCart(String items) {
		String itemsXpath = "//div[@class='inventory_list']/div";
		int length = getLength(itemsXpath);
		for (int i = 1; i <= length; i++) {
			String backPackText = "//div[@class='inventory_list']/div[" + i + "]//div[@class='inventory_item_name']";
			if (getTextStringLocator(backPackText).equals(items)) {
				String addToCart = "//div[@class='inventory_item'][" + i + "]//div[@class='pricebar']/button";
				clickStringLocator(addToCart);
				break;
			}
		}
	}

	/**
	 * validateItemHeading- validate item heading
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
	 * validateItemAddToCart- Validate Add to cart dynamically
	 * @param items
	 * @return
	 */
	public boolean validateItemAddToCart(String items) {
		boolean flag = false;
		String itemsXpath = "//div[@class='inventory_list']/div";
		int length = getLength(itemsXpath);
		for (int i = 1; i <= length; i++) {
			String addToCart = "//div[@class='inventory_item'][" + i + "]//div[@class='pricebar']/button";
			if (getTextStringLocator(addToCart).equals(items)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * validateItemPrice- Validate price dynamically
	 * @param items
	 * @return
	 */
	public boolean validateItemPrice(String items) {
		boolean flag = false;
		String itemsXpath = "//div[@class='inventory_list']/div";
		int length = getLength(itemsXpath);
		for (int i = 1; i <= length; i++) {
			String itemPrice = "//div[@class='inventory_item'][" + i + "]//div[@class='inventory_item_price']";
			if (getTextStringLocator(itemPrice).equals(items)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * validateImage- valiadte omage dynamically
	 * @param srcImgPath
	 * @return
	 */
	public boolean validateImage(String srcImgPath) {
		boolean flag = true;
		String imageElement = "//img[@src='" + srcImgPath + "']";
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getStringWebElement(imageElement));
		if (!ImagePresent) {
			System.out.println("Image not displayed.");
		} else {
			System.out.println("Image displayed.");
		}
		return flag;
	}
}

package com.sauceLabs.testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sauceLabs.applicationPages.LoginPage;
import com.sauceLabs.applicationPages.SwaglabsHome;
import com.sauceLabs.applicationPages.YourCart;
import com.sauceLabs.pages.BaseTest;

public class SwagLabsLoginTest extends BaseTest {

	private String userName;
	private String password;
	private String expectedBlankPasswordValidation;
	private String expectedBlankCredsErrorText;
	private String wrongUserName;
	private String wrongPassword;
	private String wrongCredsErrorText;
	private String swagLabsTitle;
	private String sauceLabsBackPackText;
	private String addToCartButton;
	private String itemPrice;
	private String imageLink;
	private String removeButton;

	private LoginPage loginPage;
	private SwaglabsHome swagLabsHome;
	private YourCart yourCart;

	@Test(priority = 1)
	public void verifyLoginPageTitle() {

		swagLabsTitle = page.readExcelData("Swag_Labs_Title", "ScriptData");

		try {

			// Validate Page Title
			String title = page.getInstance(LoginPage.class).getLoginPageTitle();
			Assert.assertEquals(title, swagLabsTitle);
			System.out.println("Page Title Validated");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void verifyLoginFunctionalityNegativeValidations() {

		expectedBlankPasswordValidation = page.readExcelData("Blank_Password_Error_Text", "ScriptData");
		expectedBlankCredsErrorText = page.readExcelData("Blank_Creds_Error_Text", "ScriptData");
		wrongUserName = page.readExcelData("Wrong_UserName", "Credentials");
		wrongPassword = page.readExcelData("Wrong_Password", "Credentials");
		wrongCredsErrorText = page.readExcelData("Wrong_Creds_Error_Text", "ScriptData");

		userName = page.readExcelData("UserName_Standard", "Credentials");
		password = page.readExcelData("Password_Standard", "Credentials");
		try {

			// Validating without credential
			String blankCredsErrorText = page.getInstance(LoginPage.class).logIn();
			Assert.assertEquals(blankCredsErrorText, expectedBlankCredsErrorText);
			System.out.println("Without Credential Validation Tested");

			// Validating without password
			String blankPassword = page.getInstance(LoginPage.class).logInBlankPassword(userName);
			Assert.assertEquals(blankPassword, expectedBlankPasswordValidation);
			System.out.println("Without Password Validation Tested");

			// Validating wrong password and username
			String wrongCreds = page.getInstance(LoginPage.class).logInWrongCreds(wrongUserName, wrongPassword);
			Assert.assertEquals(wrongCreds, wrongCredsErrorText);
			System.out.println("Wrong Credentials Error Message Validated");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 3)
	public void loginToSwagLabs() {

		userName = page.readExcelData("UserName_Standard", "Credentials");
		password = page.readExcelData("Password_Standard", "Credentials");

		try {
			// Logging into application
			swagLabsHome = page.getInstance(LoginPage.class).logIn(userName, password);
			System.out.println("Logged Into Application");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void validateHamBurgerMenuOptions() {

		boolean expectedStatus = true;

		try {

			// Click on Hamburger Icon
			page.getInstance(SwaglabsHome.class).clickHamBurgerIcon();
			System.out.println("Clicked on Hamburger Icon");

			// Validate Items menu
			boolean allItemsStatus = page.getInstance(SwaglabsHome.class).validateAllItems();
			Assert.assertEquals(allItemsStatus, expectedStatus);
			System.out.println("All Items Menu Valdated");

			// Validate About menu
			boolean about = page.getInstance(SwaglabsHome.class).validateAbout();
			Assert.assertEquals(about, expectedStatus);
			System.out.println("About Menu Valdated");

			// Validate log out menu
			boolean logOut = page.getInstance(SwaglabsHome.class).validatelogOut();
			Assert.assertEquals(logOut, expectedStatus);
			System.out.println("Log Out Menu Valdated");

			// Validate Reset App Menu
			boolean resetAppState = page.getInstance(SwaglabsHome.class).validateResetAppState();
			Assert.assertEquals(resetAppState, expectedStatus);
			System.out.println("Reset App State Menu Valdated");

			// Click close button
			page.getInstance(SwaglabsHome.class).clickCloseMenuIcon();
			System.out.println("Closed Menu Icon");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void addToCartAndValidate() {

		boolean expectedFlag = true;
		sauceLabsBackPackText = page.readExcelData("Sauce_Labs_Back_Pack", "ScriptData");
		addToCartButton = page.readExcelData("Add_To_Cart", "ScriptData");
		itemPrice = page.readExcelData("Price", "ScriptData");
		imageLink = page.readExcelData("Image_Link", "ScriptData");
		removeButton = page.readExcelData("Remove_Button", "ScriptData");

		try {

			// Validate Item Heading
			boolean flag = page.getInstance(SwaglabsHome.class).validateItemHeading(sauceLabsBackPackText);
			Assert.assertEquals(flag, expectedFlag);
			System.out.println("Item Heading Validated");

			// Validate Add To Cart button
			boolean addTocartFlag = page.getInstance(SwaglabsHome.class).validateItemAddToCart(addToCartButton);
			Assert.assertEquals(addTocartFlag, expectedFlag);
			System.out.println("Add To Cart Button Validated");

			// Validate Item price
			boolean priceFlag = page.getInstance(SwaglabsHome.class).validateItemPrice(itemPrice);
			Assert.assertEquals(priceFlag, expectedFlag);
			System.out.println("Item price Validated");

			// Validate Item image
			boolean imageFlag = page.getInstance(SwaglabsHome.class).validateImage(imageLink);
			Assert.assertEquals(imageFlag, expectedFlag);
			System.out.println("Image Validated");

			// Click on Add To Cart button
			page.getInstance(SwaglabsHome.class).clickAddToCart(sauceLabsBackPackText);
			System.out.println("Added to cart");

			// Click on cart icon
			yourCart = page.getInstance(SwaglabsHome.class).clickCartIcon();
			System.out.println("Clicked On Cart Icon");

			// Validate added item on Your cart Page
			page.getInstance(YourCart.class).validateItemHeading(sauceLabsBackPackText);
			System.out.println("Item validated on your order page");

			// Remove added element
			page.getInstance(YourCart.class).clickRemove(removeButton);
			System.out.println("Removed element from your cart");

			// Validate cart is empty after removing the element
			boolean cartIconFlag = page.getInstance(YourCart.class).validateCartIconIsEmpty();
			Assert.assertEquals(cartIconFlag, expectedFlag);
			System.out.println("Cart is Empty");

			// Click on Hamburger Icon
			page.getInstance(YourCart.class).clickHamBurgerIcon();
			System.out.println("Clicked on HamBurger Icon");

			// Logout out of application
			loginPage = page.getInstance(YourCart.class).logOut();
			System.out.println("Logged out of application");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

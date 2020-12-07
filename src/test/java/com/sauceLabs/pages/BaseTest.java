package com.sauceLabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;
	public Page page;

	/**
	 * setUpTest- Using bonigracia web driver manager to reduce the lines of code
	 * that we normally use to create a browser instance
	 * 
	 * @param browser
	 */
	@BeforeClass
	@Parameters(value = { "browser" })
	public void setUpTest(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else
			System.out.println("No Browser is defined in XML file");
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

		// Passing the driver instance to the Page abstract class child class BasePage
		// constructor then only it will be
		// supplied to other pages that we have created
		page = new BasePage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}

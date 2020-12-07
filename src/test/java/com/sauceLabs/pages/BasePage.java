package com.sauceLabs.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	String path = null;
	String excelPath = "//SwagLabsTestData//TestData.xlsx";

	// Overriding the unimplemented methods of the Page abstract class using
	// @Override annotation

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElement(locator).getText();
	}

	@Override
	public String getText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * readExcelData- This method is to read test data from Excel File
	 */
	public String readExcelData(String key, String sheetName) {

		HashMap<String, String> hm = null;
		String excelData = null;
		try {
			path = getPath();
			FileInputStream fis = new FileInputStream(path + excelPath);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);
			int lastRowNumber = sheet.getLastRowNum();
			hm = new HashMap();
			for (int i = 0; i <= lastRowNumber; i++) {
				Row row = sheet.getRow(i);

				Cell keyCell = row.getCell(0);
				String keyData = keyCell.getStringCellValue().trim();

				Cell valueCell = row.getCell(1);
				String value = valueCell.getStringCellValue().trim();

				hm.put(keyData, value);
			}

			for (Map.Entry<String, String> value : hm.entrySet()) {
				if (key.equals(value.getKey())) {
					excelData = value.getValue();
				}
			}

		} catch (Exception e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}

		return excelData;
	}

	/**
	 * getElement- Wrapper function to create web elements
	 */
	@Override
	public WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Error occured while creating Web Element");
			e.printStackTrace();
		}
		return element;
	}



	@Override
	public String getTextStringLocator(String xpath) {
		try {
			String text = driver.findElement(By.xpath(xpath)).getText();
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getLength(String xpath) {
		List<WebElement> ele = driver.findElements(By.xpath(xpath));
		int length = ele.size();
		return length;
	}

	@Override
	public WebElement getStringWebElement(String xpath) {
		WebElement ele = driver.findElement(By.xpath(xpath));
		return ele;
	}

	@Override
	public void clickStringLocator(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.out.println("Exception occured while waiting for the element");
			e.printStackTrace();
		}
	}

	@Override
	public String getPath() {
		File f = new File("");
		String extractedFilePath = f.getAbsolutePath();
		String filePath = extractedFilePath.replaceAll("\\\\+", "/");
		return filePath;
	}

}

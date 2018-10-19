package demo.com.keyword;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import demo.com.common.CommonUtils;
import demo.com.response.Result;

/**
 * @author quoc tran
 *
 */
public class KeywordsWeb {

	public KeywordsWeb() {
	}

	// Open Browser
	public WebDriver openBrowser(String browser, String url) {
		WebDriver driver = CommonUtils.initWebDriver(browser);
		// WebDriver driver;
		try {
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.navigate().to(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

	public void closeBrowser(WebDriver driver) {
		driver.close();
	}

	// Get element
	public WebElement getElement(WebDriver driver, String locator, String attribute) {
		WebElement element;
		switch (locator) {
		case "id":
			element = driver.findElement(By.id(attribute));
			break;
		case "name":
			element = driver.findElement(By.name(attribute));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(attribute));
			break;
		default:
			element = null;
			break;
		}
		return element;
	}

	public void checkDisplayWithTextByXPath(WebDriver driver, By xpath, String expected) {
		String getText = driver.findElement(xpath).getText();
		if (getText.equals(expected)) {
			System.out.print(getText);
		}
	}

	public String getText(WebDriver driver, String attribute) {
		WebElement element = driver.findElement(By.xpath(attribute));
		String getText = element.getText().toString();
		return getText;
	}


	// Find element and input with text with data get from data.properties
	public Result inputByData(WebDriver driver, String locator, String attribute, String value) {
		Result response = new Result();
		try {
			WebElement element = getElement(driver, locator, attribute);
			if (element != null) {
				element.sendKeys(value);
				response.setValidation(true);
			}
		} catch (Exception e) {
			response.setValidation(false);
			response.setMessage("Your XPath or Data may be wrong !");
			// System.err.format("No Element Found to input your text" + e);
		}
		return response;
	}

	// Find locator, attribute to click
	public Result clickElement(WebDriver driver, String locator, String attribute) {
		Result response = new Result();
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				element.click();
				response.setValidation(true);
			}
		} catch (Exception e) {
			response.setValidation(false);
			response.setMessage("Your XPath or Data may be wrong !");
			// System.err.format("No Element Found to perform click" + e);
		}
		return response;
	}

	// Find element and getText to compare with data get from data.properties
	public Result checkElementDislayByText(WebDriver driver, String locator, String attribute, String expectedValue) {
		Result response = new Result();
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				String actualValue = element.getText();
				if (actualValue.equals(expectedValue)) {
					response.setValidation(true);
				} else {
					response.setValidation(false);
					response.setMessage("Expected value and Actual value is not equal");
				}
			} else {
				response.setValidation(false);
				response.setMessage("Element is not found !");

			}
		} catch (Exception e) {
			response.setValidation(false);
			response.setMessage("Your XPath or Data may be wrong !");
			// System.err.format("No Element Found" + e);
		}
		return response;
	}

	// Select first option from drop-down list
	public void selectFirstOptionFromDropdown(WebDriver driver, String locator, String attribute, String value) {
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				Select sl = new Select(element);
				sl.getFirstSelectedOption();
			}
		} catch (Exception e) {
			System.err.format("No Element Found" + e);
		}
	}

	// Find text equal with option from drop-down list and select this text
	public void selectOptionFromDropdownByText(WebDriver driver, String locator, String attribute, String value) {
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				Select sl = new Select(element);
				sl.selectByVisibleText(value);
			}
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found" + e);
		}
	}

	// Get all value from a drop-down
	public List<String> getAllOptionFromDropdown(WebDriver driver, String locator, String attribute) {
		List<String> actualList = new ArrayList<>();
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				Select sl = new Select(element);
				List<WebElement> options = sl.getOptions();
				for (WebElement e : options) {
					String test = e.getText();
					actualList.add(test);
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Your XPath or Data may be wrong !");
			// System.err.format("No Element Found" + e);
		}
		return actualList;
	}

	// Find element and clear data inside
	public Result clearData(WebDriver driver, String locator, String attribute) {
		Result response = new Result();
		try {
			WebElement element = this.getElement(driver, locator, attribute);
			if (element != null) {
				element.clear();
				response.setValidation(true);
			}
		} catch (NoSuchElementException e) {
			response.setValidation(false);
			response.setMessage("Your XPath or Data may be wrong !");
			// System.err.format("No Element Found" + e);
		}
		return response;
	}

	public void screenShotView(WebDriver driver, String filePath) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File file = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File newfile = new File(filePath);
		// Copy file at destination
		FileUtils.copyFile(file, newfile);
	}

	public void waitDis(WebDriver driver, String att, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someid")));
	}

}

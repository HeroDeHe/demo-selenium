package demo.com.testcase;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import demo.com.common.CommonUtils;
import demo.com.keyword.KeywordsWeb;
import demo.com.response.Result;

public class CreateNewAccount {
	KeywordsWeb keyWeb = new KeywordsWeb();
	Properties attributeProperties;
	Properties dataProperties;
	String randomString = CommonUtils.createRandomString(4, "abcdefghijkmn");
	String rdEmail = randomString + "@gmail.com";
	String customerID;
	

	public CreateNewAccount(Properties attributeProperties, Properties dataProperties) {
		this.attributeProperties = attributeProperties;
		this.dataProperties = dataProperties;
	}
	
	// Verify create new account successfull
	public List<Result> createNewAccountSuccessful(String browser, String url) {
		WebDriver driver = keyWeb.openBrowser(browser, url);
		List<Result> getResults = new ArrayList<>();
		
		// Login script
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("username"), 
				dataProperties.getProperty("username")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("password"), 
				dataProperties.getProperty("password")));
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnLogin")));

		// Create New Customer script		
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("menuNewcustomer")));		
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formCustomerName"), 
				randomString));		
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formDateOfBirth"), 
				dataProperties.getProperty("dataDateOfBirth")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formAddress"), 
				dataProperties.getProperty("dataAddress")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formCity"), 
				dataProperties.getProperty("dataCity")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formState"), 
				dataProperties.getProperty("dataState")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formPIN"), 
				dataProperties.getProperty("dataPIN")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formMobileNumber"), 
				dataProperties.getProperty("dataMobileNumber")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formEmail"), 
				rdEmail));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formPassword"), 
				dataProperties.getProperty("dataPassword")));		
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnSubmit")));
		
		// Get Customer ID to create new Account
		customerID = keyWeb.getText(driver, attributeProperties.getProperty("regCustomerID"));
		System.out.println(customerID);		
		
		// Create new Account base on Customer ID just created
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("menuNewAccount")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("CustomerID"),
				customerID));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("InitialDeposit"),
				dataProperties.getProperty("InitialDeposit")));
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnSubmitAccount")));
		
		keyWeb.closeBrowser(driver);
		return getResults;
	}
}

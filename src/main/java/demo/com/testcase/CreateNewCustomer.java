package demo.com.testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import demo.com.common.CommonUtils;
import demo.com.keyword.KeywordsWeb;
import demo.com.response.Result;

/**
 * @author quoc tran
 *
 */
public class CreateNewCustomer {
	
	String randomString = CommonUtils.createRandomString(4, "abcdefghijkmn");
	String rdEmail = randomString + "@gmail.com";
	KeywordsWeb keyWeb = new KeywordsWeb();
	Properties attributeProperties;
	Properties dataProperties;
		
	public CreateNewCustomer(Properties attributeProperties, Properties dataProperties){
		this.attributeProperties = attributeProperties;
		this.dataProperties = dataProperties;
	}

	// Verify create user
	public List<Result> createNewCustomerSuccessful(String browser, String url){
		WebDriver driver = keyWeb.openBrowser(browser, url);
		List<Result> getResults = new ArrayList<>();
		// Conditon, user must login successful
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
			
		keyWeb.closeBrowser(driver);
		return getResults;
	}
	
	
	//Verify data created
	public List<Result> checkCustomerData(String browser, String url){
		WebDriver driver = keyWeb.openBrowser(browser, url);
		List<Result> getResults = new ArrayList<>();
		
		String registeredTitle = keyWeb.getText(driver, attributeProperties.getProperty("registeredTitle"));
		
		if(registeredTitle.equals("Customer Registered Successfully!!!")){
			/*getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regCustomerID"), randomString));*/
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regCustomerName"), randomString));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regGender"), "male"));
			/*getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regBirthdate"), dataProperties.getProperty("dataDateOfBirth")));*/
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regAddress"), dataProperties.getProperty("dataAddress")));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regCity"), dataProperties.getProperty("dataCity")));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regState"), dataProperties.getProperty("dataState")));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regPIN"), dataProperties.getProperty("dataPIN")));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regMobileNo"), dataProperties.getProperty("dataMobileNumber")));
			getResults.add(keyWeb.checkElementDislayByText
					(driver,"xpath", attributeProperties.getProperty("regEmail"), rdEmail));
		}
		keyWeb.closeBrowser(driver);
		return getResults;
	}	
}

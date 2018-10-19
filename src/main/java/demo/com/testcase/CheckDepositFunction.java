package demo.com.testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import demo.com.common.CommonUtils;
import demo.com.keyword.KeywordsWeb;
import demo.com.response.Result;

public class CheckDepositFunction {

	KeywordsWeb keyWeb = new KeywordsWeb();
	Properties attributeProperties;
	Properties dataProperties;
	String randomString = CommonUtils.createRandomString(4, "abcdefghijkmn");
	String rdEmail = randomString + "@gmail.com";
	String customerID, accountID, depositAmount, currentBalance, currentAmount;

	public CheckDepositFunction(Properties attributeProperties, Properties dataProperties) {
		this.attributeProperties = attributeProperties;
		this.dataProperties = dataProperties;
	}

	public List<Result> checkDepositAmount(String browser, String url) {
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
		getResults.add(
				keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formCustomerName"), randomString));
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
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formEmail"), rdEmail));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("formPassword"),
				dataProperties.getProperty("dataPassword")));
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnSubmit")));

		// Get Customer ID to create new Account
		customerID = keyWeb.getText(driver, attributeProperties.getProperty("regCustomerID"));
		System.out.println("Customer id =       " + customerID);

		// Create new Account base on Customer ID just created
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("menuNewAccount")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("CustomerID"), customerID));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("InitialDeposit"),
				dataProperties.getProperty("InitialDeposit")));
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnSubmitAccount")));

		// Get Customer ID to create new Account
		accountID = keyWeb.getText(driver, attributeProperties.getProperty("accAccountID"));
		System.out.println("Account ID =       " + accountID);		
		
		// Get current money
		currentAmount = keyWeb.getText(driver, attributeProperties.getProperty("accCurrentAmount"));
		System.out.println("currentAmount =       " + currentAmount);
		
		// Verify Deposit base on Account ID just created
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("menuDeposit")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("depoAccountNo"), accountID));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("depoAmountForm"),
				dataProperties.getProperty("Amount")));				
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("depoDescription"), customerID));		
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("depoSubmitBtn")));
		
		// Get Amount inputed
		depositAmount = keyWeb.getText(driver, attributeProperties.getProperty("dpoAmountCreditedDetails"));
		System.out.println("Deposit Amount =         " + depositAmount);
		
		//Check money after deposit
		if(accountID.equals(keyWeb.getText(driver, attributeProperties.getProperty("dpoAccountNoDetails")))){
						
			int numCurrentAmount = Integer.parseInt(currentAmount);
			int numDipositAmount = Integer.parseInt(depositAmount);
			
			// Total money = current money + deposit money
			int numFinalAmount = numCurrentAmount + numDipositAmount;
			System.out.println("finalAmount =       " + numFinalAmount);
			
			currentBalance = keyWeb.getText(driver, attributeProperties.getProperty("dpoCurrentBalanceDetails"));
			System.out.println("Current Balance =         " + currentBalance);
			String total = Integer.toString(numFinalAmount);
			//Compare total money after deposit
			if(total.equals(currentBalance)){
				System.out.println("Right!!! Total money after you deposit =      " + total);
			}
		}		
		
		return getResults;
	}

}

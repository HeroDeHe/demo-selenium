package demo.com;

import java.util.List;
import java.util.Properties;
import demo.com.common.CommonUtils;
import demo.com.common.CommonVariables;
import demo.com.response.Result;
import demo.com.testcase.CheckDepositFunction;
import demo.com.testcase.CreateNewAccount;
import demo.com.testcase.CreateNewCustomer;
import demo.com.testcase.Login;

/**
 * @author quoc tran
 *
 */
public class DemoTesting {
	
	public static void main(String[] args) throws InterruptedException {
		Properties getAttribute = CommonUtils.loadProperties(CommonVariables.ATTRIBUTES_PROPERTIES);
		Properties getData = CommonUtils.loadProperties(CommonVariables.DATA_PROPERTIES);		
		Login testcaseLogin = new Login(getAttribute, getData);
		CreateNewCustomer tcCreate = new CreateNewCustomer(getAttribute, getData);
		CreateNewAccount tcCreateAcc = new CreateNewAccount(getAttribute, getData);
		CheckDepositFunction tcCheckDeposit = new CheckDepositFunction(getAttribute, getData);
		List<Result> responses;
		
		// Login - Test Script
		responses = testcaseLogin.loginSuccessfully(CommonVariables.CHROME_BROWSER, CommonVariables.URL);
		CommonUtils.displayTestCase("loginSuccessfully", responses);
		
		responses = tcCreate.createNewCustomerSuccessful(CommonVariables.CHROME_BROWSER, CommonVariables.URL);
		CommonUtils.displayTestCase("createNewCustomerSuccessful", responses);
		
		responses = tcCreateAcc.createNewAccountSuccessful(CommonVariables.CHROME_BROWSER, CommonVariables.URL);
		CommonUtils.displayTestCase("createNewAccountSuccessful", responses);
		
		responses = tcCheckDeposit.checkDepositAmount(CommonVariables.CHROME_BROWSER, CommonVariables.URL);
		CommonUtils.displayTestCase("checkDepositAmount", responses);
	}
}

package demo.com.testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import demo.com.keyword.KeywordsWeb;
import demo.com.response.Result;

/**
 * @author quoc tran
 *
 */

public class Login {
	KeywordsWeb keyWeb = new KeywordsWeb();
	Properties attributeProperties;
	Properties dataProperties;

	public Login(Properties attributeProperties, Properties dataProperties){
		this.attributeProperties = attributeProperties;
		this.dataProperties = dataProperties;
	}

	// Login successful
	public List<Result> loginSuccessfully(String browser, String url){		
		
		WebDriver driver = keyWeb.openBrowser(browser, url);
		List<Result> getResults = new ArrayList<>();
		// Input correct password
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("username"), 
				dataProperties.getProperty("username")));
		getResults.add(keyWeb.inputByData(driver, "xpath", attributeProperties.getProperty("password"), 
				dataProperties.getProperty("password")));
		getResults.add(keyWeb.clickElement(driver, "xpath", attributeProperties.getProperty("btnLogin")));
		getResults.add(keyWeb.checkElementDislayByText(driver, "xpath", attributeProperties.getProperty("welcometitle"),
				dataProperties.getProperty("welcometitle")));
		keyWeb.closeBrowser(driver);
		return getResults;
	}
	
}

package demo.com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.com.response.Result;

/**
 * @author quoc tran
 *
 */
public final class CommonUtils {
	
	// Setup web driver 
	public static WebDriver initWebDriver(String browserName){
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("Firefox")) {
			//System.setProperty("webdriver.chrome.driver", FIREFOX_DRIVER);
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", CommonVariables.CHROME_DRIVER);
			driver = new ChromeDriver();
		}
		return driver;
	}
		
	// Method read attribute and data from file properties. src/test/resources
	public static Properties loadProperties(String filePath){									
        FileInputStream fileInput = null;
        Properties prop;
        try {
        	File file = new File(filePath);
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //load properties file
        prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }		
		return prop;
	}
	
	// For purpose report 
	public static void displayTestCase(String nameOfTestCase, List<Result> results){
		int step=1;
		int count = results.size();
		System.out.println();
		System.out.println("Total step will be executed = " + String.valueOf(count));
		System.out.println("Step by Step of " + nameOfTestCase + " testcase" );
		for(Result response : results){
			System.out.println("Step " + step + ": " +response.getMessage());			
			step++;
		}
	}
	
	public void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {
		  
	    int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	}
	
	public static String createRandomString(int codeLength, String id) {
	    return new SecureRandom()
	            .ints(codeLength, 0, id.length())
	            .mapToObj(id::charAt)
	            .map(Object::toString)
	            .collect(Collectors.joining());
	}
}

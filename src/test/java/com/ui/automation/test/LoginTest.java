/*
 * OOP's concepts in framework
 * 1. Use of Abstraction (Showing essential features while hiding implementation details) : Here we are showing only the login feature while hiding the way we have implemented it, by hiding what locators we have used, what actions we are performing in the method. This is called abstraction.
 * 2. One way of checking if webelement is available on page or not us by using driver.findElements(By.locator()).size!=0.
 * 3. To run using cmd through maven : mvn install -Dbrowser=Chrome -DqaURL=http://way2automation.com/way2auto_jquery/index.php -DxmlFile=testng.xml 
 * -D is for variables which needs to be given in key value pair. Key is what mentioned in pom.xml file and in @Parametes annotation in base class. Value will be input by the user at the run time.
 */

package com.ui.automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.automation.baseClass.BaseClass;
import com.ui.automation.pages.LoginPage;

public class LoginTest extends BaseClass{

	LoginPage loginPage;

	@Test(priority=0)
	public void login() {
		
		
		//Using the report reference variable of ExtentReports class to call the method createTest which returns the object of ExtentTest class called "logger" defined in base class. This logger object is used to generate the logs either info,pass etc in Extent Report
		logger = report.createTest("Login to WayToAutomation");

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting the test");
		loginPage.loginToWaytoAutomation(excel.getStringData("loginData", 0, 0), excel.getStringData(0, 0, 1));
		Assert.assertTrue(loginPage.isLogin());
		logger.pass("Login successfully done");

	}
	
	@Test(priority=1)
	public void loginFail() {
		
		
		//Using the report reference variable of ExtentReports class to call the method createTest which returns the object of ExtentTest class called "logger" defined in base class. This logger object is used to generate the logs either info,pass etc in Extent Report
		logger = report.createTest("Failure Test Case for Login");
		logger.fail("Failed case for Login");
		Assert.assertTrue(loginPage.isLogin());
	}

}

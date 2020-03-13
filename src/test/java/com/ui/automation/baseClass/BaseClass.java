package com.ui.automation.baseClass;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ui.automation.utility.BrowserFactory;
import com.ui.automation.utility.ConfigDataProvider;
import com.ui.automation.utility.ExcelDataProvider;
import com.ui.automation.utility.Utils;

public class BaseClass {

	//Created static WebDriver reference to be used in all Test class by extending the base class
	public static WebDriver driver;

	//Created the ExcelDataProvider reference globally to be used outside the @BeforeSuite annotation as well. This is used to load the excel file before starting the suite using the constructor.
	public ExcelDataProvider excel;

	//Created the ConfigDataProvider reference globally to be used outside the @BeforeSuite annotation as well. This is used to load the configuration file before starting the suite using the constructor.
	public ConfigDataProvider config;

	//Created the ExtentReports reference globally to be used outside the annotation as well. This variable is used in test class which returns the object of ExtentTest class, In our case we are using logger as an object of ExtentTest class.
	public ExtentReports report;

	//Created the ExtentTest reference globally. This is used to log the messages during the tests.
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite() throws IOException {

		Reporter.log("Initializing reports,configs and excel in TestSuite", true);

		//As soon as we create the object of Excel and Config class, the constructor in both the classes will be called and both excel and config file will be loaded. Thats why its good to keep them in BeforeSuite annotation since we would need it before the suite starts to get the details.
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(new File("./Reports/WayToAutomation_"+System.currentTimeMillis()+".html"));
		report = new ExtentReports();
		report.attachReporter(reporter);

		Reporter.log("Initialization for reports,configs and excel in TestSuite completed", true);

	}
	 
	//Below parameters should match with whatever mentioned in pom.xml file. 
	//@Parameters({"browser","qaURL"})
	@BeforeClass
	public void setup() {

		Reporter.log("Launching Application in Before Class",true);
		
		//Below code is commented since we are parameterising the browser and url from maven using pom.xml file rather using it from Config file
		driver = BrowserFactory.launchApplication(driver, config.getBrowser(), config.getDataFromConfig("qaURL"));
		
		//We have parameterised the setup method of BeforeClass annotation to parametrise the browser and url from maven using pom.xml file and parameters mentioned in @Parameters annotation has to be exactly same as mentioned in pom.xml file
		//driver = BrowserFactory.launchApplication(driver, browser, url); //Add arguements in setup() method if you want to use this code again.
		
		Reporter.log("Launch of Application done successfully",true);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitApplication(driver);
		Reporter.log("Closing Application in After Class",true);

	}

	@AfterMethod
	public void afterEachTest(ITestResult result) throws IOException {


		if(result.getStatus()==ITestResult.FAILURE) {

			//Reporter class is to print logs on console when tests are running
			Reporter.log("Taking screenshot for Failed TC", true);

			//Utils.captureScreenshot(driver); //This will capture the screenshot and add at the given location in the method in Utils class. Have commented this code since we are already capturing the screenshot in the below code and adding it to extent reports. Else, multiple times screenshots will be taken.			
			//This will add the screenshot in ExtentReport for the failed test case by adding the screenshot path in createScreenCaptureFromPath method. Add build() in the end as given below.
			logger.fail("Test Failed: "+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(Utils.captureScreenshot(driver)).build());
		}else if(result.getStatus()==ITestResult.SUCCESS) {

			Reporter.log("Taking screenshot for Passed TC", true);


			//Utils.captureScreenshot(driver); 			
			//This will add the screenshot in ExtentReport for the passed test case by adding the screenshot path in createScreenCaptureFromPath method. Add build() in the end as given below.
			logger.pass("Test Passed: "+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(Utils.captureScreenshot(driver)).build());
		}

		//This method will make sure to add the reports in current report for multiple tests.
		report.flush();
	}

}

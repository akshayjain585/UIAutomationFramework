/*
 * Note : This class is just to initialise your browser in framework
 * Methods to return home directory while setting the property for browser
 * 1. "./" returns the root directory of the user and will return "UIAutomationFramework" in the below case. Similarly "/" is the root of the current drive and "../" is the parent of the current directory.
 * 2. System.getProperty("user.dir") returns the root directory of the user and will return "UIAutomationFramework" in the below case.
 * 3. Reason behind using WebDriver parameter in quitApplication method so that it closes the session only for that respective browser.
 */


package com.ui.automation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	WebDriver driver;

	public static WebDriver launchApplication(WebDriver driver, String browserName, String url) {

		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
			driver = new ChromeDriver();			
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {		
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver");
			driver = new FirefoxDriver();
		}else {
			System.out.println("Browser not supported");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(url);

		return driver;

	}

	public static void quitApplication(WebDriver driver) {

		driver.quit();

	}

}

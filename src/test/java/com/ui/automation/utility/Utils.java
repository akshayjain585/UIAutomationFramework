package com.ui.automation.utility;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utils {
	
	//Few Methods : Screenshot, alerts, frames, windows, javascript executor, sync issues, explicit wait
	
	public static String captureScreenshot(WebDriver driver) {
		
		//Casting the driver object to TakesScreenshot interface
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);	
		String screenshotPath = "./Screenshots/"+System.currentTimeMillis()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Screenshot captured");
		} catch (Exception e) {
			System.out.println("Unable to take the screenshot: "+e.getMessage());
		}	
		
		return screenshotPath;	//Reason we are returning screenshotPath from this method so that it can be used in BaseClass @AfterMethod afterEachTest method to attach the screenshot in ExtentReport as well since there we have to provide the screenshotpath in CreateScreenCaptureFromPath method.
	}

}

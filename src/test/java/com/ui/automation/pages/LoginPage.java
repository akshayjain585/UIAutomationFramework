package com.ui.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	@FindBy(xpath="//a[text()='Signin']") WebElement signIn;
	@FindBy(xpath="(//input[@name='username'])[2]") public WebElement uName; //Used public as access  modifier so that we can use the WebElement in test class directly using the object of this class.
	@FindBy(xpath="(//input[@name='password'])[2]") WebElement pwd;
	@FindBy(xpath="(//input[@value='Submit'])[2]") WebElement submit;
	@FindBy(xpath="//a[text()='Home']") WebElement home;
	
	public void loginToWaytoAutomation(String userName, String password) {
		signIn.click();
		uName.sendKeys(userName);		
		pwd.sendKeys(password);
		submit.click();
	}
	
	public boolean isLogin() {
		return home.isDisplayed();		 
	}

}

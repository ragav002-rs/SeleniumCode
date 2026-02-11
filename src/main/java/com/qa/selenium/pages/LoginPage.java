package com.qa.selenium.pages;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	Properties prop;
	WebDriverWait w;
	
	Logger logger = LogManager.getLogger(LoginPage.class);
	
/*	private By login_btn = By.xpath("//div[@aria-label='user']");
	private By email_field = By.name("account");
	private By password_field = By.name("password");
	private By signin_btn = By.xpath("//button[text()='Sign in']");
*/	
	private By username_field = By.name("username");
	private By password_field = By.name("password");
	private By login_btn = By.xpath("//button[text()=' Login ']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageUrl() {
		String url = driver.getCurrentUrl();
		logger.debug("URL:" + url);
		return url;	
	}
	
	public String getPageTitle() {
		String title = driver.getTitle();
		logger.debug("Title:" + title);
		return title;
	}
	
/*	public String performLoginXiamoi(String emailID, String password) {
		
		w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOfElementLocated(login_btn));
	//	Actions a = new Actions(driver);
	//	a.moveToElement(driver.findElement(singin_btn)).perform();
		driver.findElement(login_btn).click();
		driver.findElement(email_field).sendKeys(emailID);
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(signin_btn).click();
		String signintitle = driver.getTitle();
		logger.debug("Page title during signin:" + signintitle);
		return signintitle;
	}  
*/		
	
	public String performLoginOrangeHrm(String emailID , String password) {
		
		driver.findElement(username_field).sendKeys(emailID);
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(login_btn).click();
		String logintitle = driver.getTitle();
		logger.debug("Page title after loginin:" + logintitle);
		return logintitle;
	}
	
}
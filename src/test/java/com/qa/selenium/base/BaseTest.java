package com.qa.selenium.base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.selenium.factory.SeleniumFactory;
import com.qa.selenium.pages.LoginPage;
import com.qa.selenium.pages.SearchPage;

public class BaseTest {
	
	protected SeleniumFactory se;
	protected WebDriver driver;
	protected LoginPage loginpage;
	protected SearchPage searchpage;
	protected Properties prop;
	
	@Parameters({"baseURLKey"})
	@BeforeTest
	public void setup(String baseURLKey) throws IOException {
		
		se = new SeleniumFactory();
		prop = se.init_prop();
		driver = se.initBrowser(prop, baseURLKey);
		loginpage = new LoginPage(driver);
		searchpage = new SearchPage(driver);
	}	
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
		
}

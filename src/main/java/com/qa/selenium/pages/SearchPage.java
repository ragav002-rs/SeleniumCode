package com.qa.selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

	WebDriver driver;
	
	Logger logger = LogManager.getLogger(SearchPage.class);
	
	private By searchicon = By.xpath("//img[@class='search-icon']");
	private By searchbox = By.name("q");
	private By submit = By.xpath("//button[text()='Submit']");
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public String getSearchPageUrl() {
		String url = driver.getCurrentUrl();
		logger.debug("URL:" + url);
		return url;	
	}
	
	public String getSearchPageTitle() {
		String title = driver.getTitle();
		logger.debug("Title:" + title);
		return title;
	}
	
	public void searchItem(String itemname) {
		driver.findElement(searchicon).click();
		driver.findElement(searchbox).sendKeys(itemname);
		driver.findElement(submit).click();	
	}
}

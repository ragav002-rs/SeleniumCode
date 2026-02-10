	package com.qa.selenium.pages;
	
	import java.time.Duration;
	
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	public class SearchPage {
	
		WebDriver driver;
		WebDriverWait w;
		JavascriptExecutor js;
		
		Logger logger = LogManager.getLogger(SearchPage.class);
		
		private By searchicon = By.cssSelector("span.search-icon");
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
			
			js = (JavascriptExecutor) driver;
			w = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement icon = w.until(ExpectedConditions.elementToBeClickable(searchicon));	
	//		w.until(ExpectedConditions.elementToBeClickable(searchicon));
			js.executeScript("arguments[0].scrollIntoView(true);", icon);
			js.executeScript("arguments[0].click()", icon);
			driver.findElement(searchbox).sendKeys(itemname);
			driver.findElement(submit).click();	
		}
	}

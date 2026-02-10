package com.qa.selenium.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumFactory {
	
	WebDriver driver;
	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	EdgeOptions eo;
	
	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getWebDriver() {
		return tldriver.get();
	}

	public WebDriver initBrowser(Properties prop, String baseURLKey) {
		
		String browsername = prop.getProperty("browser").trim();
		Boolean headless = Boolean.parseBoolean(prop.getProperty("headless").trim());
		
		switch (browsername.toLowerCase()) {
		
		case "chrome":
			co = new ChromeOptions();
			
			if(headless) {
				co.addArguments("--headless=new");
				co.addArguments("--window-size=1920,1080");
				co.addArguments("--disable-gpu");
				co.addArguments("--disable-dev-shm-usage");
				co.addArguments("--disable-notifications");
				co.addArguments("--no-sandbox");
			}
			
			tldriver.set(new ChromeDriver(co));
			break;
			
		case "firefox":
			fo = new FirefoxOptions();
			
			if(headless) {
				fo.addArguments("--headless");
				fo.addArguments("--disable-gpu");
			}
			
			tldriver.set(new FirefoxDriver(fo));			
		break;
		
		case "edge":
			eo = new EdgeOptions();
			
			if(headless) {
				eo.addArguments("--headless=new");	
				eo.addArguments("--window-size=1920,1080");
				eo.addArguments("--disable-gpu");
			}
			tldriver.set(new EdgeDriver(eo));
		break;
		default:
		System.out.println("Please pass the right Browsername");
		break;
	}
		
		getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		getWebDriver().get(prop.getProperty(baseURLKey).trim());
		return getWebDriver();
		
	}
	
	public Properties init_prop() throws IOException {
		
		FileInputStream file = new FileInputStream("./src/test/resources/config/config.properties");
		Properties prop = new Properties();
		prop.load(file);
		return prop;		
	}

}

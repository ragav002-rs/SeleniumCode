package com.qa.selenium.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumFactory {
	
	WebDriver driver;
	Properties prop;
	
	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getWebDriver() {
		return tldriver.get();
	}

	public WebDriver initBrowser(Properties prop, String baseURLKey) {
		String browsername = prop.getProperty("browser").trim();
	
		switch (browsername.toLowerCase()) {
		case "chrome":
			tldriver.set(new ChromeDriver());
			break;
		case "firefox":
			tldriver.set(new FirefoxDriver());
		break;
		case "edge":
			tldriver.set(new EdgeDriver());
		break;
		default:
		System.out.println("Please pass the right Browsername");
		break;
	}
		
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

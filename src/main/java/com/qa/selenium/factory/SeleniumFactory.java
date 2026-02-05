package com.qa.selenium.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
		boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false").trim());

		switch (browsername.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless=new");
			}
			tldriver.set(new ChromeDriver(chromeOptions));
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("-headless");
			}
			tldriver.set(new FirefoxDriver(firefoxOptions));
		break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless=new");
			}
			tldriver.set(new EdgeDriver(edgeOptions));
		break;
		default:
			throw new IllegalArgumentException("Please pass the right Browsername: " + browsername);
		}

		System.out.println("Launching browser=" + browsername + ", headless=" + isHeadless);
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

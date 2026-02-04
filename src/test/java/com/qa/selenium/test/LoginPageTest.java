package com.qa.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.selenium.base.BaseTest;
import com.qa.selenium.constants.AppConstants;

public class LoginPageTest extends BaseTest{

	@Test
	public void loginUrl() {
		String actualloginurl = loginpage.getPageUrl();
		Assert.assertEquals(actualloginurl, prop.getProperty("url-LoginPage"));
	}
	
	@Test
	public void loginTitle() {
		String actuallogintitle = loginpage.getPageTitle();
		Assert.assertEquals(actuallogintitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void performSignin() {
		String actualsignintitle = loginpage.PerformLogin(prop.getProperty("emailID"), prop.getProperty("password"));
		Assert.assertEquals(actualsignintitle, AppConstants.SIGN_IN_TITLE);
	}
	
}

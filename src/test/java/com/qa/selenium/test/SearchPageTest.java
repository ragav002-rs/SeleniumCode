package com.qa.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.selenium.base.BaseTest;
import com.qa.selenium.constants.AppConstants;

public class SearchPageTest extends BaseTest{
	
	@Test(priority=1)
	public void searchPageUrl() {
		String actualsearchpageurl = searchpage.getSearchPageUrl();
		Assert.assertEquals(actualsearchpageurl, prop.getProperty("url-SearchPage"));	
	}
	
		@Test(priority=2)
		public void searchPageTitle() {
			String actualsearchpagetitle = searchpage.getSearchPageTitle();
			Assert.assertEquals(actualsearchpagetitle, AppConstants.SEARCH_PAGE_TITLE);
		}
	
	@Test(priority=3)
	public void performSearch() {
		searchpage.searchItem("milk bikis");
	}

}

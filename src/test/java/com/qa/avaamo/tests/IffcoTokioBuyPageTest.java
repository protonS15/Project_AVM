package com.qa.avaamo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.avaamo.base.BaseTest;
import com.qa.avaamo.utils.Constants;

public class IffcoTokioBuyPageTest extends BaseTest {
	

	@Test(priority = 1)
	public void verifyInsurancePageTitleTest() {
		String title = iffcoTokioBuyPage.getInsuranceTitle();
		System.out.println("Insurance page title is : " + title);
		Assert.assertEquals(title, Constants.INSURANCE_PAGE_TITLE);
	}

}

package com.qa.avaamo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.avaamo.base.BaseTest;
import com.qa.avaamo.utils.Constants;

public class HomePageTest extends BaseTest {

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getAvmChatPopUpTitle();
		System.out.println("home page title is : " + title);
		Assert.assertEquals(title, Constants.CHAT_POP_UP_TITLE);
	}


}

package com.qa.avaamo.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.avaamo.base.BaseTest;
import com.qa.avaamo.features.HomePage;
import com.qa.avaamo.features.IffcoTokioBuyPage;
import com.qa.avaamo.utils.Constants;

public class HomePageTest extends BaseTest {
	
	String messageResponse;

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getAvmChatPopUpTitle();
		System.out.println("home page title is : " + title);
		Assert.assertEquals(title, Constants.CHAT_POP_UP_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyNotificationTextTest() {
		String notifiationTest = homePage.getNotificationText();
		System.out.println("Notification text is : " + notifiationTest);
		Assert.assertEquals(notifiationTest, Constants.NOTIFICATION_TEXT);
	}
	
	@Test(priority = 3)
	public void verifyBotLoadedTest() {
		String welcomeTextString = homePage.getIntoBot();
		System.out.println("Ira Bot loaded, welcome text is : " + welcomeTextString);
		homePage.switchToBot();
		homePage.typeTextButtonClick();
		String greetingMessage = "Hello";
		homePage.enterMessage(greetingMessage);
		System.out.println("Greeting message : " + greetingMessage);
		messageResponse = homePage.getResponsePostClear();
		Assert.assertEquals(messageResponse, Constants.GREETING_MESSAGE_RESPONSE);
		System.out.println("Greeting message response --> " + messageResponse);
	}
	
	@Test(priority = 4)
	public void verifyStartOverMenuOptionActionsTest() {
		String windowHandle = homePage.getParentWindow();
		messageResponse = homePage.executeClearCommand();
		Assert.assertTrue(messageResponse.contains(Constants.DEFAULT_MESSAGE_RESPONSE));
		System.out.println("Default command to be displayed on clear command execution : " + messageResponse);
		homePage.clickOnBotMenu();
		Object pageObject = homePage.startOverMenuOptionActions();
		Assert.assertTrue(pageObject instanceof IffcoTokioBuyPage);
		System.out.println("IffcoTokioBuyPage is loaded");
		Object pageObj = homePage.switchToParentWindow(windowHandle);
		Assert.assertTrue(pageObj instanceof HomePage);
		Assert.assertEquals(homePage.getAvmChatPopUpTitle(), Constants.CHAT_POP_UP_TITLE);
		System.out.println("Switched to Chat home page");
	}
	
	@Test(priority = 5)
	public void verifySubmitTestBotFormTest() {
		homePage.switchToBot();
		messageResponse = homePage.executeClearCommand();
		Assert.assertTrue(messageResponse.contains(Constants.DEFAULT_MESSAGE_RESPONSE));
		System.out.println("Default command to be displayed on clear command execution : " + messageResponse);
		WebElement ele = homePage.testBotForm("Chitra", "Bangalore", "Female", "Very Often", "4");
		ele.getText().contains(Constants.TEST_BOT_FORM_SUBMIT_SUCCESS);
		System.out.println("Form submit success message" + ele.getText());
		
	}

}

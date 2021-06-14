package com.qa.avaamo.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.avaamo.base.BasePage;
import com.qa.avaamo.utils.Constants;
import com.qa.avaamo.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By Locators
	private By avmBanner = By.cssSelector("div.avm-banner-text");
	private By avmIcon = By.cssSelector("div.avaamo__icon");
//	private By avmChatPopUp = By.cssSelector("#avaamo__popup");
	private By avmChatPopUpFrame = By.name("avaamoIframe");
//	private By avmChatPopUpTitle = By.cssSelector(".messages_nav-bar_title .title");
	private By chatEnterText = By.cssSelector("textarea[name='message']");
	private By responseText = By.cssSelector(".message-wrap");
	private By botMenu = By.xpath("//div[@title='Switch to bot menu']");
	private By menuOptionStartOver = By.xpath("//a[@class='botMenu__menu__item' and @actionname='Start Over']");
	private By menuOptionHelp = By.xpath("//a[@class='botMenu__menu__item' and @actionname='Help']");
	private By menuOptionContactUs= By.xpath("//a[@class='botMenu__menu__item' and @actionname='Contact us']");
	private By menuOptionTestBot = By.xpath("//a[@class='botMenu__menu__item' and @actionname='Test Bot']");
	
	private By downloadMotorPolicyLink = By.linkText("Download Motor Policy");
	private By downloadHealthPolicyLink = By.linkText("Download Health Policy");
	private By renewMotorPolicyLink = By.linkText("Renew Motor Policy");
	private By buyPolicyLink = By.linkText("Buy Policy");
	private By othersLink = By.linkText("Others");
	private By downloadLink = By.linkText("Download");
	
	//testbotform
	private By fullName = By.xpath("//input[@class='textbox']");
	private By address = By.xpath("//textarea[@class='textbox']");
	private By genderRadioFemale = By.xpath("//span[text()='Female']");
	private By genderRadioByMale = By.xpath("//span[text()='Male']");
	private By searchPolicyTextBy = By.cssSelector("input[class='textbox picklist-textbox']");
	private By rateTheExperience = By.cssSelector("input[id*='rating-']");
	private By formSubmit = By.cssSelector(".btn.default_card_submit");
	private By selectOptions = By.xpath("//ul[@class='list picklist']//li");


	// 2. Constructor of the page class:

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. page actions: features(Behaviour) of the page in the form methods:
	
	public String getNotificationText() {
		return elementUtil.doGetText(avmBanner);
	}
	
	public void getIntoBot() {
		elementUtil.doClick(avmIcon);
		elementUtil.switchFrame(avmChatPopUpFrame);
	}

	public String getAvmChatPopUpTitle() {
		return elementUtil.waitForTitlePresent(Constants.CHAT_POP_UP_TITLE, 10);
	}
	
	public void enterMessage(String message) {
		elementUtil.doSendKeys(chatEnterText, message);
	}
	
	public String getMessageResponse() {
		return elementUtil.doGetText(responseText);
	}
	
	public void clickOnBotMenu() {
		elementUtil.doClick(botMenu);
	}
	
	public void selectBotMenuOption(By locator) {
		elementUtil.doClick(locator);
	}
	
	public void selectFromWelcomeMenuOptions(By locator) {
		elementUtil.doClick(locator);
	}
	
	public IffcoTokioBuyPage clickOnDownloadLink() {
		elementUtil.doClick(downloadLink);
		return new IffcoTokioBuyPage(driver);
	}
	
	public void testBotForm(String name, String address, String gender, String policySearch, String experience) {
		
		elementUtil.doSendKeys(fullName, name);
		elementUtil.doSendKeys(this.address, address);
		if (gender.contentEquals("Male")) {
			elementUtil.doClick(genderRadioByMale);
		} else {
			elementUtil.doClick(genderRadioFemale);
		}
		elementUtil.selectDropDownValueWithoutSelectClass(selectOptions, policySearch);
		elementUtil.doActionsClick(rateTheExperience);
		elementUtil.doClick(formSubmit);
		
	}
}

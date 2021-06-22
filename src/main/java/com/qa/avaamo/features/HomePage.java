package com.qa.avaamo.features;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.avaamo.base.BasePage;
import com.qa.avaamo.utils.Constants;
import com.qa.avaamo.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	public IffcoTokioBuyPage iffcoTokioBuyPage;

	// 1. By Locators
	private By avmBanner = By.cssSelector("div.avm-banner-text");
	private By avmIcon = By.cssSelector("div.avaamo__icon");
//	private By avmChatPopUp = By.cssSelector("#avaamo__popup");
	private By avmChatPopUpFrame = By.name("avaamoIframe");
	private By botWelcomeText = By.cssSelector("#avaamo__popup .welcome-message");
	private By getStartedButton = By.cssSelector("a[class='get-started-link']");
	private By typeTextButton = By.cssSelector("div[class*='botMenu__toggleEditor anchor botMenu__typing']");
//	private By avmChatPopUpTitle = By.cssSelector(".messages_nav-bar_title .title");
	private By chatEnterText = By.cssSelector("textarea[name='message']");
	private By responseText = By.cssSelector(".message-wrap");
	private By botMenu = By.xpath("//div[@title='Switch to bot menu']");
	private By sendButton = By.cssSelector("div[class*='ptr locale-trans send enabled']");
	private By loadIcon = By.cssSelector("div[class='la-ball-pulse la-sm']");
	
	//menu options
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
	private By formTitle = By.cssSelector("div.default_card_title");
	private By fullName = By.xpath("//input[@class='textbox']");
	private By address = By.xpath("//textarea[@class='textbox']");
	private By genderRadioFemale = By.xpath("//span[text()='Female']");
	private By genderRadioByMale = By.xpath("//span[text()='Male']");
	private By searchPolicyText = By.cssSelector("input[class='textbox picklist-textbox']");
	private By rateTheExperience = By.cssSelector("div[class*='composer__container__preview__container__options'] label[for*='rating']");
	private By formSubmit = By.cssSelector(".btn.default_card_submit");
	private By selectOptions = By.xpath("//ul[@class='list picklist']//li");
	private By submitSuccessButton = By.cssSelector("button[class='btn default_card_submit success']");


	// 2. Constructor of the page class:

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. page actions: features(Behaviour) of the page in the form methods:
	
	public String getNotificationText() {
		return elementUtil.doGetText(avmBanner);
	}
	
	public String getBotWelcomeText() {
		return elementUtil.doGetText(botWelcomeText);
	}
	
	public String getIntoBot() {
		elementUtil.doActionsClick(avmIcon);
		String welcomeTextString = getBotWelcomeText();
		elementUtil.doClick(getStartedButton);
		return welcomeTextString;
	}
	
	public void switchToBot() {
		elementUtil.switchFrame(avmChatPopUpFrame);
	}
	
	public void typeTextButtonClick() {
		elementUtil.doActionsClick(typeTextButton);
	}

	public String getAvmChatPopUpTitle() {
		return elementUtil.waitForTitlePresent(Constants.CHAT_POP_UP_TITLE, 10);
	}
	
	public void enterMessage(String message) {
		elementUtil.doSendKeys(chatEnterText, message);
		elementUtil.doActionsClick(sendButton);
	}
	
	public String getMessageResponse() {
		return elementUtil.doGetText(responseText);
	}
	
	public String getResponsePostClear() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> elements = elementUtil.getElements(this.responseText);
		return elements.get(elements.size() - 1).getText();
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
	
	public void switchToTextMode() {
		if (!elementUtil.getElement(chatEnterText).isDisplayed()) {
			elementUtil.doActionsClick(botMenu);
		}
	}
	
	
	public String executeClearCommand() {
		switchToTextMode();
		enterMessage(Constants.CLEAR_COMMAND);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getMessageResponse();
	}
	
	public IffcoTokioBuyPage startOverMenuOptionActions() {
		elementUtil.doActionsClick(menuOptionStartOver);
		String message = getResponsePostClear();
		if (message.contains(Constants.DEFAULT_MESSAGE_RESPONSE))
			System.out.println("Start Over Menu options loaded : " + message);
		else {
			System.out.println("Start Over Menu options not loaded");
		}
		elementUtil.doActionsClick(downloadMotorPolicyLink);
		String downloadPolicyResponse = getResponsePostClear();
		if (message.equalsIgnoreCase(Constants.DOWNLOAD_POLICY_TEXT))
			System.out.println("Download policy Response : " + downloadPolicyResponse);
		else {
			System.out.println("Download policy Response not loaded");
		}
		elementUtil.doActionsClick(downloadLink);
		return new IffcoTokioBuyPage(driver);
	}
	
	public WebElement testBotForm(String name, String address, String gender, String policySearch, String rate) {
		enterMessage(Constants.TEST_BOT_COMMAND);
		String message = getResponsePostClear();
		if (message.contains(Constants.TEST_BOT_FORM_TITLE))
			System.out.println("Test bot form loaded : " + message);
		else {
			System.out.println("Test bot form not loaded");
		}
		elementUtil.doActionsSendKeys(fullName, "name");
		elementUtil.doActionsSendKeys(this.address, address);
		System.out.println("Selecting gender : " + gender);
		if (gender.equalsIgnoreCase("Male")) {
			elementUtil.doActionsClick(genderRadioByMale);
		} else {
			elementUtil.doActionsClick(genderRadioFemale);
		}
		System.out.println("Click on policy text to get select options");
		elementUtil.doActionsClick(searchPolicyText);
		elementUtil.waitForElementToBeLocated(selectOptions, 20000);
		elementUtil.getElement(selectOptions).click();
//		elementUtil.selectDropDownValueWithoutSelectClass(selectOptions, policySearch);
		System.out.println("****Rate your Experience****");
		elementUtil.waitForElementToBeLocated(rateTheExperience, 20000);
		elementUtil.getElement(rateTheExperience).click();
//		List<WebElement> eleList = elementUtil.getElements(rateTheExperience);
//		for (int i = 0; i < eleList.size(); i++) {
//			if (eleList.get(i).getAttribute("radio").contains(rate)) {
//				eleList.get(i).click();
//				break;
//			}
//		}
		System.out.println("Submit form button");
		elementUtil.doActionsClick(formSubmit);
		elementUtil.waitForElementToBeLocated(submitSuccessButton, 20000);
		return elementUtil.getElement(submitSuccessButton);

	}
	
	public String getParentWindow() {
		return driver.getWindowHandle();
	}
	
	public HomePage switchToParentWindow(String parentString) {
		driver.switchTo().window(parentString);
		return new HomePage(driver);
	}
}

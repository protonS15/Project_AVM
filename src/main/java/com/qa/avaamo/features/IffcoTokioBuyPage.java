package com.qa.avaamo.features;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.avaamo.base.BasePage;
import com.qa.avaamo.utils.Constants;
import com.qa.avaamo.utils.ElementUtil;

public class IffcoTokioBuyPage extends BasePage {
	
	WebDriver webDriver;
	ElementUtil elementUtil;
	Set<String> windowSet;
	
	private By policyTextField = By.cssSelector("div[id='buy-car-insurance'] input[class*='form-control ml']");
	private By phoneNumberTextField = By.cssSelector("div[id='buy-car-insurance'] input[class='form-control']");
	private By submitButton = By.xpath("//div[@id='buy-car-insurance']//button[@type='submit']");
	

	public IffcoTokioBuyPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getInsuranceTitle() {
		return elementUtil.waitForTitlePresent(Constants.INSURANCE_PAGE_TITLE, 10);
	}
	
	public Set<String> getWindowHandles() {
		windowSet = driver.getWindowHandles();
		return windowSet;
	}

	
	public void enterAndSubmitFormDetails(String policyNum, String phonenum, String parentString) {
		elementUtil.doSendKeys(policyTextField, policyNum);
		elementUtil.doSendKeys(phoneNumberTextField, phonenum);
		elementUtil.doClick(submitButton);
	}
	
	public void switchtoParentWindow(String parentWindow) {
		
		for (String s:windowSet) {
			if (s.contains(parentWindow)) {
				driver.switchTo().window(parentWindow);
			} else {
				driver.close();
			}
		}
		
	}

	

}

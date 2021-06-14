package com.qa.avaamo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qa.avaamo.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	/**
	 * This method is used to initialize the browser on the basis of given
	 * browser...
	 * 
	 * @param browser
	 * @return This return webdriver driver
	 */
	public WebDriver init_driver(String browser, String browserVersion) {
		System.out.println("browser value is : " + browser);

		highlight = prop.getProperty("highilight");
		optionsManager = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome", browserVersion);
			} 

		}

		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox", browserVersion);
			} 
		}

		else {
			System.out.println("Please pass the correct browser value : " + browser);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	private void init_remoteDriver(String browser, String browserVersion) {

		System.out.println("Running test on remote grid: " + browser);

		if (browser.equals("chrome")) {

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
		} else if (browser.equals("firefox")) {

			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setCapability("browserVersion", browserVersion);

		}

	}


	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * @return it return Properties prop reference
	 */
	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/avaamo/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * This method is used to take the screenshot It will return the path of
	 * screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

package com.seleniumproject.app.SeleniumProject.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	public static WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	
	/**
	 * Initialize driver window
	 * Initialize driver timeouts
	 */
	public static void init() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	}
	
	/**
	 * Set driver to Firefox - Gecko Driver
	 */
	public static WebDriver setFireFoxDriver() {
		System.setProperty("webdriver.gecko.driver",projectPath+"/drivers/geckodriver/geckodriver");
		driver = new FirefoxDriver();
		return driver;
	}
	
	/**
	 * Set driver to Google Chrome - Chrome Driver
	 */
	public static WebDriver setChromeDriver() {
		System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver();
		return driver;
	}
}

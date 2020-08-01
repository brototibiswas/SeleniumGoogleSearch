package com.seleniumproject.app.SeleniumProject.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	private WebDriver driver;
	
	
	public WebDriver setDriver(int browser) {
		String projectPath = System.getProperty("user.dir");
		switch(browser) {
			case 1 :
				System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver/chromedriver");
				driver = new ChromeDriver();
				break;
			case 2:
				System.setProperty("webdriver.gecko.driver",projectPath+"/drivers/chromedriver/geckodriver");
				driver = new FirefoxDriver();
				break;
		}
		
		init();
		return driver;
	}
	
	public WebDriverWait setWait() {
		return new WebDriverWait(driver, 20);
	}
	
	public void quit() throws InterruptedException {
		//close the current tab
		Thread.sleep(2000);
	    driver.close();
	    
	    //close the browser
	    Thread.sleep(2000);
	    driver.quit();
	}
	
	private void init() {
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
	}
}

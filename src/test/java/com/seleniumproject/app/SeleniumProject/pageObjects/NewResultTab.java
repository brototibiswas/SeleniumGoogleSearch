package com.seleniumproject.app.SeleniumProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewResultTab{
	WebDriver driver;
	WebDriverWait wait;
	
	public NewResultTab(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		
		//This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		//Wait for the new page to load and have meta information
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));
		return driver.getTitle();
	}

	public String getMeta() {
		String meta;
		
		Boolean isPresent = driver.findElements(By.xpath("//meta[name='description']")).size() > 0;
		
		if(isPresent) {
			meta = driver.findElement(By.xpath("//meta[name='description']")).getAttribute("content");
		}
		else {
			meta = "* Description is not provided *";
		}
		return meta;
	}

}

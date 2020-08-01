package com.seleniumproject.app.SeleniumProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage{
	public WebDriver driver;
	private WebDriverWait wait;
	private String searchTerm;
	
	@FindBy(name = "q")
	private WebElement searchBar;
	
	@FindBy(name = "btnK")
	private WebElement searchBtn;
	
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		wait = new WebDriverWait(this.driver, 30);  
		
		//This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void navigate(String url) {
		driver.get(url);
	}
	
	public void searchOnGoogle(String searchTerm) {
		this.searchTerm = searchTerm;
		enterSearchTerm();
		clickSearchButton();
	}
	
	private void clickSearchButton() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		searchBtn.click();	//click the "google search" button
	}

	private void enterSearchTerm() {
//		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).clear();
		searchBar.sendKeys(searchTerm);
	}
	
}

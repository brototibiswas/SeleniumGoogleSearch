package com.seleniumproject.app.SeleniumProject.pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 

public class SearchResultPage{
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	@FindBy(xpath = "//div[@class='g'][3]/div[contains(@class,'rc')]/div[@class='r']/a")
	private WebElement thirdResult;

	
	public SearchResultPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		action = new Actions(driver); 
		wait = new WebDriverWait(driver, 20); //initialize wait
		
		//This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}

	public void openThirdResult() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#result-stats")));
		moveToResult(thirdResult);
		openInNewTab(thirdResult);
	}

	
	/**
	 * Open link in new tab using keyboard. 
	 * Specify Control/Command keys based on Operating System
	 * For Mac -> Command
	 * For Windows and Linux or others -> Control
	 */
	private void openInNewTab(WebElement result) {
		//Get OS name.
		String OS = System.getProperty("os.name").toLowerCase();
		
		//for mac
		if(OS.contains("mac")) {
			action.keyDown(Keys.COMMAND)
	        .click(result)
	        .keyUp(Keys.COMMAND)
	        .build()
	        .perform();
		}
		//for windows and linux
		else {
			action.keyDown(Keys.CONTROL)
	        .click(result)
	        .keyUp(Keys.CONTROL)
	        .build()
	        .perform();
		}
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	
	private void moveToResult(WebElement result) {
		//MOVE TO the chosen element
		action.moveToElement(result).perform();
		
//		wait.until(ExpectedConditions.visibilityOf(result));

		//SCROLL DOWN the page till the element is found using script		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//      js.executeScript("arguments[0].scrollIntoView(false);", result);
        
		//NOT WORKING
//        wait.until(ExpectedConditions.elementToBeClickable(result));
        
        //ARROW DOWN to scroll to element
//        result.sendKeys(Keys.ARROW_DOWN);

	}
}

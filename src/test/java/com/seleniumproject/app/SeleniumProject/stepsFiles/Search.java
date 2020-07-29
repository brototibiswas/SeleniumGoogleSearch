package com.seleniumproject.app.SeleniumProject.stepsFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Search {
	WebDriver driver;
	Scanner sc;
	int browser;
	String searchTerm;
	String projectPath;
	String title;
	String meta;
	WebDriverWait wait;
	
	@Before 
	public void setup() {
		sc = new Scanner(System.in);
		projectPath = System.getProperty("user.dir"); //stores location for project
//		System.out.println(projectPath);
	}
	
	
	@Given("User is asked to choose a browser")
	public void user_is_asked_to_choose_a_browser() {
		System.out.println("Choose a Browser. 1 for Google Chrome, 2 for Firefox");
		browser = sc.nextInt();
		
		while((browser != 1) && (browser != 2)) {
			System.out.println("Invalid browser " + browser + " Try again");
			browser = sc.nextInt();
		}
		
		if(browser == 1) {
			System.out.println("You have chosen Google Chrome");
		}
		else if(browser == 2) {
			System.out.println("You have chosen Firefox");
		}
		
		sc.nextLine();
	}

	
	@Then("User is asked to enter a search query")
	public void user_is_asked_to_enter_a_search_query() {
		System.out.println("Write something to search in Google");
		
		searchTerm = sc.nextLine();
		
		while(searchTerm.equals("") && searchTerm.length() == 0) {
			System.out.println("Invalid search. Try again");
	        searchTerm = sc.nextLine();
		}
		
		System.out.println("You have searched >> " + searchTerm);
	}

	
	@Then("The browser is opened and goes to “https:\\/\\/google.com”")
	public void the_browser_is_opened_and_goes_to_https_google_com() {
		if(browser == 2) { 
			System.setProperty("webdriver.gecko.driver","/Users/brototibiswas/Documents/learning code/projects/Java/SeleniumProject/drivers/geckodriver/geckodriver");
			driver = new FirefoxDriver();
		}
		
		else if(browser == 1) {
			System.setProperty("webdriver.chrome.driver","/Users/brototibiswas/Documents/learning code/projects/Java/SeleniumProject/drivers/chromedriver/chromedriver");
			driver = new ChromeDriver();
		}
		
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
	}

	
	@Then("The query is typed Google Search field and search button is clicked")
	public void the_query_is_typed_google_search_field_and_search_button_is_clicked() throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys(searchTerm);
		Thread.sleep(2000);
		driver.findElement(By.name("btnK")).click();
		Thread.sleep(2000);
	}

	
	@Then("The 3rd result from result list is opened in a new tab")
	public void the_3rd_result_from_result_list_is_opened_in_a_new_tab() throws InterruptedException {
		Actions newTab = new Actions(driver); 
		wait = new WebDriverWait(driver, 20);
		
		// Link to open in new tab
		WebElement result = driver.findElement(By.xpath("(//div[@class='g'])[2]/div[@class='rc']/div[@class='r']/a"));
		
		wait.until(ExpectedConditions.visibilityOf(result));

		newTab.moveToElement(result).perform();
		
		//open link in new tab - MacOS
		newTab.keyDown(Keys.COMMAND)
        .click(result)
        .keyUp(Keys.COMMAND)
        .build()
        .perform();
		
		
		// Move to new window
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	
	@Then("The title and meta description from the page is copied")
	public void the_title_and_meta_description_from_the_page_is_copied() {
		//Wait for the new page to load and have meta information
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));
	
		// Get title
		title = driver.getTitle();
		System.out.println(title);
		
		// Get meta if exists
		Boolean isPresent = driver.findElements(By.xpath("//meta[name='description']")).size() > 0;
			
		if(isPresent) {
			System.out.println(driver.findElement(By.xpath("//meta[name='description']")).getAttribute("content"));
		}
		else {
			System.out.println("Description is not provided");
		}
	}

	
	@Then("The browser is closed")
	public void the_browser_is_closed() throws InterruptedException {
		Thread.sleep(1000);
	    driver.close();
	    
	    Thread.sleep(2000);
	   
	    driver.quit();
	    
	    System.out.println("Title = " + title );
	    System.out.println("Description = " + meta );
	}

}

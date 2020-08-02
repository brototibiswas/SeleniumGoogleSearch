package com.seleniumproject.app.SeleniumProject.stepsFiles;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.seleniumproject.app.SeleniumProject.pageObjects.Base;
import com.seleniumproject.app.SeleniumProject.pageObjects.HomePage;
import com.seleniumproject.app.SeleniumProject.pageObjects.NewResultTab;
import com.seleniumproject.app.SeleniumProject.pageObjects.SearchResultPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SearchSteps extends Base{
	WebDriver driver;
	Scanner sc;
	int browser;
	String searchTerm;
	String projectPath;
	String title;
	String meta;
	WebDriverWait wait;
	HomePage homepage;
	SearchResultPage resultpage;
	NewResultTab resultTab;
	
	
	@Before 
	public void setup() {
		sc = new Scanner(System.in);
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

	
	@And("The browser is opened and goes to “https:\\/\\/google.com”")
	public void the_browser_is_opened_and_goes_to_https_google_com() {
		driver = setDriver(browser);
		wait = setWait();
		homepage = new HomePage(driver,wait);
		resultpage = new SearchResultPage(driver,wait);
		resultTab = new NewResultTab(driver,wait);
		homepage.navigate("http://www.google.com");
	}

	
	@And("The query is typed Google Search field and search button is clicked")
	public void the_query_is_typed_google_search_field_and_search_button_is_clicked() throws InterruptedException {
		homepage.searchOnGoogle(searchTerm);
	}

	
	@And("The 3rd result from result list is opened in a new tab")
	public void the_3rd_result_from_result_list_is_opened_in_a_new_tab() throws InterruptedException {
		resultpage.openThirdResult();
	}
	
	
	@Then("The title and meta description from the page is copied")
	public void the_title_and_meta_description_from_the_page_is_copied() {
		title = resultTab.getTitle();
		meta = resultTab.getMeta();
	}
	
	
	@After
	public void tearDown() throws InterruptedException {
		quit();
		//print the title and meta
	    System.out.println("Title = " + title );
	    System.out.println("Description = " + meta );
	}

}

package com.seleniumproject.app.SeleniumProject.stepsFiles;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Search {
	WebDriver driver;
	Scanner sc;
	String browser;
	String searchTerm;
	String projectPath;
	
	@Before 
	public void setup() {
		sc = new Scanner(System.in);
		driver = null;
		projectPath = System.getProperty("user.dir"); //stores location for project
		System.out.println(projectPath);
	}

	
	@Given("User is asked to choose a browser")
	public void user_is_asked_to_choose_a_browser() {
		System.out.println("Choose a Browser. 1 for Google Chrome, 2 for Firefox");
		int choice = sc.nextInt();
		
		while((choice != 1) && (choice != 2)) {
			System.out.println("Invalid browser " + choice + " Try again");
			choice = sc.nextInt();
		}
		
		if(choice == 1) {
			System.out.println("You have chosen Google Chrome");
			browser = "chrome";
		}
		else if(choice == 2) {
			System.out.println("You have chosen Firefox");
			browser = "firefox";
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
		
	}

	
	
	@And("The query is typed Google Search field and search button is clicked")
	public void the_query_is_typed_google_search_field_and_search_button_is_clicked() {
		
	}
	
	

	@Then("The 3rd result from result list is opened in a new tab")
	public void the_3rd_result_from_result_list_is_opened_in_a_new_tab() {
		
	}

}

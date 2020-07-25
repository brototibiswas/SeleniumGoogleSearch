Feature: Get the Title and Meta description from third url on result list
	
Scenario: Perform search in Google and open the third result using user input
	Given User is asked to choose a browser
	Then User is asked to enter a search query
	And The browser is opened and goes to “https://google.com”
	And The query is typed Google Search field and search button is clicked
	And The 3rd result from result list is opened in a new tab
	Then The title and meta description from the page is copied 
 	Then The browser is closed 
Feature: Get the Title and Meta description from third url on result list
	
Scenario: Perform search in Google and open the third result
	Given User opens the browser
	When User navigates to “https://google.com”
	And User types something in Google Search field
	And User clicks on the Search button
	Then Google will show a list of urls relevant to search query
	Then User will open the third url from the result list in a new tab
	Then User will close the tab and the browser

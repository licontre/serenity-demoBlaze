@Skip
Feature: Search for an Offer in the outlet section on Adidas
Scenario Outline: The user is on OutletSection and wants to buy something
	Given the user is navigating to outlet Page
	When the user click on originals section
	Then shoes should be displayed
	And can filter by size <size>
	When the user finds one valid shoe
	And clicks on add to cart button
	Then the product is not properly added because of bot Protection

    Examples:
    | size |
    | 37.5 |
    | 48.5 |
    | 44.5 |
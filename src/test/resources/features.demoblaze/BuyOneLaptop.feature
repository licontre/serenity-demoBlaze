Feature: Search for a laptop and buy it
  Scenario Outline: The user is on the application DemoBlaze and wants to buy something
    Given the user is on our DEMO ONLINE SHOP
    When the user navigate to "<section>"
    And Add to the cart the "<laptopName1>"
    And Add to the cart the "<laptopName2>"
    Then I can see my two items on the cart
    When deletes "<laptopName2>" from cart
    And clicks on place Order
    And fills all the fields
    And click on Purchase
    Then the order price is <price>
    And finish the order
  Examples:
    |section|laptopName1|laptopName2|price|
    |Laptop |Sony vaio i5|MacBook Pro|790|
    |Phones |Nexus 6|Iphone 6 32gb|650|
    |Monitors |Apple monitor 24|ASUS Full HD|230|

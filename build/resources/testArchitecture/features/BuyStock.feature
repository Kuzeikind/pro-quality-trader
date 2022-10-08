@EnableWireMock
Feature: buy stock

  Scenario: buy stock with sufficient cash
    Given user exists
    And user has 51 moneys
    And stock with ticker 'ABC' exists and costs 10 moneys to sell and 9 moneys to buy
    And user has 0 shares of stock with ticker 'ABC'
    When user requests to buy 5 shares of stock with ticker 'ABC'
    Then user should have 5 stocks with ticker 'ABC'
    And kafka event to update user's balance by -50 moneys should be sent

  Scenario: buy stock with insufficient cash
    Given user exists
    And user has 49 moneys
    And stock with ticker 'ABC' exists and costs 10 moneys to sell and 9 moneys to buy
    When user requests to buy 5 shares of stock with ticker 'ABC'
    Then user should receive an error with message saying user has not enough money

Feature: buy stock

  Scenario: buy stock with sufficient cash
    Given user exists
    And user has 0 shares of stock with ticker 'AMZN'
    And shares with ticker 'AMZN' cost 10 moneys to buy
    And user has 51 moneys
    When user requests to buy 5 shares of stock with ticker 'AMZN'
#    Then user should have 5 stocks with ticker 'AMZN'

  Scenario: buy stock with insufficient cash
    Given user exists
    And shares with ticker 'AMZN' cost 10 moneys to buy
    And user has 49 moneys
    When user requests to buy 5 shares of stock with ticker 'AMZN'
#    Then user should receive an error with message saying user has not enough money

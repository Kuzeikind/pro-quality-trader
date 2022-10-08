Feature: update stock price

  Scenario: update existing stock price
    Given stock with ticker 'ABC' exists and costs 10.1 moneys to sell and 10.3 moneys to buy
    When service receives event to update sell price to 12.1 and buy price to 12.3 for stock with ticker 'ABC'
    Then stock with ticker 'ABC' should cost 12.1 to sell and 12.3 to buy
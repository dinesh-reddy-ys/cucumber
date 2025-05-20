Feature: Guess the word

  # The first example has two steps
  Scenario: Login to the screen
    Given User on the login screen 
    When User enter login details and login
    Then User navigate to home page
Feature: Login Screen Functionality

  Scenario: Successful login with valid credentials
    Given User is on the login screen
    When User enters valid username and password
    And User clicks on login button
    Then User should be navigated to home page
    And User should see welcome message

  Scenario: Login with invalid password
    Given User is on the login screen
    When User enters valid username
    And User enters invalid password
    And User clicks on login button
    Then User should see error message "Invalid password"
    And User should remain on login screen

  Scenario: Login with invalid username
    Given User is on the login screen
    When User enters invalid username
    And User enters valid password
    And User clicks on login button
    Then User should see error message "Invalid username"
    And User should remain on login screen

  Scenario: Login with empty credentials
    Given User is on the login screen
    When User clicks on login button without entering credentials
    Then User should see validation message for username field
    And User should see validation message for password field
    And User should remain on login screen

  Scenario: Login with locked account
    Given User is on the login screen
    And User account is locked
    When User enters valid username and password
    And User clicks on login button
    Then User should see message "Account is locked"
    And User should see option to reset account

  Scenario: Password reset through login screen
    Given User is on the login screen
    When User clicks on "Forgot Password" link
    Then User should be redirected to password reset page

  Scenario: Remember me functionality
    Given User is on the login screen
    When User enters valid username and password
    And User checks "Remember Me" checkbox
    And User clicks on login button
    Then User should be logged in successfully
    And User credentials should be remembered for next login

  Scenario: Login attempt limit exceeded
    Given User is on the login screen
    When User enters invalid credentials 3 times
    Then User account should be temporarily locked
    And User should see message "Too many failed attempts"

  Scenario: Login with special characters in credentials
    Given User is on the login screen
    When User enters username with special characters
    And User enters password with special characters
    And User clicks on login button
    Then User should be logged in successfully

  Scenario Outline: Login with different user roles
    Given User is on the login screen
    When User enters "<username>" and "<password>"
    And User clicks on login button
    Then User should be logged in with "<role>" permissions
    And User should see "<role>" specific dashboard

    Examples:
      | username      | password      | role          |
      | admin        | admin123      | Administrator |
      | manager      | manager123    | Manager       |
      | user         | user123       | User          |
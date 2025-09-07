Feature: Login functionality for SauceDemo users

  Background:
    Given I am on the SauceDemo login page

  Scenario: Standard user can login successfully
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the Products page

  Scenario: Locked out user sees an error
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message containing "locked"

  Scenario: Invalid user shows error
    When I login with username "invalid_user" and password "invalid_pass"
    Then I should see an error message containing "do not match"

  Scenario Outline: Valid SauceDemo user types can login successfully
    When I login with username "<username>" and password "secret_sauce"
    Then I should see the Products page
    And I logout
    
     Examples:
      | username                |
      | problem_user           |
      | performance_glitch_user|
      | error_user             |
      | visual_user            |

    
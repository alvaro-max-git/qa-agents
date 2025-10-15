@openHome
Feature: Verify Your Account Mini App

  Scenario: I tpye the correct verification code
    Given I am in the homepage
    When I navigate to the Verify Your Account Mini App
    And I type the correct code
    Then I see the success message
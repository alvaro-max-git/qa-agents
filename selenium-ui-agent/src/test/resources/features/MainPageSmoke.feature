@openHome
Feature: Main page smoke

  Scenario: Homepage loads and I can click an app
    Given I am in the homepage
    When I navigate to the first app
    Then I should be in the Dynamic Table app page

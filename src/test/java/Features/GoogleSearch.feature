Feature: Searching google should return the name of query

  Scenario: Google search with scenario
    Given user launches Google webapp
    When user searches for a "LambdaTest"
    Then click on search button


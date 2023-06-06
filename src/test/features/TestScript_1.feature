Feature: Test 1

  Scenario: Create Advance Renewal user
    Given Create a user
    And Generate the Bearer Token
    And Generate the UserId
    When Add the Subscription Plan
    Then Advance Renewal User is created


  Scenario: Create Lapser user
    Given Create a user
    And Generate the Bearer Token
    And Generate the UserId
    When Add the Subscription Plan
    Then Lapser User is created
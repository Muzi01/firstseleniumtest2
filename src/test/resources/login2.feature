Feature: login orange
  @1
  Scenario: Login To Orange
    When Customer is on application page
    And He is login to System
    @3
    Scenario: Login to Orange Alternative
      When  Customer is on application page
      And  Fill His username
      And  Fill His password





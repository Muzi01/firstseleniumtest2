Feature: login orange
  @1 @login
  Scenario: Login To Orange
    When Customer is on application page
    And He is login to System
    @3 @login
    Scenario: Login to Orange Alternative
      When  Customer is on application page
      And  Fill His username
      And  Fill His password



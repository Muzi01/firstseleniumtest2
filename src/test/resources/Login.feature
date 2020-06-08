@login
Feature: Login

@critical
  Scenario: : Unauthorized Login
    Given  Customer is on application page
    And He is login to System

  Scenario: User Is login to linkedin
    Given User is on linkedin login page
    Given User fill login
  @critical
  Scenario: Alternatywne logowanie  Orange xpath 3
    Given He opens login page
    And Write login
    And Write password









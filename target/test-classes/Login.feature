Feature: Login

@test
  Scenario: : Unauthorized Login
    Given  Customer is on application page
    And He is login to System

  Scenario: User Is login to linkedin
    Given User is on linkedin login page
    Given User fill login


    Scenario:  alternatywa 3 logowania
      Given He opens login page
      And Fill login data
      And Fill password data




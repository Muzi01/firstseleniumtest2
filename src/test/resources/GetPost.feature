Feature:
  Verify diffrent Get operations

  Scenario: Verify one author of the post
    Given I perform GET operation for "/post"
    And I perform GET for post number "1"
    Then I should see the autor name as
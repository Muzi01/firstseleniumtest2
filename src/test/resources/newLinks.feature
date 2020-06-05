Feature:  test

  Scenario: Test Links on pages
    Given System checks links on tvn24


  Scenario Outline: <test> - Verify links in given main url : <mainPageUrl>
    Then All links on page "<mainPageUrl>" are active


Feature: Page url's verification tests
  Purpose of test is to get all links from webpage and send GET request and assert html response code is proper

  @FT_onboarding_acquisition
  Scenario Outline: <test> - Verify links in given main url : <mainPageUrl>
    Then All links on page "<mainPageUrl>" are active

    @sit @es @hapi @TEST-88653 @critical
    Examples:
      | test       | mainPageUrl             |
      | TEST-88653 | https://wp.pl |

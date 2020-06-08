$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/newLinks.feature");
formatter.feature({
  "name": "test",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@links"
    }
  ]
});
formatter.scenario({
  "name": "Test links on TVN24",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@links"
    },
    {
      "name": "@smoke"
    }
  ]
});
formatter.step({
  "name": "System checks links on tvn24",
  "keyword": "Given "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.systemChecksLinksOnTvn(int)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Test links on wp.pl",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@links"
    },
    {
      "name": "@smoke"
    }
  ]
});
formatter.step({
  "name": "System Check lins on  wp.pl",
  "keyword": "Given "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.systemCheckLinsOnWpPl()"
});

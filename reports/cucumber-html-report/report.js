$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/Registraction.feature");
formatter.feature({
  "name": "Registraction process",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Registraction Process in travell planet",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@register"
    }
  ]
});
formatter.step({
  "name": "Customer is on travell planet registraction page",
  "keyword": "When "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.customerIsOnTravellPlanetRegistractionPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "He register account via Webpage",
  "keyword": "Then "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.heRegisterAccountViaWebpage()"
});
formatter.result({
  "status": "passed"
});
});
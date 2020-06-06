$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/login2.feature");
formatter.feature({
  "name": "login orange",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Login To Orange",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@1"
    },
    {
      "name": "@login"
    }
  ]
});
formatter.step({
  "name": "Customer is on application page",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "He is login to System",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Login to Orange Alternative",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@3"
    },
    {
      "name": "@login"
    }
  ]
});
formatter.step({
  "name": "Customer is on application page",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Fill His username",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Fill His password",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});
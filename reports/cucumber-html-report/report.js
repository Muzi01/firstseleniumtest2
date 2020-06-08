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
formatter.result({
  "error_message": "org.openqa.selenium.NoSuchWindowException: no such window: target window already closed\nfrom unknown error: web view not found\n  (Session info: chrome\u003d83.0.4103.61)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027muzis-iMac\u0027, ip: \u0027192.168.1.152\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.3\u0027, java.version: \u00271.8.0_211\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 83.0.4103.61, chrome: {chromedriverVersion: 83.0.4103.39 (ccbf011cb2d2b..., userDataDir: /var/folders/5j/4d78x1v55r5...}, goog:chromeOptions: {debuggerAddress: localhost:50441}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: MAC, platformName: MAC, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify, webauthn:virtualAuthenticators: true}\nSession ID: d1a5b38ecedcaddffa6be5207b170568\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:285)\n\tat org.openqa.selenium.remote.RemoteWebElement.getAttribute(RemoteWebElement.java:134)\n\tat tests.legacy.GetRequestTest.Checkwp(GetRequestTest.java:70)\n\tat bindings.cucumber.funcjonal.steps.MyStepdefs.systemCheckLinsOnWpPl(MyStepdefs.java:92)\n\tat ✽.System Check lins on  wp.pl(file:///Users/muzi/IdeaProjects/firstseleniumtest23/src/test/resources/newLinks.feature:10)\n",
  "status": "failed"
});
formatter.scenario({
  "name": "Test links on happipozyczki.pl",
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
  "name": "System Check lins on  happipozyczki",
  "keyword": "Given "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.systemCheckLinsOnHappipozyczki()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Test links on creditea.es",
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
  "name": "System Check lins on  creditea es",
  "keyword": "Given "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.systemCheckLinsOnCrediteaEs()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Scenario: Test links on credit24.lt",
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
  "name": "System Check lins on  credit24.lt",
  "keyword": "Given "
});
formatter.match({
  "location": "bindings.cucumber.funcjonal.steps.MyStepdefs.systemCheckLinsOnCreditLt(int)"
});
formatter.result({
  "status": "passed"
});
});
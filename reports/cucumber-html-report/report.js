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
  "error_message": "org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document\n  (Session info: chrome\u003d83.0.4103.97)\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/stale_element_reference.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027MBP-muzi\u0027, ip: \u0027192.168.1.127\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.4\u0027, java.version: \u00271.8.0_241\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 83.0.4103.97, chrome: {chromedriverVersion: 83.0.4103.39 (ccbf011cb2d2b..., userDataDir: /var/folders/bd/rns_y2td5x3...}, goog:chromeOptions: {debuggerAddress: localhost:52301}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: MAC, platformName: MAC, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify, webauthn:virtualAuthenticators: true}\nSession ID: 739ef8040930000668dfe58323126774\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:285)\n\tat org.openqa.selenium.remote.RemoteWebElement.getAttribute(RemoteWebElement.java:134)\n\tat tests.legacy.GetRequestTest.CheckTvn(GetRequestTest.java:29)\n\tat bindings.cucumber.funcjonal.steps.MyStepdefs.systemChecksLinksOnTvn(MyStepdefs.java:85)\n\tat ✽.System checks links on tvn24(file:///Users/muzi/IdeaProjects/firstseleniumtest2/src/test/resources/newLinks.feature:5)\n",
  "status": "failed"
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

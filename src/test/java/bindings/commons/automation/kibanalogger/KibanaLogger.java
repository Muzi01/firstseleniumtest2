package bindings.commons.automation.kibanalogger;

import java.util.Random;

public class KibanaLogger {

  private String processId;
  private Integer processStep = 0;
  private String environment;
  private String country;

  public KibanaLogger() {
    final Random rand = new Random();
    setProcessId(rand.nextInt(999999999) + "");
  }

  public String getProcessId() {
    return this.processId;
  }

  public void setProcessId(final String processId) {
    this.processId = processId;
  }

  public Integer getProcessStep() {
    return this.processStep;
  }

  public Integer nextProcessStep() {
    return ++this.processStep;
  }

  public KibanaLogger kibanaLoggerWithNextProcessStep() {
    this.processStep++;
    return this;
  }

  public String getEnvironment() {
    return this.environment;
  }

  public void setEnvironment(final String environment) {
    this.environment = environment;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }
}

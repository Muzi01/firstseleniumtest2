package bindings.cucumber.linkstatusverification;


import java.util.Random;

public class KibanaLogger {
    private String processId;
    private Integer processStep = 0;
    private String environment;
    private String country;

    public KibanaLogger() {
        Random rand = new Random();
        this.setProcessId(rand.nextInt(999999999) + "");
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getProcessStep() {
        return this.processStep;
    }

    public Integer nextProcessStep() {
        return this.processStep = this.processStep + 1;
    }

    public KibanaLogger kibanaLoggerWithNextProcessStep() {
        Integer var2 = this.processStep;
        Integer var3 = this.processStep = this.processStep + 1;
        return this;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

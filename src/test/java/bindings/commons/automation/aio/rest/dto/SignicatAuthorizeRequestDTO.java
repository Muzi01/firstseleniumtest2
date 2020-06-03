package bindings.commons.automation.aio.rest.dto;

import feign.form.FormProperty;

public class SignicatAuthorizeRequestDTO {

  public String state;
  @FormProperty("redirect_uri")
  public String redirectUri;
  public String ssn;
  public String name;
  public String locale;
  public String gname;
  public String fname;
  public String bank;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getGname() {
    return gname;
  }

  public void setGname(String gname) {
    this.gname = gname;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }
}

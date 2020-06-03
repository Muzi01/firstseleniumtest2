package bindings.commons.automation.generator.utils.scores;

import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.ClosedAccountsNumber;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.FixedMortgagePaymentsAmount;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.HistoricAccountsNumber;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.MaximumCreditsAmount;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.NewAddress;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.ResidenceType;

public class ScoreMXInsertRequest {

  private String env;
  private String ssn;

  ResidenceType residenceType;
  Integer rs09;
  Integer rs13;
  ClosedAccountsNumber closedAccountsNumber;
  HistoricAccountsNumber historicAccountsNumber;
  NewAddress newAddress;
  MaximumCreditsAmount maximumCreditsAmount;
  FixedMortgagePaymentsAmount fixedMortgagePaymentsAmount;

  public String getEnv() {
    return this.env;
  }

  public void setEnv(final String env) {
    this.env = env;
  }

  public String getSsn() {
    return this.ssn;
  }

  public void setSsn(final String ssn) {
    this.ssn = ssn;
  }

  public ResidenceType getResidenceType() {
    return this.residenceType;
  }

  public void setResidenceType(final ResidenceType residenceType) {
    this.residenceType = residenceType;
  }

  public Integer getRs09() {
    return this.rs09;
  }

  public void setRs09(final Integer rs09) {
    this.rs09 = rs09;
  }

  public Integer getRs13() {
    return this.rs13;
  }

  public void setRs13(final Integer rs13) {
    this.rs13 = rs13;
  }

  public ClosedAccountsNumber getClosedAccountsNumber() {
    return this.closedAccountsNumber;
  }

  public void setClosedAccountsNumber(final ClosedAccountsNumber closedAccountsNumber) {
    this.closedAccountsNumber = closedAccountsNumber;
  }

  public HistoricAccountsNumber getHistoricAccountsNumber() {
    return this.historicAccountsNumber;
  }

  public void setHistoricAccountsNumber(final HistoricAccountsNumber historicAccountsNumber) {
    this.historicAccountsNumber = historicAccountsNumber;
  }

  public NewAddress getNewAddress() {
    return this.newAddress;
  }

  public void setNewAddress(final NewAddress newAddress) {
    this.newAddress = newAddress;
  }

  public MaximumCreditsAmount getMaximumCreditsAmount() {
    return this.maximumCreditsAmount;
  }

  public void setMaximumCreditsAmount(final MaximumCreditsAmount maximumCreditsAmount) {
    this.maximumCreditsAmount = maximumCreditsAmount;
  }

  public FixedMortgagePaymentsAmount getFixedMortgagePaymentsAmount() {
    return this.fixedMortgagePaymentsAmount;
  }

  public void setFixedMortgagePaymentsAmount(
      final FixedMortgagePaymentsAmount fixedMortgagePaymentsAmount) {
    this.fixedMortgagePaymentsAmount = fixedMortgagePaymentsAmount;
  }

  public String buildMessage() {
    return ScoreMX.getFullBDCMessage(this);
  }
}

package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.CreditApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditApplicationMapper implements ResultMapper<CreditApplication> {

  @Override
  public CreditApplication mapByResultSet(final ResultSet resultSet) throws SQLException {
    final CreditApplication creditApplication = new CreditApplication();
    creditApplication.id = resultSet.getLong("ID");
    creditApplication.entityVersion = resultSet.getInt("entityVersion");
    creditApplication.created = resultSet.getDate("created");
    creditApplication.bankScrapingReference = resultSet.getString("bankScrapingReference");
    creditApplication.firstDrawAmount = resultSet.getInt("firstDrawAmount");
    creditApplication.firstDueDate = resultSet.getDate("firstDueDate");
    creditApplication.rejectReason = resultSet.getString("rejectReason");
    creditApplication.state = resultSet.getString("state");
    creditApplication.customerId = resultSet.getLong("customer_ID");
    creditApplication.productId = resultSet.getLong("product_ID");
    creditApplication.target = resultSet.getString("target");
    creditApplication.loanType = resultSet.getString("loanType");
    creditApplication.targetExtraData = resultSet.getString("targetExtraData");
    creditApplication.session_uuid = resultSet.getString("session_uuid");
    creditApplication.externalId = resultSet.getString("externalId");
    creditApplication.referer = resultSet.getString("referer");
    creditApplication.applicationCode = resultSet.getString("applicationCode");
    creditApplication.financialDataChangeByCCVersion =
        resultSet.getInt("financialDataChangeByCCVersion");
    creditApplication.active = resultSet.getBoolean("active");
    creditApplication.rejectReasonCustomerCare = resultSet.getString("rejectReasonCustomerCare");
    creditApplication.mandatoryProvisioningFeeFactor =
        resultSet.getFloat("mandatoryProvisioningFeeFactor");
    return creditApplication;
  }
}

package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultMapper<Product> {


  @Override
  public Product mapByResultSet(final ResultSet resultSet) throws SQLException {
    final Product product = new Product();
    product.id = resultSet.getLong("ID");
    product.entityVersion = resultSet.getLong("entityVersion");
    product.apr = resultSet.getDouble("apr");
    product.closed = resultSet.getDate("closed");
    product.country = resultSet.getString("country");
    product.hidden = resultSet.getBoolean("hidden");
    product.interest = resultSet.getDouble("interest");
    product.annualInterest = resultSet.getDouble("annualInterest");
    product.maturityPeriodLength = resultSet.getString("maturityPeriodLength");
    product.maturityPeriods = resultSet.getInt("maturityPeriods");
    product.name = resultSet.getString("name");
    product.minimumWithdrawalFee = resultSet.getInt("minimumWithdrawalFee");
    product.openingFee = resultSet.getInt("openingFee");
    product.invoicingFee = resultSet.getInt("invoicingFee");
    product.principal = resultSet.getInt("principal");
    product.requiredScore = resultSet.getDouble("requiredScore");
    product.requiredScoreRepeat = resultSet.getDouble("requiredScoreRepeat");
    product.requiredScoreRepeatCase2 = resultSet.getDouble("requiredScoreRepeatCase2");
    product.type = resultSet.getString("type");
    product.withdrawalFeePercentage = resultSet.getDouble("withdrawalFeePercentage");
    product.updated = resultSet.getTimestamp("updated");
    product.withdrawalFixedFee = resultSet.getInt("withdrawalFixedFee");
    product.groupMinScore = resultSet.getInt("groupMinScore");
    product.groupMaxScore = resultSet.getInt("groupMaxScore");
    product.brand = resultSet.getString("brand");
    product.groupName = resultSet.getString("groupName");
    product.groupMinScoreRepeat = resultSet.getInt("groupMinScoreRepeat");
    product.groupMaxScoreRepeat = resultSet.getInt("groupMaxScoreRepeat");
    product.created = resultSet.getTimestamp("created");
    product.scope = resultSet.getString("scope");
    product.version = resultSet.getString("version");
    product.customerOldNewType = resultSet.getString("customerOldNewType");
    product.establishmentFee = resultSet.getInt("establishmentFee");
    product.provisioningFee = resultSet.getInt("provisioningFee");
    return product;
  }
}

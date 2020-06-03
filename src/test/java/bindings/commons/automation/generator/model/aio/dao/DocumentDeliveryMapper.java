package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentDeliveryMapper implements ResultMapper<DocumentDelivery> {

  @Override
  public DocumentDelivery mapByResultSet(final ResultSet resultSet) throws SQLException {
    final DocumentDelivery documentDelivery = new DocumentDelivery();
    documentDelivery.id = resultSet.getLong("ID");
    documentDelivery.entityVersion = resultSet.getLong("entityVersion");
    documentDelivery.created = resultSet.getDate("created");
    documentDelivery.externalId = resultSet.getString("externalId");
    documentDelivery.externalSentStamp = resultSet.getDate("externalSentStamp");
    documentDelivery.externalDocId = resultSet.getString("externalDocId");
    documentDelivery.data = resultSet.getBlob("data");
    documentDelivery.submitted = resultSet.getDate("submitted");
    documentDelivery.type = resultSet.getString("type");
    documentDelivery.country = resultSet.getString("country");
    documentDelivery.subType = resultSet.getString("subType");
    documentDelivery.deliveryMethod = resultSet.getString("deliveryMethod");
    documentDelivery.customerId = resultSet.getLong("customer_ID");
    documentDelivery.status = resultSet.getString("status");
    documentDelivery.updated = resultSet.getTimestamp("updated");
    documentDelivery.errorCode = resultSet.getString("errorCode");
    documentDelivery.emailId = resultSet.getString("emailId");
    return documentDelivery;
  }
}

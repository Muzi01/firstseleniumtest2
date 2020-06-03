package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper
    implements ResultMapper<com.ipfdigital.automation.generator.model.aio.dao.Invoice> {

  @Override
  public Invoice mapByResultSet(final ResultSet resultSet) throws SQLException {
    final Invoice invoice = new Invoice();
    invoice.id = resultSet.getLong("ID");
    invoice.entityVersion = resultSet.getLong("entityVersion");
    invoice.amount = resultSet.getInt("amount");
    invoice.dueDate = resultSet.getDate("due_date");
    invoice.invoiceDate = resultSet.getDate("invoiceDate");
    invoice.invoiceNumber = resultSet.getString("invoiceNumber");
    invoice.referenceNumber = resultSet.getString("referenceNumber");
    invoice.invoiceType = resultSet.getString("invoice_type");
    invoice.contractId = resultSet.getLong("contract_ID");
    invoice.documentDeliveryId = resultSet.getLong("documentDelivery_ID");
    invoice.updated = resultSet.getTimestamp("updated");
    invoice.closed = resultSet.getDate("closed");
    invoice.stateType = resultSet.getString("stateType");
    invoice.parentInvoiceId = resultSet.getLong("parentInvoice_ID");
    invoice.reminderSMSId = resultSet.getLong("reminderSMS_ID");
    invoice.onHold = resultSet.getDate("onHold");
    invoice.childInvoicesOrder = resultSet.getInt("childInvoices_ORDER");
    invoice.created = resultSet.getDate("created");

    return invoice;
  }
}

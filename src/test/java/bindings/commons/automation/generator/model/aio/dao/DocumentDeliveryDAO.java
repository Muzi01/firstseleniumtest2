package bindings.commons.automation.generator.model.aio.dao;

public interface DocumentDeliveryDAO extends GenericDAO, ShiftingDatesDAO {

  DocumentDelivery getLastDocumentDeliveryByCustomerId(String env, Long customerId);
}

package bindings.commons.automation.generator.model.aio.dao;

public interface UserRegistrationRequestDAO extends ShiftingDatesDAO {

  void verifyIdentification(String env, String identifier);
}

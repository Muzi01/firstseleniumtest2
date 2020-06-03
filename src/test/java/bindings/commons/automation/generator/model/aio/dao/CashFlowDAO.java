package bindings.commons.automation.generator.model.aio.dao;

public interface CashFlowDAO extends ShiftingDatesDAO {

  void updateCashFlowValueDateBack(String env, Integer days,
      Long customerId);
}

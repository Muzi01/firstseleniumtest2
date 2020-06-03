package bindings.commons.automation.generator.model.aio.dao;

public interface ShiftingDatesDAO {
  /**
   * Shifts dates relevant to Invoicing process.
   *
   * @param env environment name (such as "st01", "uat")
   * @param days number of days to be subtracted (positive value) or added (negative value)
   * @param customerId aio ID of the chosen customer
   */
  default void shiftDates(final String env, final Integer days, final Long customerId) {
    if (days >= 0) {
      shiftDatesBackward(env, days, customerId);
    } else {
      shiftDatesForward(env, Math.abs(days), customerId);
    }
  }

  void shiftDatesForward(String env, Integer days, Long customerId);

  void shiftDatesBackward(String env, Integer days, Long customerId);
}

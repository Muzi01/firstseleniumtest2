package bindings.commons.automation.customer.documents.australian;

import com.ipfdigital.automation.api.customer.MedicareGenerator;
import com.ipfdigital.automation.customer.australian.MedicareDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

final class MedicareGeneratorService implements MedicareGenerator {

  private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9};

  @Override
  public MedicareDTO generate() {
    final int irn = RandomUtils.randomInt(9) + 1;
    return new MedicareDTO(irn, generateCardNumber(), generateExpiryDate());
  }

  private Long generateCardNumber() {
    final int randomNumber = 20000000 + RandomUtils.randomInt(50000000);
    final Long result = ((long) (randomNumber * 10)) + checksum(randomNumber);
    return result * 10 + RandomUtils.randomInt(10);
  }

  private int checksum(int number) {
    int sum = 0;
    for (int i = 7; i >= 0; i--) {
      sum += (number % 10) * WEIGHTS[i];
      number /= 10;
    }
    return sum % 10;
  }

  /**
   * Regular (green) Medicare cards have expiry year and month only. Thus expiry date is set for
   * last day of given month (to match)
   */
  private String generateExpiryDate() {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
    final LocalDate minValidDate = LocalDate.now().with(lastDayOfMonth());
    final LocalDate maxValidDate = LocalDate.of(2027, 12, 31);
    final long monthsBetween = ChronoUnit.MONTHS.between(minValidDate, maxValidDate);
    return minValidDate.plusMonths(RandomUtils.randomLong(monthsBetween))
        .with(lastDayOfMonth())
        .format(formatter);
  }
}

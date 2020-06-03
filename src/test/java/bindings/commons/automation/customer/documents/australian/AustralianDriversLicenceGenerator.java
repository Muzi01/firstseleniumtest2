package bindings.commons.automation.customer.documents.australian;

import com.ipfdigital.automation.api.customer.DriversLicenceGenerator;
import com.ipfdigital.automation.customer.State;
import com.ipfdigital.automation.customer.australian.AustralianDriversLicenceDTO;
import com.ipfdigital.automation.generators.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

final class AustralianDriversLicenceGenerator implements DriversLicenceGenerator {

  @Override
  public AustralianDriversLicenceDTO generate() {
    return new AustralianDriversLicenceDTO(
        RandomUtils.randomEnum(State.class),
        generateExpiryDate(),
        generateLicenceNumber());

  }

  private String generateExpiryDate() {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
    final LocalDate minValidDate = LocalDate.now();
    final LocalDate maxValidDate = LocalDate.of(2037, 12, 31);
    final long daysBetween = ChronoUnit.DAYS.between(minValidDate, maxValidDate);
    return minValidDate.plusDays(RandomUtils.randomLong(daysBetween))
        .format(formatter);
  }

  /**
   * Based on DriversLicenceGen.java from SoapUI generator lib. Unable to confirm if this algorithm
   * is valid. It's not an exact copy - refer to bitbucket for details.
   *
   * @return a drivers licence number
   */
  private String generateLicenceNumber() {
    final int length = RandomUtils.randomInt(4) + 6;
    final int numbersLength = length - 2;
    final String number = RandomStringUtils.randomNumeric(numbersLength);
    final String letters = RandomStringUtils.randomAlphabetic(2).toUpperCase();
    int letterPosition = RandomUtils.randomInt(length - 3);
    if (letterPosition == 1 || letterPosition == 2 || letterPosition == 3)
      letterPosition = 4;
    return number.substring(0, letterPosition) + letters + number.substring(letterPosition);
  }

}

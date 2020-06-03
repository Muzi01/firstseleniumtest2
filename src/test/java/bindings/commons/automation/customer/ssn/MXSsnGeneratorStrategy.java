package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;

import java.time.LocalDate;

import static com.ipfdigital.automation.generators.RandomUtils.randomInt;

final class MXSsnGeneratorStrategy implements GenerateSsnStrategy {

  private final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
  private final char[] CONSONANTS = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
      'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z'};
  // Unused states due to application rejection (MX market rules): DG, GR, OA, TM, ZA, NE
  private final String[] STATE_CODES =
      {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "GT", "HG", "JC", "MC", "MN", "MS",
          "NT", "NL", "OC", "PL", "QR", "QT", "SP", "SL", "SR", "TC", "TS", "TL", "VZ", "YN", "ZS"};

  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    return String.valueOf(randomLetters(1) + // first surname initial
        randomVowel() + // surname first vowel
        randomLetters(2) + // second surname and first name initial
        SsnHelper.parseYearAsString(dto.getBirthDate()) +
        SsnHelper.parseMonthAsString(dto.getBirthDate()) +
        SsnHelper.parseDayAsString(dto.getBirthDate()) +
        genderLetter(dto.getGender()) +
        randomStateCode() +
        threeRandomConsonants() + // first surname, second surname and first name second consonant
        serial(dto.getBirthDate()));
  }

  private char genderLetter(final Gender gender) {
    if (gender == Gender.FEMALE)
      return 'M';
    return 'H';
  }

  private String randomLetters(final int numberOfLetters) {
    final StringBuilder letters = new StringBuilder();
    for (int i = 0; i < numberOfLetters; i++) {
      letters.append((char) ('A' + randomInt(26)));
    }
    return letters.toString();
  }

  private char randomVowel() {
    return VOWELS[randomInt(VOWELS.length)];
  }

  private String threeRandomConsonants() {
    final StringBuilder consonants = new StringBuilder();
    for (int i = 0; i < 3; i++) {
      consonants.append(CONSONANTS[randomInt(CONSONANTS.length)]);
    }
    return consonants.toString();
  }

  private String randomStateCode() {
    return STATE_CODES[randomInt(STATE_CODES.length)];
  }

  private String serial(final LocalDate birthDate) {
    if (birthDate.getYear() < 2000) {
      return String.format(SsnHelper.PARSE_02_TEMPLATE, randomInt(100));
    }
    return String.valueOf(randomLetters(2));
  }
}

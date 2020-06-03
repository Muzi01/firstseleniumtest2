package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class AUNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES =
      Arrays.asList("Noah", "Jack", "William", "Lucas",
          "Mason", "Charlie", "Max", "Jackson", "Harrison", "Thomas", "Leo", "Hunter", "Henry",
          "Xavier", "Liam",
          "Patrick", "Nick", "Tony", "Norrie");
  private static final List<String> FEMALE_FIRST_NAMES =
      Arrays.asList("Andrea", "Ella", "Ivy", "Zoe", "Isabelle", "Sophie",
          "Chloe", "Harper", "Scarlett", "Emily", "Evie", "Mia");
  private static final List<String> LAST_NAMES = Arrays.asList("Adams", "Allen", "Anderson",
      "Bailey",
      "Baker", "Barnes", "Bell", "Bennett", "Brooks",
      "Brown", "Butler", "Campbell", "Carter", "Chapman", "Chen", "Clark", "Collins", "Cook",
      "Cooper", "Cox", "Davis", "Edwards", "Ellis",
      "Evans", "Fisher", "Foster", "Fox", "Fraser", "Graham", "Gray", "Green", "Griffiths",
      "Hall", "Hamilton", "Harris", "Harrison", "Henderson",
      "Howard", "Hughes", "James", "Johnson", "Jones", "Kelly", "Kennedy", "King", "Kumar", "Lee",
      "Lewis", "Li", "Marshall",
      "Mason", "McDonald", "Miller", "Mitchell", "Moore", "Morgan", "Morris", "Murphy", "Murray",
      "Nelson", "Nguyen", "O'Brien", "O'Connor",
      "O'Sullivan", "Parker", "Patel", "Perry", "Peterson", "Phillips", "Powell", "Price", "Reed",
      "Reynolds", "Richardson", "Roberts", "Robertson",
      "Robinson", "Rogers", "Ross", "Russel", "Ryan", "Sanders", "Scott", "Shaw", "Simpson",
      "Singh", "Smith", "Stevens", "Stewart", "Taylor",
      "Thompson", "Turner", "Walker", "Walsh", "Wang", "Ward", "Watson", "White", "Williams",
      "Wilson", "Wood", "Wright", "Young", "Zhang",
      "Joona", "Joonas", "Jooseppi", "Jorma", "Jouko", "Jouni", "Juha", "Juhana", "Juhani",
      "Juho", "Jukka", "Jussi", "Juuso", "Jyri", "Jyrki",
      "Kaapo", "Kaapro", "Kaarle", "Kaarlo", "Kai", "Kaleva", "Kalevi", "Kalle", "Kari", "Karl",
      "Kauko", "Kim", "Kimi", "Klaus",
      "Kustaa", "Kusti", "Kyösti", "Lari", "Lars", "Lasse", "Lassi", "Launo", "Lauri", "Leevi",
      "Leo", "Luukas", "Mainio", "Manu", "Markku",
      "Markus", "Martti", "Matias", "Matti", "Mauno", "Maunu", "Mauri", "Mika", "Mikael", "Mikko",
      "Miska", "Nestori", "Niilo",
      "Niklas", "Niko", "Nooa", "Oiva", "Olli", "Onni", "Oskari", "Osku", "Otso", "Otto",
      "Paavali", "Pasi", "Pauli", "Pekka",
      "Pentti", "Pertti", "Perttu", "Petteri", "Pietari", "Pyry", "Raimo", "Ransu", "Reijo",
      "Reima", "Reino", "Reko", "Rikhard", "Riku",
      "Risto", "Roni", "Roope", "Roopertti", "Ruuben", "Sakari", "Sakke", "Saku", "Sami");

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }

  @Override
  public String generateFirstName(final Gender gender) {
    if (gender == Gender.MALE) {
      return RandomUtils.randomFromList(MALE_FIRST_NAMES);
    }
    return RandomUtils.randomFromList(FEMALE_FIRST_NAMES);
  }

  @Override
  public String generateLastName(final Gender gender) {
    return RandomUtils.randomFromList(LAST_NAMES);
  }
}

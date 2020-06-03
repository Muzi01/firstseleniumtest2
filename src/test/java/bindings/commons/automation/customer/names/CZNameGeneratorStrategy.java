package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class CZNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Abdon", "Abrahám", "Adam", "Adolf", "Alan", "Albert", "Aleš", "Alexandr", "Alexej",
      "Alojz", "Ambrož", "Andrej", "Anton", "Antonín", "Arnošt", "Artur", "Augustýn",
      "Barnabáš", "Bartoloměj", "Bedřich", "Benedikt", "Benjamín", "Bernard", "Blahoslav",
      "Blažej", "Bogdan", "Bohdan", "Bohumil", "Bohumír", "Bohuslav", "Bolek",
      "Boleslav", "Bonifác", "Bořek", "Boris", "Bořivoj", "Boyan", "Branimir", "Branislav",
      "Břetík", "Břetislav", "Bronislav", "Bruno", "Čeněk", "Čestmír", "Chrudoš",
      "Ctibor", "Ctirad", "Cyril", "Dalibor", "Dalimil", "Daniel", "David", "Dobroslav",
      "Dominik", "Drahoslav", "Dušan", "Edík", "Eduard", "Emánek", "Emanuel",
      "Emil", "Erik", "Evžen", "Evženek", "Felix", "Ferdinand", "Fero", "Filip", "František",
      "Gabriel", "Goran", "Gustav", "Havel", "Herbert", "Heřman", "Honza",
      "Horymír", "Hubert", "Hugo", "Hynek", "Ignác", "Igor", "Ilja", "Ivan", "Ivo", "Izák",
      "Jáchym", "Jakub", "Jan", "Ján", "Jarek", "Jarmil", "Jaromir", "Jaromír",
      "Jaroslav", "Jeroným", "Jindřich", "Jiří", "Jonáš", "Josef", "Jozef", "Julek", "Julius",
      "Jura", "Juraj", "Kamil", "Karel", "Karol", "Kašpar", "Kazimír",
      "Klement", "Kristian", "Kristián", "Kryštof", "Kubík", "Květoslav", "Kvido", "Ladislav",
      "Leopold", "Leoš", "Libor", "Lojzík", "Lubomir", "Lubomír", "Luboš",
      "Ludomir", "Ludvík", "Lukáš", "Lumír", "Marcel", "Marek", "Marián", "Maroš", "Martin",
      "Matěj", "Matouš", "Matyáš", "Matýsek", "Maxim", "Maxmilián", "Medard",
      "Metoděj", "Michael", "Michal", "Mikuláš", "Milan", "Milivoj", "Milorad", "Miloš",
      "Miloslav", "Miroslav", "Mojmír", "Mořic", "Nikola", "Nikolka", "Norbert",
      "Novak", "Oldík", "Oldřich", "Oleg", "Oliver", "Ondra", "Ondrej", "Ondřej", "Oskar", "Ota",
      "Otakar", "Otík", "Otmar", "Oto", "Otokar", "Pankrác", "Patrik",
      "Pavel", "Pavlík", "Pavol", "Petr", "Pravoslav", "Přemysl", "Prokop", "Radan", "Radek",
      "Radim", "Radomir", "Radomír", "Radoslav", "Radovan", "Ratimir", "Ratko",
      "Řehoř", "Řehořek", "René", "Richard", "Robert", "Robin", "Roland", "Roman", "Rostislav",
      "Rudolf", "Samuel", "Sáva", "Šebestián", "Servác", "Silvestr", "Šimon",
      "Slávek", "Slavoj", "Slavomír", "Soběslav", "Stanimir", "Stanislav", "Štefan", "Štěpán",
      "Svatopluk", "Svatoslav", "Sylvester", "Tadeáš", "Teodor", "Tibor",
      "Tomáš", "Toník", "Václav", "Valdemar", "Valentin", "Valentýn", "Vašík", "Vavřinec",
      "Velimir", "Věnceslav", "Vendelín", "Venoušek", "Věroslav", "Viktor",
      "Vilém", "Vincenc", "Vít", "Vítězslav", "Vitomir", "Vladan", "Vladimir", "Vladimír",
      "Vladislav", "Vlastimil", "Vlastislav", "Vojtech", "Vojtěch", "Vratislav",
      "Záviš", "Zawisza", "Zbigniew", "Zbyšek", "Zdeněk", "Zdenko", "Zikmund", "Zoran");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Amelia", "Angelika", "Barbora", "Branka", "Dagmar", "Danica", "Darina", "Drahomíra",
      "Elena", "Emma",
      "Gorana", "Helena", "Ivana", "Iveta", "Jarmila", "Jolana", "Lada", "Lena", "Liana",
      "Liběna", "Ljuba",
      "Lucia", "Ludmila", "Magdalena", "Mahulena", "Mária", "Maria", "Marika", "Marta", "Milada",
      "Milena",
      "Miluše", "Minna", "Mira", "Monika", "Nadia", "Natasha", "Nikola", "Nikoleta", "Olga",
      "Olivia", "Radmila",
      "Romana", "Ružena", "Sophia", "Stanislava", "Stefania", "Svetlana", "Tamara", "Tatiana",
      "Veronica", "Vesna",
      "Viera", "Vilma", "Vladana", "Yvette", "Zora", "Zuzana");
  private static final List<String> LAST_NAMES = Arrays.asList(
      "Novak", "Novotny", "Svoboda", "Dvorak", "Cerny", "Vesely", "Prochazka", "Pokorny",
      "Kucera", "Jelinek", "Hajek", "Ruzicka", "Zeman", "Fiala", "Kral", "Benes", "Marek",
      "Cermak", "Horak", "Kratochvil");

  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
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

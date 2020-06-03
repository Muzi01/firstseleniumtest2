package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class PLNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Adalbert", "Ada", "Adrian", "Albert", "Albin", "Aleks", "Aleksander", "Aleksy", "Alfons",
      "Alfred",
      "Alojzy", "Ambroży", "Anastazy", "Anatol", "Andrzej", "Antoni", "Apolinary", "Arkadiusz",
      "Aron",
      "August", "Augustyn", "Aureliusz", "Bartek", "Bartłomiej", "Bartosz", "Bazyli", "Benedykt",
      "Bernard",
      "Błażej", "Bogdan", "Bogumił", "Bogusław", "Bolek", "Bolesław", "Bonifacy", "Borys",
      "Bożydar", "Bratumił",
      "Bronimir", "Bronisław", "Celestyn", "Cezary", "Cibor", "Cyprian", "Cyryl", "Czcibor",
      "Czesław",
      "Damian", "Dan", "Darek", "Dariusz", "Dawid", "Dobrogost", "Dobromił", "Dobrosław",
      "Dominik",
      "Donat", "Edmund", "Edward", "Eliasz", "Eligiusz", "Emil", "Ernest", "Eryk", "Eugeniusz",
      "Fabian",
      "Felicjan", "Feliks", "Ferdynand", "Filip", "Florian", "Franciszek", "Fryderyk", "Gaweł",
      "Gerard", "Gerwazy", "Gracjan", "Grzegorz", "Gustaw", "Henryk", "Herbert", "Hipolit",
      "Hubert", "Ignacy",
      "Igor", "Ireneusz", "Iwan", "Iwo", "Izaak", "Izydor", "Jacek", "Jacenty", "Jakub", "Jan",
      "Janek", "Janusz",
      "Jarek", "Jarogniew", "Jaromir", "Jaropełk", "Jarosław", "Jędrzej", "Jerzy", "Joachi",
      "Jozafat", "Józef",
      "Julek", "Julian", "Juliusz", "Jurek", "Justyn", "Kacper", "Kajetan", "Kamil", "Karol",
      "Kazik",
      "Kazimierz", "Klaudiusz", "Klemens", "Kondrat", "Konrad", "Konstanty", "Konstantyn",
      "Kornel", "Krystian",
      "Krystyn", "Krzesimir", "Krzyś", "Krzysiek", "Krzysztof", "Ksawery", "Kuba", "Lech",
      "Lechosław", "Leon",
      "Leopold", "Lesław", "Leszek", "Lew", "Longin", "Lubomierz", "łucjan", "Lucjan", "Lucjusz",
      "Ludwik",
      "łukasz", "Maciej", "Maksy", "Maksymilian", "Manfred", "Marcel", "Marceli", "Marcin",
      "Marek",
      "Marian", "Mariusz", "Mateusz", "Maurycy", "Metody", "Michał", "Mieczysław", "Mieszko",
      "Mikołaj",
      "Miłogost", "Miłosław", "Miłosz", "Mirek", "Miron", "Mirosław", "Mścisław", "Narcyz",
      "Nikode", "Norbert",
      "Olaf", "Olek", "Oliwer", "Oliwier", "Oskar", "Patryk", "Paweł", "Piotr", "Przemek",
      "Przemko", "Przemo",
      "Przemysł", "Przemysław", "Racław", "Radek", "Radomił", "Radosław", "Radzi", "Radzimierz",
      "Rafał",
      "Rajmund", "Remigiusz", "Robert", "Roch", "Roman", "Rudolf", "Ryszard", "Sebastian",
      "Serafin",
      "Sergiusz", "Seweryn", "Siemowit", "Sławomir", "Sobiesław", "Stanisław", "Stefan", "Stefek",
      "Sulisław",
      "świętomierz", "świętopełk", "świętosław", "Sylwester", "Szczepan", "Szczęsny", "Szymon",
      "Tadeusz",
      "Teodor", "Teofil", "Tobiasz", "Tomasz", "Tomek", "Tymon", "Tymoteusz", "Tytus", "Urban",
      "Uriasz",
      "Wacław", "Waldek", "Waldemar", "Walenty", "Walerian", "Walery", "Walter", "Warcisław",
      "Wawrzyniec",
      "Więcesław", "Wielisław", "Wiesław", "Wiktor", "Wilhel", "Wincenty", "Wisław", "Wit",
      "Witek", "Witołd",
      "Witold", "Władek", "Władysław", "Włodek", "Włodzimierz", "Włodzisław", "Wojciech",
      "Wojtek", "Zachariasz",
      "Zawisza", "Zbigniew", "Zdzisław", "Zenon", "Ziemowit", "Zygfryd", "Zygmunt");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Ada", "Adelajda", "Adrianna", "Agnieszka", "Aldona", "Aleksandra",
      "Alfreda", "Alicja", "Alina", "Anastazja", "Andżelika", "Angelika", "Ania", "Aniela",
      "Anielka", "Anka", "Anna", "Antonina", "Asia", "Augusta",
      "Augustyna", "Barbara", "Basia", "Beata", "Beatrycze", "Benedykta", "Bianka", "Blanka",
      "Bogna", "Bogumiła", "Bogusława", "Bolesława", "Bożena", "Bronisława", "Brygida", "Cecylia",
      "Celestyna",
      "Celina", "Czesława", "Dagmara", "Danka", "Danuta", "Daria", "Dobrosława", "Dominika",
      "Dorota",
      "Dosia", "Edyta", "Ela", "Eleonora", "Eliza", "Elwira", "Elżbieta", "Estera", "Eunika",
      "Ewa",
      "Ewelina", "Felicja", "Felicyta", "Filipina", "Franciszka", "Fryderyka",
      "Gaja", "Genowefa", "Gertruda", "Gosia", "Gracja", "Grażyna", "Halina", "Hania",
      "Henryka", "Honorata", "Iga", "Ignacja", "Irena", "Irenka", "Iwona", "Iza", "Izabela",
      "Izabella",
      "Izolda", "Jadwiga", "Jadzia", "Jagienka", "Jagna", "Jagoda", "Jagusia", "Jarosława",
      "Joanna",
      "Joasia", "Jola", "Jolanta", "Jowita", "Józefa", "Józefina", "Judyta", "Julianna", "Julita",
      "Justyna", "Kaja", "Kalina", "Kamila", "Karina", "Karolina", "Kasandra", "Kasia",
      "Katarzyna", "Kazia",
      "Kazimiera", "Kinga", "Klara", "Klaudia", "Klementyna", "Konstancja", "Kornelia", "Krysia",
      "Krystiana",
      "Krystyna", "Ksenia", "Kunegunda", "Lechosława", "Lena", "Leokadia", "Lesława", "Lidka",
      "Liwia", "Longina", "łucja", "Lucja", "Lucyna", "Ludmiła", "Ludwika", "Luiza", "Magda",
      "Maja", "Maja", "Małgorzata", "Małgosia", "Malina", "Malwina", "Marcelina",
      "Marianna", "Marlena", "Martyna", "Maryla", "Marzanna", "Marzena", "Matylda",
      "Michalina", "Mieczysława", "Milena", "Mira", "Mirosława", "Nadzieja", "Natalka",
      "Natasza", "Nikola", "Nina", "Ola", "Olga", "Oliwia", "Otylia", "Patka", "Patrycja",
      "Pelagia", "Petronela", "Pola", "Radomiła", "Radosława", "Renia", "Roksana",
      "Romana", "Róża", "Rozalia", "Ruta", "Salomea", "Sara", "Seweryna",
      "Sławomira", "Sobiesława", "Stanisława", "Stefania", "Stefcia", "Sybilla", "Sylwia",
      "Teodozja", "Teofila", "Tosia", "Ula", "Urszula", "Wacława", "Walentyna", "Waleria",
      "Wanda", "Wera", "Weronika", "Wiesława", "Wiktoria", "Wiola", "Wioleta", "Wioletta",
      "Wisława",
      "Władysława", "Wojciecha", "żaklina", "Zdzisława", "Zofia", "Zosia", "Zula", "Zuza",
      "Zuzanna", "Zuzia",
      "Zyta");
  private static final List<String> MALE_LAST_NAMES = Arrays.asList(
      "Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski",
      "Zieliński",
      "Szymański", "Wożniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Kwiatkowski",
      "Wojciechowski",
      "Krawczyk", "Kaczmarek", "Piotrowski", "Grabowski");
  private static final List<String> FEMALE_LAST_NAMES = Arrays.asList(
      "Nowak", "Kowalska", "Wiśniewska", "Wójcik", "Kowalczyk", "Kamińska", "Lewandowska",
      "Zielińska",
      "Szymańska", "Wożniak", "Dąbrowska", "Kozłowska", "Jankowska", "Mazur", "Kwiatkowska",
      "Wojciechowska",
      "Krawczyk", "Kaczmarek", "Piotrowska", "Grabowska");

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
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
    if (gender == Gender.MALE) {
      return RandomUtils.randomFromList(MALE_LAST_NAMES);
    }
    return RandomUtils.randomFromList(FEMALE_LAST_NAMES);
  }
}

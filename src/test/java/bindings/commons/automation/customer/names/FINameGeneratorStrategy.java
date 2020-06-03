package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class FINameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Aabraha", "Aadolf", "Aapeli", "Aapo", "Aarne", "Aatami", "Aatos", "Aatto", "Aatu", "Ahti",
      "Aimo", "Aki",
      "Akseli", "Aku", "Ale", "Aleksanteri", "Aleksi", "Alpertti", "Altti", "Anselmi", "Anssi",
      "Antero", "Anton",
      "Antti", "Anttoni", "Ari", "Armas", "Armo", "Arto", "Arttu", "Artturi", "Arvo", "Atte",
      "Aukusti", "Aulis",
      "Edvard", "Edvin", "Eelis", "Eemeli", "Eemil", "Eerik", "Eerikki", "Eero", "Eetu", "Eino",
      "Elias", "Eljas",
      "Ensio", "Erik", "Erkki", "Erno", "Esa", "Filip", "Frans", "Fredrik", "Hannes", "Hannu",
      "Harri",
      "Heikki", "Heino", "Henri", "Henrikki", "Hermanni", "Hesekiel", "Iikka", "Iiro", "Iisakki",
      "Ilari",
      "Ilmari", "Into", "Ismo", "Jaakkima", "Jaakko", "Jaakob", "Jaakoppi", "Jalmari", "Jalo",
      "Jami", "Jani",
      "Janne", "Jari", "Jarkko", "Jarmo", "Jaska", "Jere", "Jeremias", "Joaki", "Joel",
      "Johannes", "Joni",
      "Joona", "Joonas", "Jooseppi", "Jorma", "Jouko", "Jouni", "Juha", "Juhana", "Juhani",
      "Juho", "Jukka",
      "Jussi", "Juuso", "Jyri", "Jyrki", "Kaapo", "Kaapro", "Kaarle", "Kaarlo", "Kai", "Kaleva",
      "Kalevi",
      "Kalle", "Kari", "Karl", "Kauko", "Kim", "Kimi", "Klaus", "Kristian", "Kustaa", "Kusti",
      "Kyösti", "Lari",
      "Lars", "Lasse", "Lassi", "Launo", "Lauri", "Leevi", "Leo", "Luukas", "Mainio", "Manu",
      "Markku",
      "Marko", "Markus", "Martin", "Martti", "Matias", "Matti", "Mauno", "Maunu", "Mauri", "Mika",
      "Mikael",
      "Mikko", "Miska", "Nestori", "Niilo", "Niklas", "Niko", "Nooa", "Oiva", "Olavi", "Olli",
      "Onni",
      "Oskari", "Osku", "Otso", "Otto", "Paavali", "Paavo", "Pasi", "Pauli", "Pekka", "Pentti",
      "Pertti",
      "Perttu", "Petri", "Petteri", "Pietari", "Pyry", "Raimo", "Ransu", "Reijo", "Reima",
      "Reino", "Reko",
      "Rikhard", "Riku", "Risto", "Roni", "Roope", "Roopertti", "Ruuben", "Sakari", "Sakke",
      "Saku", "Sami",
      "Sampo", "Samppa", "Samu", "Samuli", "Santeri", "Santtu", "Sauli", "Sebastian", "Sepi",
      "Seppo",
      "Severi", "Simo", "Sisu", "Soini", "Sulo", "Taavetti", "Taavi", "Tahvo", "Taisto", "Taneli",
      "Tapani",
      "Tapio", "Tarmo", "Tatu", "Tauno", "Teemu", "Teppo", "Terho", "Tero", "Teuvo", "Tiitus",
      "Timo", "Toivo",
      "Tomi", "Tommi", "Toni", "Topi", "Topias", "Torsti", "Tuomas", "Tuomo", "Tuukka", "Tuure",
      "Tyko", "Ukko",
      "Uolevi", "Urho", "Usko", "Väinö", "Valdemar", "Valto", "Valtteri", "Veeti", "Veikko",
      "Veli", "Vertti",
      "Vesa", "Vieno", "Vilhel", "Vilhelmi", "Vilho", "Vili", "Viljami", "Viljo", "Ville",
      "Vilppu", "Voitto",
      "Yrjänä", "Yrjö");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Aada", "Aamu", "Aava", "Ada", "Adele", "Aila", "Aili", "Aina", "Aino", "Ale", "Aliisa",
      "Alisa", "Alli",
      "Anja", "Anna", "Anna-liisa", "Anne", "Anneli", "Anni", "Anniina", "Annika", "Annikki",
      "Annukka",
      "Ansa", "Anu", "Arja", "Aune", "Birgitta", "Brita", "Camilla", "Eerika", "Eeva",
      "Eevi", "Eija", "Eini", "Eleonoora", "Eliina", "Eliisa", "Elina", "Ella", "Elli",
      "Elsa", "Emma", "Emmi", "Enni", "Erika", "Erja", "Essi", "Esteri", "Eveliina", "Fanni",
      "Fredrika", "Hanna", "Hanna", "Hannele", "Heidi", "Heleena", "Helena", "Heli", "Helka",
      "Hellä", "Helmi",
      "Henna", "Henrietta", "Henriikka", "Hilja", "Hillevi", "Iida", "Iina", "Iines", "Iiris",
      "Ilma",
      "Ilta", "Impi", "Inka", "Inkeri", "Irina", "Iris", "Irja", "Irma", "Jaana", "Janika",
      "Janina",
      "Janna", "Jasmin", "Jenna", "Jenni", "Johanna", "Jonna", "Josefiina", "Kaarina", "Kaija",
      "Kaisa", "Karin", "Karoliina", "Kata", "Katariina", "Kati", "Katja", "Katri", "Katriina",
      "Kerttu", "Kielo",
      "Kiira", "Kirsi", "Kirsikka", "Kirsti", "Krista", "Kristiina", "Kukka", "Kylli", "Kyllikki",
      "Lahja",
      "Laila", "Lea", "Leena", "Lempi", "Liina", "Liisa", "Liisi", "Lilja", "Lilli", "Linda",
      "Lotta",
      "Loviisa", "Lumi", "Lyydia", "Lyyti", "Maaria", "Maarika", "Maarit", "Maija", "Maire",
      "Manu", "Margareeta",
      "Margareta", "Mari", "Marianna", "Marianne", "Marika", "Maritta", "Marja", "Marjaana",
      "Marjatta", "Marjo", "Marjukka", "Marjut", "Marketta", "Martta", "Matilda", "Matleena",
      "Meri", "Merja",
      "Miia", "Miina", "Mikaela", "Milla", "Mimmi", "Minna", "Minttu", "Mirja", "Mirjam",
      "Mirjami", "Nea",
      "Niina", "Nina", "Noora", "Oili", "Oona", "Orvokki", "Päivä", "Päivi", "Pauliina",
      "Petra", "Pihla", "Piia", "Pilvi", "Pinja", "Piritta", "Pirjo", "Pirkko", "Priita",
      "Raakel", "Rauha",
      "Rebekka", "Reeta", "Reetta", "Riika", "Riikka", "Riina", "Riitta", "Ritva", "Ruut",
      "Saana", "Saara",
      "Säde", "Saija", "Salli", "Sanna", "Sanni", "Sara", "Sari", "Satu", "Seija", "Senja",
      "Sigrid", "Siiri",
      "Silja", "Sini", "Sinikka", "Sirpa", "Sisko", "Sohvi", "Soile", "Soili", "Sonja", "Stiina",
      "Suoma", "Susanna", "Suvi", "Sylvi", "Sylvia", "Tähti", "Taika", "Taimi", "Taina", "Tanja",
      "Tarja",
      "Taru", "Tea", "Teija", "Terhi", "Terttu", "Tiia", "Tiina", "Tilda",
      "Toini", "Tuija", "Tuula", "Tuuli", "Tuulikki", "Tyyne", "Ulla", "Ulriikka", "Ursula",
      "Valpuri", "Vanamo",
      "Vappu", "Varpu", "Veera", "Venla", "Vieno", "Viivi", "Vilhelmiina", "Vilja", "Virva",
      "Vuokko");
  private static final List<String> LAST_NAMES = Arrays.asList(
      "Korhonen", "Virtanen", "Mäkinen", "Nieminen", "Mäkelä", "Hämäläinen", "Laine", "Heikkinen",
      "Koskinen",
      "Järvinen", "Lehtonen", "Lehtinen", "Saarinen", "Salminen", "Heinonen", "Niemi", "Heikkilä",
      "Kinnunen",
      "Salonen", "Turunen", "Salo", "Laitinen", "Tuominen", "Rantanen", "Karjalainen", "Jokinen",
      "Mattila",
      "Savolainen", "Lahtinen", "Ahonen");

  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
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

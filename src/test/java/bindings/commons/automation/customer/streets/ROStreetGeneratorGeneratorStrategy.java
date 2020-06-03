package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class ROStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> RO_STREET_NAMES = Arrays.asList(
      "Stradă Academiei", "Stradă Edgar Quinet", "Stradă Biserica Enei", "Pasaj Comedia",
      "Pasaj Majestic",
      "Pasaj Victoriei", "Stradă Popişteanu Cristian", "Stradă Dobrescu I. Demetru",
      "Stradă Boteanu", "Intrare Georgescu Jean",
      "Stradă Câmpineanu Ion", "Bulevard Bălcescu Nicolae", "Intrare Micle Veronica",
      "Piaţă 21 Decembrie 1989",
      "Cale Victoriei", "Piaţă Revoluţiei", "Stradă Ştirbei Vodă", "Intrare Ştirbei Vodă",
      "Piaţă Ionesco Eugen",
      "Piaţetă Sava Iosif", "Stradă Actor Brezoianu Ion", "Stradă Mille Constantin");

  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(RO_STREET_NAMES);
  }
}

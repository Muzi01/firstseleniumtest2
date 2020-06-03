package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class MXCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Mexico City", "09010"),
      new CityDTO("Aguascalientes", "20117"),
      new CityDTO("Mexicali", "21371"),
      new CityDTO("Ciudad Victoria", "21960"),
      new CityDTO("Pachuca", "21980"),
      new CityDTO("Tijuana", "22476"),
      new CityDTO("Ensenada", "22842"),
      new CityDTO("La Paz", "23000"),
      new CityDTO("Ciudad del Carmen", "24100"),
      new CityDTO("Saltillo", "25008"),
      new CityDTO("Monclova", "25700"),
      new CityDTO("Piedras Negras", "26000"),
      new CityDTO("Ciudad Acuña", "26200"),
      new CityDTO("Torreón", "27009"),
      new CityDTO("Matamoros", "27449"),
      new CityDTO("Colima", "28000"),
      new CityDTO("Manzanillo", "28200"),
      new CityDTO("Ciudad de Villa de Álvarez", "28970"),
      new CityDTO("Tuxtla Gutiérrez", "29016"),
      new CityDTO("San Cristóbal de las Casas", "29200"),
      new CityDTO("Ojo de Agua", "29410"),
      new CityDTO("Chilpancingo", "29623"),
      new CityDTO("Campeche", "29635"),
      new CityDTO("Guaymas", "30488"),
      new CityDTO("Chihuahua", "31066"),
      new CityDTO("Juárez", "32190"),
      new CityDTO("Delicias", "33000"),
      new CityDTO("Hidalgo del Parral", "33800"),
      new CityDTO("Irapuato", "36691"),
      new CityDTO("Salamanca", "36700"),
      new CityDTO("León", "37549"),
      new CityDTO("Santa Catarina", "37950"),
      new CityDTO("Celaya", "38018"),
      new CityDTO("Puerto Vallarta", "42900"),
      new CityDTO("Tulancingo", "43600"),
      new CityDTO("Guadalajara", "44960"),
      new CityDTO("Zapopan", "45068"),
      new CityDTO("Tonalá", "45404"),
      new CityDTO("Tlaquepaque", "45630"),
      new CityDTO("Buenavista", "45850"),
      new CityDTO("Cuautla", "48150"),
      new CityDTO("Toluca", "50209"),
      new CityDTO("Tlalnepantla", "50274"),
      new CityDTO("Ciudad López Mateos", "52900"),
      new CityDTO("Naucalpan", "53000"),
      new CityDTO("Córdoba", "53229"),
      new CityDTO("Ciudad Nicolás Romero", "54474"),
      new CityDTO("Cuautitlán Izcalli", "54715"),
      new CityDTO("Cuautitlán", "54800"),
      new CityDTO("San Pablo de las Salinas", "54930"),
      new CityDTO("Ecatepec", "55020"),
      new CityDTO("Coacalco de Berriozábal", "55713"),
      new CityDTO("Tepexpan", "55885"),
      new CityDTO("Texcoco", "56100"),
      new CityDTO("Nezahualcóyotl", "56263"),
      new CityDTO("Chimalhuacán", "56335"),
      new CityDTO("Chicoloapan de Juárez", "56370"),
      new CityDTO("Ixtapaluca", "56576"),
      new CityDTO("Chalco de Díaz Covarrubias", "56600"),
      new CityDTO("Morelia", "58003"),
      new CityDTO("Uruapan", "60000"),
      new CityDTO("Cuernavaca", "62493"),
      new CityDTO("Jiutepec", "62550"),
      new CityDTO("Monterrey", "64268"),
      new CityDTO("San Pedro Garza García", "66200"),
      new CityDTO("San Nicolás de los Garza", "66486"),
      new CityDTO("Ciudad Apodaca", "66647"),
      new CityDTO("Guadalupe", "67123"),
      new CityDTO("Ciudad Benito Juárez", "67250"),
      new CityDTO("Puebla", "74160"),
      new CityDTO("Tehuacán", "75717"),
      new CityDTO("Querétaro", "76118"),
      new CityDTO("Chetumal", "77000"),
      new CityDTO("Cancún", "77528"),
      new CityDTO("Playa del Carmen", "77710"),
      new CityDTO("San Luis Potosí", "78008"),
      new CityDTO("Soledad de Graciano Sánchez", "78430"),
      new CityDTO("Ciudad Valles", "79000"),
      new CityDTO("Villahermosa", "86080"),
      new CityDTO("Xico", "91240"),
      new CityDTO("Veracruz", "91709"),
      new CityDTO("Ciudad Cuauhtémoc", "92030"),
      new CityDTO("Poza Rica", "92909"),
      new CityDTO("Orizaba", "94300"),
      new CityDTO("Nogales", "94720"),
      new CityDTO("Xalapa", "95805"),
      new CityDTO("Miramar", "96380"),
      new CityDTO("Coatzacoalcos", "96400"),
      new CityDTO("Minatitlán", "96700"),
      new CityDTO("Mérida", "97203"));

  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  @Override
  public CityDTO generate() {
    return RandomUtils.randomFromList(cities);
  }

  @Override
  public List<CityDTO> getCities() {
    return cities;
  }
}

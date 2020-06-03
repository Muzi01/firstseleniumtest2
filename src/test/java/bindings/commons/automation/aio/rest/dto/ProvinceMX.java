package bindings.commons.automation.aio.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProvinceMX {

  private static final Map<String, String> provinces = new HashMap<>();

  private ProvinceMX() {
  }

  public static String getProvinceGDPforProvince(final String province) {

    return provinces.get(province).toUpperCase();
  }

  public static List<String> getProvinces(final String provinceGDP) {
    return provinces.entrySet().stream().filter(e -> e.getValue()
        .contains(provinceGDP)).map(Map.Entry::getKey).collect(Collectors.toList());
  }

  static {
    provinces.put("AGS", "d2");
    provinces.put("BCN", "d2");
    provinces.put("BCS", "d2");
    provinces.put("CAM", "d3");
    provinces.put("CHS", "d1");
    provinces.put("CHI", "d2");
    provinces.put("COA", "d2");
    provinces.put("COL", "d2");
    provinces.put("CDMX", "d2");
    provinces.put("DGO", "d3");
    provinces.put("GTO", "d2");
    provinces.put("GRO", "d1");
    provinces.put("HGO", "d1");
    provinces.put("JAL", "d2");
    provinces.put("EM", "d2");
    provinces.put("MICH", "d1");
    provinces.put("MOR", "d2");
    provinces.put("NAY", "d1");
    provinces.put("NL", "d3");
    provinces.put("OAX", "d1");
    provinces.put("PUE", "d2");
    provinces.put("QRO", "d2");
    provinces.put("QR", "d3");
    provinces.put("SLP", "d2");
    provinces.put("SIN", "d2");
    provinces.put("SON", "d2");
    provinces.put("ZAC", "d1");
    provinces.put("TAB", "d2");
    provinces.put("TAM", "d2");
    provinces.put("TLA", "d1");
    provinces.put("VER", "d1");
    provinces.put("YUC", "d2");
  }
}

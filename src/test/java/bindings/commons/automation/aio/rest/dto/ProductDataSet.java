package bindings.commons.automation.aio.rest.dto;


public class ProductDataSet extends ProductDTO {

  public String type;
  public Integer scoreMin;
  public Integer scoreMax;

  public ProductDataSet(
      final String scoreMin, final String scoreMax, final String name, final String groupName,
      final String pricipal, final String maturity) {
    this.scoreMin = Integer.parseInt(scoreMin);
    this.scoreMax = Integer.parseInt(scoreMax);
    this.name = name;
    this.groupName = groupName;
    this.principal = Integer.parseInt(pricipal);
    this.maturityPeriods = Integer.parseInt(maturity);
  }

  public ProductDataSet(
      final String scoreMin, final String scoreMax, final String type, final String name,
      final String groupName, final String pricipal, final String maturity) {
    this.scoreMin = Integer.parseInt(scoreMin);
    this.scoreMax = Integer.parseInt(scoreMax);
    this.name = name;
    this.groupName = groupName;
    this.principal = Integer.parseInt(pricipal);
    this.maturityPeriods = Integer.parseInt(maturity);
    this.type = type;
  }

}

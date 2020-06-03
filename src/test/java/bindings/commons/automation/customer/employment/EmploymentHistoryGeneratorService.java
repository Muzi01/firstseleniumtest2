package bindings.commons.automation.customer.employment;

import com.ipfdigital.automation.api.customer.EmploymentGenerator;
import com.ipfdigital.automation.api.customer.EmploymentHistoryGenerator;
import com.ipfdigital.automation.api.customer.EmploymentTypeGenerator;
import com.ipfdigital.automation.generators.RandomUtils;

final class EmploymentHistoryGeneratorService implements EmploymentHistoryGenerator {

  private final EmploymentGenerator employmentGeneratorService;
  private final EmploymentTypeGenerator employmentTypeGenerator;

  public EmploymentHistoryGeneratorService(final EmploymentGenerator employmentGeneratorService,
      final EmploymentTypeGenerator employmentTypeGenerator) {
    this.employmentGeneratorService = employmentGeneratorService;
    this.employmentTypeGenerator = employmentTypeGenerator;
  }

  @Override
  public EmploymentHistoryDTO generate() {
    return new EmploymentHistoryDTO(
        RandomUtils.randomEnum(StudiesLevel.class),
        employmentTypeGenerator.generate(false),
        employmentGeneratorService.generate(),
        employmentGeneratorService.generate());
  }
}

package bindings.commons.automation.customer.employment;

import com.ipfdigital.automation.api.customer.EmploymentGenerator;
import com.ipfdigital.automation.api.customer.EmploymentTypeGenerator;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class EmploymentGeneratorService implements EmploymentGenerator {

  private static final List<String> employers = Arrays.asList(
      "Ernst & Young",
      "Swiss Credit",
      "IBM",
      "UNILEVER",
      "Amcor",
      "Ansell",
      "Arnott's Biscuits",
      "ASC Pty Ltd",
      "Coca-Cola Amatil",
      "Honeysuckle Development Corporation");

  private static final List<String> jobTitles = Arrays.asList(
      "Quality Assurance",
      "Security Officer",
      "Developer",
      "Nightly Builds Operator",
      "Administrator",
      "Account Manager");

  private final EmploymentTypeGenerator employmentTypeGenerator;

  public EmploymentGeneratorService(final EmploymentTypeGenerator employmentTypeGenerator) {
    this.employmentTypeGenerator = employmentTypeGenerator;
  }

  @Override
  public EmploymentDTO generate() {
    return new EmploymentDTO(
        RandomUtils.randomFromList(employers),
        RandomUtils.randomFromList(jobTitles),
        employmentTypeGenerator.generate(true),
        RandomUtils.randomEnum(JobDuration.class));
  }
}

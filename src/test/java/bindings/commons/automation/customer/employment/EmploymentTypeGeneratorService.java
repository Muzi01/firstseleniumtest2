package bindings.commons.automation.customer.employment;

import com.ipfdigital.automation.api.customer.EmploymentTypeGenerator;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class EmploymentTypeGeneratorService implements EmploymentTypeGenerator {
  @Override
  public EmploymentType generate(final Boolean isEmployed) {
    final List<EmploymentType> employmentTypeList = Arrays
        .stream(EmploymentType.values())
        .filter(e -> e.isEmployed() == isEmployed)
        .collect(Collectors.toList());
    return RandomUtils.randomFromList(employmentTypeList);
  }
}

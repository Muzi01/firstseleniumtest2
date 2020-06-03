package bindings.commons.automation.generator.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidateParameters {

  private ValidateParameters() {
    throw new IllegalStateException(
        "ValidateParameters is utility class and should not be instantiate!");
  }

  // ToDo: Replace with Spring validation in Generator 2.0
  public static List<String> getMissingFields(final Object object, final String excludePattern)
      throws IllegalAccessException {
    final List<String> missingFields = new ArrayList<>();
    final Class<?> objectClass = object.getClass();
    final Field[] fields = objectClass.getDeclaredFields();
    for (final Field field : fields) {
      final String fieldName = field.getName();
      final Object fieldValue = FieldUtils.readField(object, fieldName, true);
      if (Objects.isNull(fieldValue) && !excludePattern.contains(fieldName)) {
        missingFields.add(fieldName);
      }
    }

    return missingFields;
  }
}

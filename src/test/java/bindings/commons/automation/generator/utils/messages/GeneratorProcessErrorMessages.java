package bindings.commons.automation.generator.utils.messages;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorProcessErrorMessages {

  private static final String ERROR_500 = "500";
  private static final String ERROR_502 = "502";
  private static final String ERROR_504 = "504";
  private static final String ERROR_400 = "400";
  private static final String ERROR_401 = "401";
  private static final String NEXT_LINE = "<br>";
  private static final String BOLD_START = "<b>";
  private static final String BOLD_END = "</b>";
  private static final String ITALIC_START = "<i>";
  private static final String ITALIC_END = "</i>";
  private static final String TEST_AND_CREATE_BUG_MSG =
      "Please test process manually and if problem persists contact market developers and create a BUG! ";
  private static final String IF_PROBLEM_PERSISTS_CONTACT_AT =
      "If the problem doesn't occur during manual process, please  " + ITALIC_START + "ONLY THEN"
          + ITALIC_END + " contact AT team! ";

  private static final String ERROR_500_MSG = NEXT_LINE + NEXT_LINE + BOLD_START +
      TEST_AND_CREATE_BUG_MSG + NEXT_LINE + NEXT_LINE +
      IF_PROBLEM_PERSISTS_CONTACT_AT + BOLD_END;

  private static final String ERROR_502_MSG = NEXT_LINE + BOLD_START +
      "Probably there is a build of application, check proper slack channel for this Env! " +
      BOLD_END;

  private static final String ERROR_504_MSG = NEXT_LINE + NEXT_LINE +
      "Probably there is a problem with connection to external service. " +
      NEXT_LINE + BOLD_START + "Please contact market developers " +
      BOLD_END;

  private static final String ERROR_400_MSG = NEXT_LINE + NEXT_LINE +
      "Probably there is a problem with data used by generator for this request. " +
      NEXT_LINE + NEXT_LINE + BOLD_START + TEST_AND_CREATE_BUG_MSG +
      NEXT_LINE + NEXT_LINE + IF_PROBLEM_PERSISTS_CONTACT_AT + BOLD_END;

  private static final String ERROR_401_MSG = NEXT_LINE + NEXT_LINE +
      "Probably there is a problem with authentication. " +
      NEXT_LINE + NEXT_LINE + BOLD_START + TEST_AND_CREATE_BUG_MSG +
      NEXT_LINE + NEXT_LINE + IF_PROBLEM_PERSISTS_CONTACT_AT + BOLD_END;

  private GeneratorProcessErrorMessages() {
  }

  public static String createErrorMessage(final String responseCode) {
    switch (responseCode) {
      case ERROR_400:
        return ERROR_400_MSG;
      case ERROR_500:
        return ERROR_500_MSG;
      case ERROR_502:
        return ERROR_502_MSG;
      case ERROR_504:
        return ERROR_504_MSG;
      case ERROR_401:
        return ERROR_401_MSG;
      default:
        return "";
    }
  }

  public static String extractErrorCode(final String message) {
    String errorCode = "";
    final Pattern p = Pattern.compile("\\d{3}");
    final Matcher m = p.matcher(message);
    if (m.find()) {
      errorCode = m.group(0);
    }
    return errorCode;
  }
}

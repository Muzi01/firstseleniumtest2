package bindings.commons.automation.aio.soap;

import com.ipfdigital.automation.aio.soap.exceptions.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

public class XMLUtils {

  private static final Logger LOGGER = LogManager.getLogger(XMLUtils.class);

  private XMLUtils() {
  }

  private static final DatatypeFactory datatypeFactory;

  static {
    try {
      datatypeFactory = DatatypeFactory.newInstance();
    } catch (final DatatypeConfigurationException e) {
      LOGGER.error("Error initializing DatatypeFactory!", e);
      throw new ConfigurationException("Error initializing DatatypeFactory!", e);
    }
  }

  /**
   * Converts a date to XMLGregorianCalendar object, used in SOAP requests.
   * 
   * @param date Date object to be converted
   * @return an XMLGregorianCalendar instance
   */
  public static XMLGregorianCalendar dateToXML(final Date date) {
    final GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.setTime(date);
    return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
  }

  /**
   * Converts a LocalDate to XMLGregorianCalendar object, used in SOAP requests.
   * 
   * @param localDate LocalDate object to be converted
   * @return an XMLGregorianCalendar instance
   */
  public static XMLGregorianCalendar localDateToXML(final LocalDate localDate) {
    return datatypeFactory.newXMLGregorianCalendar(localDate.toString());
  }

  /**
   * Converts an XMLGregorianCalendar object to a LocalDate.
   * 
   * @param calendar XMLGregorianCalendar to be converted
   * @return the converted date
   */
  public static LocalDate xmlCalendarToLocalDate(final XMLGregorianCalendar calendar) {
    return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
  }

  /**
   * Converts an XMLGregorianCalendar object to a Date.
   * 
   * @param calendar XMLGregorianCalendar to be converted
   * @return the converted date
   */
  public static Date xmlCalendarToDate(final XMLGregorianCalendar calendar) {
    return Date.from(calendar.toGregorianCalendar().toInstant());
  }

}

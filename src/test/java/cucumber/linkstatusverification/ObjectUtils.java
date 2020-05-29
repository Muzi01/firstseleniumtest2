package cucumber.linkstatusverification;

import org.apache.commons.lang3.ClassUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

public class ObjectUtils {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Returns a String representation of an object. Basically a custom toString() method which
     * includes project-specific classes
     *
     * @param object any object
     * @return a String representation of the given object
     */
    public static String getStringValueOfObject(final Object object) {

        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        }
        if (ClassUtils.isPrimitiveOrWrapper(object.getClass()) || object.getClass().isEnum()) {
            return String.valueOf(object);
        }
        if (object instanceof XMLGregorianCalendar) {
            return ((XMLGregorianCalendar) object).toGregorianCalendar().getTime().toString();
        }
        if (object instanceof JAXBElement) {
            return getStringValueOfObject(((JAXBElement) object).getValue());
        }
        if (object.getClass().getSimpleName().equalsIgnoreCase("IdReferenceType")) {
            try {
                return String.valueOf(object.getClass().getMethod("getId").invoke(object));
            } catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error("Error accessing Id attribute of IdReferenceType.", e);
            }
        }
        return object.toString();
    }
}

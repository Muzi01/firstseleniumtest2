package DataGenerator;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class Jfairy {

    @Test
    public static  void  main (String[] args) {

        Fairy fairy = Fairy.create ();
        Person person  = fairy.person ();
        person.getFirstName ();
        person.getFullName ();
        person.getAddress ();

        System.out.println ("Person first name" + person.getFirstName ());
        System.out.println ("Person full name " + person.getFullName ());
    }
}

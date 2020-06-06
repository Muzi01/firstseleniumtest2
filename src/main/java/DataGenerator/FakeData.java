package DataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FakeData {

    @Test
    public void givenValidService_whenRegexifyCalled_checkPattern() throws Exception {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String alphaNumericString = fakeValuesService.regexify("[a-z1-9]{10}");
        Matcher alphaNumericMatcher = Pattern.compile("[a-z1-9]{10}").matcher(alphaNumericString);

        assertTrue(alphaNumericMatcher.find());
    }

    @Test
    public void givenJavaFakersWithSameSeed_whenNameCalled_CheckSameName() {

        Faker faker1 = new Faker(new Random(24));
        Faker faker2 = new Faker(new Random(24));

        assertEquals(faker1.name().firstName(), faker2.name().firstName());
    }
}

package DataGenerator;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.jupiter.api.Test;

public class Main {
@Test
    public static void test (String[] args) {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 100; i++) {
            String name = df.getFirstName() + " "+ df.getLastName();
            System.out.println(name);
        }
    }
@Test
    public static void main (String[] args) {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 100; i++) {
            String address = df.getAddress()+","+df.getCity()+","+df.getNumberText(5);
            String business = df.getBusinessName();
            System.out.println(business + " located at " + address);
        }
    }
}
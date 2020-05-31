package tests.legacy;

public class Main {

    public static void main(String[] args) {
        double tab[] = new double[10];
        tab[1]=1;
        tab[0]=1;
        for (int i=2; i<10; i++)
            tab[i]=tab[i-2]+tab[i-1];

        for (int i=0; i<20; i++)
            System.out.println(i+1 + " liczba = " + tab[i]);
    }
}
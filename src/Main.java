package src;

public class Main {
    public static void main(String[] args) {

        Conversion test = new Conversion();

        double convert = test.convert("EUR", "GBP", 3);
        double conversionRate = test.conversionRate("EUR", "GBP");

        System.out.println("Result:" + convert);
        System.out.println("Rate:" + conversionRate);

    }
}
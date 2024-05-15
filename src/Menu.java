package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {

    public static void createMenu (String mainCurrencyCode, ArrayList<String> currencyCodes) {
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda");

        //Variables
        int numberOption = 1;
        HashMap<Integer, String[]> mapCodes = new HashMap<>();
        List<String> lastestConversions = new ArrayList<>();
        List<String> conversionsDate = new ArrayList<>();


        //Input
        Scanner scannerOption = new Scanner(System.in);
        int option = 0;

        while (true) {
            System.out.println("************************************************************");
            for (String currencyCode : currencyCodes) {
                System.out.println(numberOption + ") " + mainCurrencyCode + " =>> " + currencyCode);
                mapCodes.put(numberOption, new String[]{mainCurrencyCode, currencyCode});
                numberOption++;
                System.out.println(numberOption + ") " + currencyCode + " =>> " + mainCurrencyCode);
                mapCodes.put(numberOption, new String[]{currencyCode, mainCurrencyCode});
                numberOption++;
            }
            System.out.println(numberOption + ") Últimas conversões");
            numberOption++;
            System.out.println(numberOption + ") Sair");

            //Input a valid option
            System.out.println("Escolha uma opção válida:");
            System.out.println("************************************************************");
            option = scannerOption.nextInt();
            if (option == numberOption-1) {
                if (lastestConversions.isEmpty()) {
                    System.out.println("Não foi realizada ainda nenhuma conversão nessa sessão do programa.");
                }
                lastestConversions.forEach(System.out::println);
                numberOption = 1;
                mapCodes.clear();
                continue;
            }
            else if (option == numberOption) {
                System.out.println("Finalizando a aplicação, gerando log de conversões.");
                generateLog(conversionsDate, lastestConversions);
                break;
            } else if (option < 1 || option > numberOption) {
                System.out.println("Opção inválida, digite uma opção válida");
                //Reset variables
                numberOption = 1;
                mapCodes.clear();
                continue;
            }

            //Ammount to be converted
            Scanner scannerAmmount = new Scanner(System.in);
            System.out.println("Digite o valor a ser convertido:");
            double ammount = scannerAmmount.nextDouble();

            String[] arrayCodes = mapCodes.get(option);
            String baseCurrency = arrayCodes[0];
            String targetCurrency = arrayCodes[1];

            double ammountConverted = Conversion.convert(baseCurrency, targetCurrency, ammount);
            String outputConversion = ammount + " [" + baseCurrency + "] =>> " + ammountConverted + " [" + targetCurrency + "]";

            //Date
            LocalDateTime actualDate = LocalDateTime.now();
            DateTimeFormatter brazilDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String conversionDate = actualDate.format(brazilDateFormat);
            conversionsDate.add(conversionDate);

            lastestConversions.add(outputConversion); //Save the ammount converted to show in the history option
            System.out.println(outputConversion);

            //Reset variables
            numberOption = 1;
            mapCodes.clear();
        }
    }

    private static void generateLog(List<String> dates, List<String> conversions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            for (int i = 0; i < dates.size(); i++) {
                String date = dates.get(i);
                String conversion = conversions.get(i);
                writer.write(date + " : " + conversion);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

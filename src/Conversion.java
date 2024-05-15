package src;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversion {

    private static final String APIKEY = "7ca0225e9b553ead1296109c";

    public static double convert (String baseCurrency, String targetCurrency, double ammount) {
        String baseURL = "https://v6.exchangerate-api.com/v6/" + APIKEY + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + ammount;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
            client.close();

            JsonElement jsonElement = JsonParser.parseString(response);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            double conversionResult = Double.parseDouble(String.valueOf(jsonObject.get("conversion_result")));

            return conversionResult;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double conversionRate (String baseCurrency, String targetCurrency) {
        String baseURL = "https://v6.exchangerate-api.com/v6/" + APIKEY + "/pair/" + baseCurrency + "/" + targetCurrency;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
            client.close();

            JsonElement jsonElement = JsonParser.parseString(response);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            double conversionRate = Double.parseDouble(String.valueOf(jsonObject.get("conversion_rate")));

            return conversionRate;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

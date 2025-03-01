import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "1ee65e3b34ccdd0f635c19d2";  // Replace with your actual API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String urlString = API_URL + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed to fetch exchange rate.");
        }

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Extract the exchange rate using simple string processing
        String responseBody = response.toString();
        String searchKey = "\"" + targetCurrency + "\":";
        int startIndex = responseBody.indexOf(searchKey) + searchKey.length();
        int endIndex = responseBody.indexOf(",", startIndex);

        if (startIndex == -1) {
            throw new IOException("Invalid target currency or API response.");
        }

        // Extracting exchange rate as a substring
        String exchangeRateString = responseBody.substring(startIndex, endIndex).trim();
        return Double.parseDouble(exchangeRateString);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD, EUR, INR): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, EUR, INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;

            System.out.println("\nConversion Result:");
            System.out.println(amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
        } catch (IOException e) {
            System.out.println("Error fetching exchange rate. Please try again later.");
        }

        scanner.close();
    }
}

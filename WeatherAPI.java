import java.net.*;
import java.io.*;

public class WeatherAPI {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int choice = -1;
            while (choice != 0) {
                System.out.println("Enter your choice:");
                System.out.println("1. Get weather");
                System.out.println("2. Get Wind Speed");
                System.out.println("3. Get Pressure");
                System.out.println("0. Exit");
                choice = Integer.parseInt(reader.readLine());
                if (choice == 0) {
                    return;
                }
                String city = "Kolkata";
                String apiKey = "4e8969f7ea0a94ecf916a6191a25a9d4";
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                String jsonString = result.toString();
                if (choice == 1) {
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = reader.readLine();
                    int index = jsonString.indexOf("\"temp\":");
                    double temp = Double.parseDouble(jsonString.substring(index + 7, index + 12)) - 273.15;

                    System.out.println("Temperature: " + temp + " °C");
                } else if (choice == 2) {
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = reader.readLine();
                    int index = jsonString.indexOf("\"speed\":");
                    double speedKmh = Double.parseDouble(jsonString.substring(index + 8, index + 12)) * 3.6;

                    System.out.println("Wind Speed: " + speedKmh +" KM/h");
                } else if (choice == 3) {
                    System.out.println("Enter date (yyyy-mm-dd): ");
                    String date = reader.readLine();
                    int index = jsonString.indexOf("\"pressure\":");
                    
                    double pressure = Double.parseDouble(jsonString.substring(index + 11, index + 16));
                    System.out.println("Pressure: " + pressure);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import org.json.JSONObject; //what does this object do
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
public class testAPI {
    public static void main(String[] args) throws Exception {
        String apiKey = "DNHGK48TBIMVUDJK";
        String symbol = "TSLA";
        //String targetDate = "2025-04-09";
        try {
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey;
        // Make HTTP request
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        // Parse JSON
        JSONObject json = new JSONObject(response.toString());
        //JSONObject timeSeries = json.getJSONObject(targetDate);
        JSONObject timeSeries = json.getJSONObject("Time Series (Daily)");
        System.out.println(timeSeries.keySet());
        // Loop through dates
        for (String date : timeSeries.keySet()) {
            JSONObject dailyData = timeSeries.getJSONObject(date);
            String open = dailyData.getString("1. open");
            String high = dailyData.getString("2. high");

            System.out.println(date + ": Open=" + open + ", High=" + high);
        }
    }
    catch (Exception ex){
        System.out.println("no match found");
}
}
}
//https connection
//use buffered reader
//buffered reader gets the data I need
//how to use specific stock?
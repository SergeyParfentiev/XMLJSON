package classExample.json.example1;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        String request = "http://query.yahooapis.com/v1/public/yql?format=json&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        String result = performRequestFirstOption(request);

        Gson gson = new GsonBuilder().create();
        JSON json = (JSON) gson.fromJson(result, JSON.class);

        for (Rate rate : json.query.results.rate) {
            System.out.println(rate.id + "=" + rate.Rate + " " + rate.Name);
        }

        System.out.println("JSON: \n\t" + gson.toJson(json));
    }

    private static String performRequestFirstOption(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        Scanner scanner;
        String result = "";
        final String CHARSET_NAME = "UTF-8";
        final Pattern EVERYTHING_PATTERN  = Pattern.compile("\\A");

        URLConnection site = url.openConnection();
        //use the source code as in input stream and read in the entire website
        InputStream is = site.getInputStream();
        scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
        if (scanner.hasNextLine())
            result = scanner.useDelimiter(EVERYTHING_PATTERN).next();

        scanner.close();
        return result;

    }

    private static String performRequestSecondOption(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }
        return sb.toString();
    }
}
package homeTask.task2.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import homeTask.task2.objects.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
Распарсить структуру данных из parsingFromTXT.txt.
 */

public class ParsingFromTXT {

    public static void main(String[] args) throws IOException {
        String request = "parsingFromTXT.txt";
        String result = text(request);

        Gson gson = new GsonBuilder().create();
        Person json = gson.fromJson(result, Person.class);

        System.out.println("Name : " + json.name + " Surname: " + json.surname);

        System.out.print("Phones: ");
        for (String phone : json.phones) {
            System.out.print(phone + " ");
        }

        System.out.print("\nSites: ");
        for (String site : json.sites) {
            System.out.print(site + " ");
        }

        System.out.println("\nAddress:\n\tCountry: " + json.address.country + "\n\tCity: " + json.address.city +
                "\n\tStreet: " + json.address.street);
    }

    private static String text(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String text;
        StringBuilder sb = new StringBuilder();

        while ((text = bufferedReader.readLine()) != null) {
            sb.append(text);
        }
        return String.valueOf(sb);
    }
}

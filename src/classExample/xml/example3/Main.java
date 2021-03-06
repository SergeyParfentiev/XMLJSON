package classExample.xml.example3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Book("Author1", "Title1", 14.55, new Date()));
        catalog.add(new Book("Author2", "Title2", 66, new Date()));

        try {
            File file = new File("example3.xml");
            StringWriter xml = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // читабельное форматирование
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 //           marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

            // пишем в файл
            marshaller.marshal(catalog, file);
            marshaller.marshal(catalog, System.out);

            // читаем из файла
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            catalog = (Catalog) unmarshaller.unmarshal(file);
            System.out.println(catalog);

//            System.out.println(catalog.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
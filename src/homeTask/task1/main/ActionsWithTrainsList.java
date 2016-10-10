package homeTask.task1.main;

import homeTask.task1.objects.Train;
import homeTask.task1.objects.Trains;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/*
Есть список поездов, представленный с виде XML. Вывести на экран информацию о тех поездах, которые
отправляются сегодня с 15:00 до 19:00.

Написать код для добавления новых поездов в существующий XML.
 */

public class ActionsWithTrainsList {

    public static void main(String[] args) throws Exception {
        String fileName = "src/homeTask/task1/xml/trainsList.xml";
        File file = new File(fileName);

        JAXBContext context = JAXBContext.newInstance(Trains.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Trains trains = (Trains) unmarshaller.unmarshal(file);

        trains.trainsToday();

        trains.addNewTrains(new Train("Sumy", "Khmelnytsky", "12.12.2016", "20:12"));
        trains.addNewTrains(new Train("Kirovograd", "Ternopil", "30.9.2016", "18:53"));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(trains, file);
    }
}

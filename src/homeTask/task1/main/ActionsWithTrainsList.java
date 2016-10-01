package homeTask.task1.main;

import homeTask.task1.showAndAdd.NewTrains;
import homeTask.task1.showAndAdd.TrainsToday;

/*
Есть список поездов, представленный с виде XML. Вывести на экран информацию о тех поездах, которые
отправляются сегодня с 15:00 до 19:00.

Написать код для добавления новых поездов в существующий XML.
 */

public class ActionsWithTrainsList {

    public static void main(String[] args) throws Exception {
        String fileName = "trainsList.xml";

        TrainsToday trainsToday = new TrainsToday();
        trainsToday.show(fileName);

        NewTrains newTrains = new NewTrains();
        newTrains.addTrainsToXML(fileName);

    }
}

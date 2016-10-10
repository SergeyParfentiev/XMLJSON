package homeTask.task1.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "trains")
public class Trains {

    @XmlElement(name = "train")
    private List<Train> trains = new ArrayList<>();

    public void addNewTrains(Train train) {
        trains.add(train);
    }

    public void trainsToday() throws Exception{
        Date currentDate = new Date();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyyHH:mm");

        Date beforePeriod = dateFormat.parse(currentDateFormat.format(currentDate) + "14:59");
        Date afterPeriod = dateFormat.parse(currentDateFormat.format(currentDate) + "19:00");

        for(Train train : trains) {
            Date trainDate = dateFormat.parse(train.getDate() + train.getDeparture());

            if (trainDate.after(beforePeriod) && trainDate.before(afterPeriod)) {
                System.out.println(train);
            }
        }
    }
}

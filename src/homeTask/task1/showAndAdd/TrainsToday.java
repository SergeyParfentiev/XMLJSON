package homeTask.task1.showAndAdd;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainsToday {
    private DocumentBuilder documentBuilder;

    public TrainsToday() throws ParserConfigurationException {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    private boolean checkDate(StringBuilder trainDateBuilder) {
        boolean answer = false;

        try {
            Date currentDate = new Date();
            SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyyHH:mm");

            Date trainDate = dateFormat.parse(String.valueOf(trainDateBuilder));
            Date beforePeriod = dateFormat.parse(currentDateFormat.format(currentDate) + "14:59");
            Date afterPeriod = dateFormat.parse(currentDateFormat.format(currentDate) + "19:00");

            if (trainDate.after(beforePeriod) && trainDate.before(afterPeriod)) {
                answer = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private List<String> getTrainsTodayId(NodeList trains) {
        List<String> listId = new ArrayList<>();


        for (int i = 0; i < trains.getLength(); i++) {
            Node train = trains.item(i);
            StringBuilder trainDateBuilder = new StringBuilder();

            if (train.getNodeType() != Node.TEXT_NODE) {
                NodeList trainData = train.getChildNodes();
                for (int j = 0; j < trainData.getLength(); j++) {
                    Node data = trainData.item(j);
                    if (data.getNodeType() != Node.TEXT_NODE &&
                            ("date".equals(data.getNodeName()) || "departure".equals(data.getNodeName()))) {
                        trainDateBuilder.append(data.getChildNodes().item(0).getTextContent());
                    }
                }
            }
            if (trainDateBuilder.length() > 0 && checkDate(trainDateBuilder)) {
                listId.add(train.getAttributes().getNamedItem("id").getNodeValue());
            }
        }
        return listId;
    }

    private boolean checkTrainId(List<String> trainsTodayId, String trainId) {
        boolean answer = false;

        for(String id : trainsTodayId) {
            if(id.equals(trainId)) {
                answer = true;
            }
        }
        return answer;
    }

    public void show(String fileName) {
        try {
            Document document = documentBuilder.parse(fileName);
            Node root = document.getDocumentElement();

            System.out.println("List of trains today:");
            System.out.println();

            NodeList trains = root.getChildNodes();

            List<String> trainsTodayId = getTrainsTodayId(trains);

            for (int i = 0; i < trains.getLength(); i++) {
                Node train = trains.item(i);
                if (train.getNodeType() != Node.TEXT_NODE &&
                        checkTrainId(trainsTodayId, train.getAttributes().getNamedItem("id").getNodeValue())) {
                    NodeList trainData = train.getChildNodes();
                    for (int j = 0; j < trainData.getLength(); j++) {
                        Node data = trainData.item(j);
                        if (data.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(data.getNodeName() + ": " + data.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

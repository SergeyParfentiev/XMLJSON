package homeTask.task1.showAndAdd;

import homeTask.task1.objects.Train;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewTrains {

    private DocumentBuilder documentBuilder;
    private List<Train> trainsList;

    public NewTrains() {
        initBuilder();
    }

    private void initBuilder() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            trainsList = new ArrayList<>();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void takeListTrainsFromXML() {
        try {
            File xmlFile = new File("trainsList.xml");
            Document document = documentBuilder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList trains = root.getChildNodes();
            for (int i = 0; i < trains.getLength(); i++) {
                Train newTrain = new Train();
                Node train = trains.item(i);
                if (train.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList trainData = train.getChildNodes();
                    for (int j = 0; j < trainData.getLength(); j++) {
                        Node data = trainData.item(j);
                        switch (data.getNodeName()) {
                            case "from":
                                newTrain.setFrom(data.getChildNodes().item(0).getTextContent());
                                break;
                            case "to":
                                newTrain.setTo(data.getChildNodes().item(0).getTextContent());
                                break;
                            case "date":
                                newTrain.setDate(data.getChildNodes().item(0).getTextContent());
                                break;
                            case "departure":
                                newTrain.setDeparture(data.getChildNodes().item(0).getTextContent());
                                break;
                        }
                    }
                    trainsList.add(newTrain);
                }
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewTrainToList() {
        trainsList.add(new Train("Kirovograd", "Ternopil", "30.9.2016", "18:53"));
        trainsList.add(new Train("Sumy", "Khmelnytsky", "12.12.2016", "20:12"));
    }


    private void writeToXML(String fileName) {
        int id = 0;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("trains");
            document.appendChild(rootElement);

            for (Train aTrainsList : trainsList) {
                Element train = document.createElement("train");
                train.setAttribute("id", String.valueOf(++id));
                rootElement.appendChild(train);

                Element from = document.createElement("from");
                from.setTextContent(aTrainsList.getFrom());
                train.appendChild(from);

                Element to = document.createElement("to");
                to.setTextContent(aTrainsList.getTo());
                train.appendChild(to);

                Element date = document.createElement("date");
                date.setTextContent(aTrainsList.getDate());
                train.appendChild(date);

                Element departure = document.createElement("departure");
                departure.setTextContent(aTrainsList.getDeparture());
                train.appendChild(departure);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void addTrainsToXML(String fileName) {
        takeListTrainsFromXML();

        addNewTrainToList();

        writeToXML(fileName);
    }
}

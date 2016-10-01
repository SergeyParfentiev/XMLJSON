package homeTask.task3.main;

import homeTask.task3.printDocument.NodeRecursion;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

/*
Написать парсер для Yahoo Finance в
режиме XML (format=xml).
 */
public class YahooFinanceParserFromXMLFormat {

    public static void main(String[] args) throws Exception {

        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        Document doc = factory.newDocumentBuilder().parse(new URL(request).openStream());

        Node root = doc.getDocumentElement();

        new NodeRecursion().entryText(root.getChildNodes());
    }
}

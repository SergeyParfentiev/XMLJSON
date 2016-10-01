package classExample.xml.example2;

import javafx.util.BuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class MyClassToXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            //book 1
            Element book1 = document.createElement("book1");
            book1.setAttribute("id", "1");
            rootElement.appendChild(book1);

            Element year = document.createElement("year");
            year.setTextContent("Year 1");
            book1.appendChild(year);

            Element author1 = document.createElement("author");
            author1.setTextContent("Author 1");
            book1.appendChild(author1);

            //book 2
            Element book2 = document.createElement("book2");
            rootElement.appendChild(book2);

            Element author2 = document.createElement("author");
            author2.setTextContent("Author 2");
            book2.appendChild(author2);

            //save
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("myClassToXML.xml"));

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setParameter(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

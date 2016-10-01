package classExample.xml.example4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class OutFromXML extends DefaultHandler {

    private boolean bAuthor;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Document started...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Document ends.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Element " + localName);
        bAuthor = localName.equals("author");
        for (String s : localName.split("genre")) {
//            System.out.println(s);
        }
//        System.out.println(localName.split("genre").length);
        for (String s : localName.split("catalog")) {
            System.out.println(s);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bAuthor) {
//            System.out.println("Author value: " + new String(ch, start, length));
            bAuthor = false;
        }
    }

    public static void main(String[] args) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new OutFromXML());
        xmlReader.parse("example4.xml");
    }
}

package homeTask.task3.printDocument;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeRecursion {

    public void entryText(NodeList elements) {
        for (int i = 0; i < elements.getLength(); i++) {
            Node entryElement = elements.item(i);
            if (entryElement.getNodeType() == Node.ELEMENT_NODE) {
                if (entryElement.getChildNodes().getLength() == 1) {
                    System.out.println(entryElement.getNodeName() + ": " +
                            entryElement.getChildNodes().item(0).getTextContent());
                } else {
                    entryText(entryElement.getChildNodes());
                }
            }
        }
        System.out.println();
    }
}

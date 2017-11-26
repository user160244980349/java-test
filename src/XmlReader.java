import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Класс парсит прямоугольники из xml-файла
// и возвращает их список
class XmlReader {

    private File xml;

    public XmlReader(File f) {
        xml = f;
    }

    public List<Rect> GetRects() {
        List<Rect> rects = new ArrayList<>();
        try {
            System.out.println("Parsing rects...");

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xml);

            NodeList rectNodes = document.getElementsByTagName("rect");

            for (int i = 0; i < rectNodes.getLength(); i++) {

                NamedNodeMap rectNodeAttributes = rectNodes.item(i).getAttributes();
                Rect newRect = new Rect();

                newRect.x1 = Integer.valueOf(rectNodeAttributes.getNamedItem("x1").getNodeValue());
                newRect.y1 = Integer.valueOf(rectNodeAttributes.getNamedItem("y1").getNodeValue());
                newRect.x2 = Integer.valueOf(rectNodeAttributes.getNamedItem("x2").getNodeValue());
                newRect.y2 = Integer.valueOf(rectNodeAttributes.getNamedItem("y2").getNodeValue());
                newRect.c = rectNodeAttributes.getNamedItem("c").getNodeValue();

                rects.add(newRect);

            }
            System.out.println("Ok!");

        } catch(ParserConfigurationException | SAXException | IOException exception) {
            System.out.println(exception.getMessage());
        }

        return rects;
    }
}

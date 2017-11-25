import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class XmlReader implements IReader {

    private File Xml;

    public XmlReader(File f) {
        Xml = f;

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(Xml);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of rects:");
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();

        } catch(ParserConfigurationException | SAXException | IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<Rect> GetElements() {

        // parsing next rect and return
        System.out.println("Parsing element...");

        return null;
    }
}

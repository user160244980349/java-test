import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File xml = new File(args[0]);
        File html = new File(args[1]);

        try {

            if (html.exists()) {
                boolean deleted = html.delete();
                if (deleted)
                    System.out.println("Html is deleted!");
            }

            boolean created = html.createNewFile();
            if (created)
                System.out.println("Html is created!");

            XmlReader xmlReader = new XmlReader(xml);
            HtmlTable table = new HtmlTable(xmlReader.GetRects());
            HtmlBuilder htmlBuilder = new HtmlBuilder(html);
            htmlBuilder.insertMarkup(table.html());
            htmlBuilder.buildHtml();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        File Xml = new File(args[0]);
        File Html = new File(args[1]);

        try {

            XmlReader XmlR = new XmlReader(Xml);
            HtmlBuilder HtmlB = new HtmlBuilder(Html, XmlR);

            boolean created = Html.createNewFile();
            if (created)
                System.out.println("Html is created!");

            HtmlB.BuildHtml();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

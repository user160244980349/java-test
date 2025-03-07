import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Класс создает поток для редактирования документа,
// генерирует его начало и конец и
// аккумулирует стороннюю разметку
class HtmlBuilder {

    private File html;
    private String markup = "";

    public HtmlBuilder(File f) {
        html = f;
    }

    public void buildHtml() {
        System.out.println("Building html...");
        try {
            FileOutputStream htmlStream = new FileOutputStream(html);
            htmlStream.write("<html>\n".getBytes());
            htmlStream.write("<head>\n".getBytes());
            htmlStream.write("<meta charset='utf-8'>\n".getBytes());
            htmlStream.write("<title>RECTS</title>\n".getBytes());
            htmlStream.write("</head>\n".getBytes());
            htmlStream.write("<body>\n".getBytes());
            htmlStream.write(markup.getBytes());
            htmlStream.write("</body>\n".getBytes());
            htmlStream.write("</html>\n".getBytes());
            htmlStream.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Ok!");
    }

    public void insertMarkup(String m) {
        markup += m;
    }

}

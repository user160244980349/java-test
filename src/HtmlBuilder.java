import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class HtmlBuilder {

    private File html;
    private String markup = "";
    private FileOutputStream htmlStream;

    public HtmlBuilder(File f) {
        html = f;

        try {
            htmlStream = new FileOutputStream(html);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void buildHtml() {
        System.out.println("Building html...");
        try {
            htmlStream.write("<html>\n".getBytes());
            htmlStream.write("<head>\n".getBytes());
            htmlStream.write("<meta charset='utf-8'>\n".getBytes());
            htmlStream.write("<title>RECTS</title>\n".getBytes());
            htmlStream.write("</head>\n".getBytes());
            htmlStream.write("<body style='border-spacing:initial' bgcolor=black >\n".getBytes());
            htmlStream.write("<table>\n".getBytes());
            htmlStream.write(markup.getBytes());
            htmlStream.write("</table>\n".getBytes());
            htmlStream.write("</body>\n".getBytes());
            htmlStream.write("</html>\n".getBytes());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Ok!");
    }

    public void insertMarkup(String m) {
        markup += m;
    }

}

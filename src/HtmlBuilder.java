import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class HtmlBuilder {

    private File html;
    private IReader reader;

    public HtmlBuilder(File f, IReader r) {
        html = f;
        reader = r;
    }

    public void BuildHtml() {

        List<Rect> rects = reader.GetElements();

//        try {
//
//
//
//        } catch (IOException exception) {
//            System.out.println(exception.getMessage());
//        }

    }

    private void GenerateRect(Rect r) {

        // html generation
        System.out.println("Html generation...");

    }

}

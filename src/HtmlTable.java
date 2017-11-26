import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class HtmlTable {

    private List<Cell> cells;

    public HtmlTable(List<Rect> rects) {
        System.out.println("Combining rects with cells...");
        cells = new ArrayList<>();
        List<Integer> verticalsXCoords = new ArrayList<>();
        List<Integer> horizontalsYCoords = new ArrayList<>();

        for (Rect rect : rects) {
            verticalsXCoords.add(rect.x1);
            verticalsXCoords.add(rect.x2);
            horizontalsYCoords.add(rect.y1);
            horizontalsYCoords.add(rect.y2);
        }

        verticalsXCoords.sort(Integer::compareTo);
        horizontalsYCoords.sort(Integer::compareTo);

        Cell previousCell = new Cell();
        previousCell.w = 0;
        previousCell.h = 0;
        previousCell.row = 0;

        for (Integer horizontalYCoord : horizontalsYCoords) {
            for (Integer verticalXCoord : verticalsXCoords) {

                Cell newCell = new Cell();
                newCell.w = verticalXCoord - previousCell.w;
                newCell.h = horizontalYCoord - previousCell.h;
                newCell.row = previousCell.row;

                ListIterator<Rect> it = rects.listIterator(rects.size());
                while (it.hasPrevious()) {
                    Rect rect = it.previous();
                    if (    rect.x1 <= previousCell.w   &&
                            rect.y1 <= previousCell.h   &&
                            rect.x2 >= verticalXCoord   &&
                            rect.y2 >= horizontalYCoord) {
                        newCell.c = rect.c;
                        break;
                    }
                }

                if (newCell.w != 0 && newCell.h != 0)
                    cells.add(newCell);

                previousCell.w = verticalXCoord;
            }
            previousCell.h = horizontalYCoord;
            previousCell.w = 0;
            previousCell.row++;
        }
        System.out.println("Ok!");
    }

    public String html() {
        int curRow = -1;
        StringBuilder markup = new StringBuilder();

        markup.append("<table style='border-spacing:0'>\n");

        for (Cell cell : cells) {
            if (cell.row > curRow) {
                if (curRow != -1)
                    markup.append("</tr>\n");

                markup  .append("<tr height=")
                        .append(cell.h)
                        .append(">\n");

                curRow = cell.row;
            }

            markup.append("<td width=")
                    .append(cell.w);

            if (cell.c != null)
                markup  .append(" bgcolor=")
                        .append(cell.c)
                        .append("></td>\n");
            else
                markup.append("></td>\n");

        }

        markup.append("</tr>\n</table>\n");

        return markup.toString();
    }

}

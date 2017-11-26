import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class HtmlTable {

    private List<Cell> cells;

    public HtmlTable(List<Rect> rects) {
        cells = new ArrayList<Cell>();
        List<Integer> verticals = new ArrayList<Integer>();
        List<Integer> horizontals = new ArrayList<Integer>();

        for (Rect rect : rects) {
            verticals.add(rect.x1);
            verticals.add(rect.x2);
            horizontals.add(rect.y1);
            horizontals.add(rect.y2);
        }

        verticals.sort(Integer::compareTo);
        horizontals.sort(Integer::compareTo);

        Cell previousCell = new Cell();
        previousCell.w = 0;
        previousCell.h = 0;
        previousCell.row = 0;

        for (Integer horizontal : horizontals) {
            for (Integer vertical : verticals) {

                Cell newCell = new Cell();
                newCell.w = vertical - previousCell.w;
                newCell.h = horizontal - previousCell.h;
                newCell.row = previousCell.row;

                ListIterator<Rect> it = rects.listIterator(rects.size());
                while (it.hasPrevious()) {
                    Rect rect = it.previous();
                    if (    rect.x1 <= previousCell.w   &&
                            rect.y1 <= previousCell.h   &&
                            rect.x2 >= vertical         &&
                            rect.y2 >= horizontal) {
                        newCell.c = rect.c;
                        break;
                    }
                }

                if (newCell.w != 0 && newCell.h != 0)
                    cells.add(newCell);

                previousCell.w = vertical;
            }
            previousCell.h = horizontal;
            previousCell.w = 0;
            previousCell.row++;
        }
    }

    public String html() {

        int curRow = -1;
        StringBuilder markup = new StringBuilder();
        markup.append("<table style='border-spacing:initial'>\n");
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

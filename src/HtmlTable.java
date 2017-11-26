import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class HtmlTable {

    private List<Cell> cells;

    public HtmlTable(List<Rect> rects) {
        cells = new ArrayList<Cell>();
        List<Integer> columns = new ArrayList<Integer>();
        List<Integer> rows = new ArrayList<Integer>();

        for (Rect rect : rects) {
            columns.add(rect.x1);
            columns.add(rect.x2);
            rows.add(rect.y1);
            rows.add(rect.y2);
        }

        columns.sort(Integer::compareTo);
        rows.sort(Integer::compareTo);

        Cell previousCell = new Cell();
        previousCell.w = 0;
        previousCell.h = 0;
        previousCell.row = 0;

        for (Integer row : rows) {
            for (Integer column : columns) {

                Cell newCell = new Cell();
                newCell.w = column - previousCell.w;
                newCell.h = row - previousCell.h;
                newCell.row = previousCell.row;

                ListIterator<Rect> it = rects.listIterator(rects.size());
                while (it.hasPrevious()) {
                    Rect rect = it.previous();
                    if (    rect.x1 <= previousCell.w   &&
                            rect.y1 <= previousCell.h   &&
                            rect.x2 >= column         &&
                            rect.y2 >= row) {
                        newCell.c = rect.c;
                        break;
                    }
                }

                if (newCell.w != 0 && newCell.h != 0)
                    cells.add(newCell);

                previousCell.w = column;
            }
            previousCell.h = row;
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

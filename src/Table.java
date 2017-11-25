import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Cell> cells;

    public Table(List<Rect> rects) {
        cells = new ArrayList<Cell>();
        List<Integer> verticals = new ArrayList<Integer>();
        List<Integer> horizontals = new ArrayList<Integer>();

        for (Rect rect : rects) {
            verticals.add(rect.x1);
            verticals.add(rect.x2);
        }

        for (Rect rect : rects) {
            horizontals.add(rect.y1);
            horizontals.add(rect.y2);
        }

        verticals.sort(Integer::compareTo);
        horizontals.sort(Integer::compareTo);

        int prevH = 0;
        int prevV = 0;
        int row = 0;

        for (Integer vertical : verticals) {
            for (Integer horizontal : horizontals) {

                Cell newCell = new Cell();
                newCell.w = horizontal - prevH;
                newCell.h = vertical - prevV;
                newCell.row = row;
                cells.add(newCell);

                prevH = horizontal;
            }
            prevH = 0;
            prevV = vertical;
            row++;
        }

    }

    public String html() {

        int curRow = 0;
        StringBuilder markup = new StringBuilder();

        markup.append("<tr>\n");
        for (Cell cell : cells) {
            if (cell.row > curRow) {
                curRow = cell.row;
                markup.append("</tr>\n<tr>\n");
            }

            markup.append("<td width=")
                    .append(cell.w)
                    .append(" height=")
                    .append(cell.h)
                    .append(" bgcolor=")
                    .append(cell.c)
                    .append("></td>\n");

        }
        markup.append("</tr>\n");

        return markup.toString();
    }

}

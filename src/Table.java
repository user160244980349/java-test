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
            horizontals.add(rect.y1);
            horizontals.add(rect.y2);
        }

        verticals.sort(Integer::compareTo);
        horizontals.sort(Integer::compareTo);

        int prevH = 0;
        int prevV = 0;
        int row = 0;

        System.out.println(verticals.size());
        System.out.println(horizontals.size());

        for (Integer horizontal : horizontals) {
            for (Integer vertical : verticals) {

                Cell newCell = new Cell();
                newCell.w = vertical - prevV;
                newCell.h = horizontal - prevH;
                newCell.row = row;

                for (Rect rect : rects) {
                    if (    rect.x1 <= prevV &&
                            rect.y1 <= prevH &&
                            rect.x2 >= vertical &&
                            rect.y2 >= horizontal)
                        newCell.c = rect.c;
                }

                cells.add(newCell);

                prevV = vertical;
            }
            prevV = 0;
            prevH = horizontal;
            row++;
        }

    }

    public String html() {

        int curRow = -1;
        StringBuilder markup = new StringBuilder();

        for (Cell cell : cells) {
            if (cell.row > curRow) {
                if (curRow != -1)
                    markup.append("</tr>\n");

                markup.append("<tr height=")
                        .append(cell.h)
                        .append(">\n");

                curRow = cell.row;
            }

            markup.append("<td width=")
                    .append(cell.w)
                    .append(" bgcolor=")
                    .append(cell.c)
                    .append("></td>\n");

        }
        markup.append("</tr>\n");

        return markup.toString();
    }

}

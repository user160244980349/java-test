import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

// Представление прямоугольников
// в виде таблицы состоящей из ячеек
class HtmlTable {

    private List<Cell> cells;

    public HtmlTable(List<Rect> rects) {
        System.out.println("Combining rects with cells...");
        cells = new ArrayList<>();
        List<Integer> verticalsXCoords = new ArrayList<>();
        List<Integer> horizontalsYCoords = new ArrayList<>();

        // Берем проекции всех сторон всех прямоугольников на оси x и y
        for (Rect rect : rects) {
            verticalsXCoords.add(rect.x1);
            verticalsXCoords.add(rect.x2);
            horizontalsYCoords.add(rect.y1);
            horizontalsYCoords.add(rect.y2);
        }

        // Сортируем их
        verticalsXCoords.sort(Integer::compareTo);
        horizontalsYCoords.sort(Integer::compareTo);

        // Создаем буфферную переменную, в которую
        // будем записывать правую и нижнюю координаты,
        // к которым прицепим следующую ячейку
        Cell previousCell = new Cell();
        previousCell.w = 0;
        previousCell.h = 0;
        previousCell.row = 0;

        // Из всех сторон всех прямоугольников строим решетку,
        // причем все образующиеся ячейки располагаются 2мя
        // параметрами относительно верхнего и левого соседа
        for (Integer horizontalYCoord : horizontalsYCoords) {
            for (Integer verticalXCoord : verticalsXCoords) {

                // Создаем ячейку таблицы
                Cell newCell = new Cell();
                // В зависимости от координат предыдущей ячейки
                // рассчитывается ширина новой ячейки
                newCell.w = verticalXCoord - previousCell.w;
                newCell.h = horizontalYCoord - previousCell.h;
                newCell.row = previousCell.row;

                // В этом цикле раскрашиваем нужные клетки.
                // Цикл начинается с последнего элемента,
                // т.е. ищем цвет прямоугольника,
                // который перекроет остальные прямоугольники
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

                // Исключение ячеек с нулевой шириной или высотой.
                // Такое возможно когда 2 прямоугольника имеют
                // одинаковые соответсвенные координаты
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

    // Ничего интересного, просто генерация разметки,
    // как метод сериализации объекта таблицы
    // на основе ранее полученных ячеек
    public String html() {
        System.out.println("Building table markup...");
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

        System.out.println("Ok!");
        return markup.toString();
    }

}

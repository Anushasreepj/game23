package gameoflife;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Cell> getNeighbours() {
        return new HashSet<Cell>() {{
            add(new Cell(x - 1, y - 1));
            add(new Cell(x, y - 1));
            add(new Cell(x + 1, y - 1));
            add(new Cell(x - 1, y));
            add(new Cell(x + 1, y));
            add(new Cell(x - 1, y + 1));
            add(new Cell(x, y + 1));
            add(new Cell(x + 1, y + 1));
        }};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}

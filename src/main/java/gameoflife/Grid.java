package gameoflife;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grid {

    private final int width;
    private final int height;
    private final Set<Cell> activeCells;
    private Set<Cell> allCells;

    public Grid(int width, int height) {
        this(width, height, Collections.<Cell>emptySet());
    }

    public Grid(int width, int height, Set<Cell> activeCells) {
        this.width = width;
        this.height = height;
        this.activeCells = activeCells;
        this.allCells = createCells(width, height);
    }

    private Grid(int width, int height, Set<Cell> activeCells, Set<Cell> allCells) {
        this.width = width;
        this.height = height;
        this.activeCells = activeCells;
        this.allCells = createCells(width, height);
    }

    private Set<Cell> createCells(int width, int height) {
        Set<Cell> cells = new HashSet<Cell>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells.add(new Cell(x, y));
            }
        }
        return cells;
    }

    public Grid nextIteration() {

        Set<Cell> newActiveCells = new HashSet<Cell>() {{
            for (Cell cell : allCells) {
                if (shouldBeActive(cell)) {
                    add(cell);
                }
            }
        }};
        return new Grid(width, height, newActiveCells, allCells);
    }

    private boolean shouldBeActive(Cell cell) {
        int activeNeighbourCount = getActiveNeighbourCount(cell);
        return 3 == activeNeighbourCount || (2 == activeNeighbourCount && activeCells.contains(cell));
    }

    private int getActiveNeighbourCount(Cell cell) {
        final Set<Cell> neighbours = cell.getNeighbours();
        Set<Cell> activeNeighbours = new HashSet<Cell>() {{
            addAll(neighbours);
            retainAll(activeCells);
        }};
        return activeNeighbours.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grid grid = (Grid) o;

        if (height != grid.height) return false;
        if (width != grid.width) return false;
        if (activeCells != null ? !activeCells.equals(grid.activeCells) : grid.activeCells != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + (activeCells != null ? activeCells.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(activeCells.contains(new Cell(x, y)) ? "* " : ". ");
            }
            builder.append("\n");
        }
        return builder.toString();

    }
}

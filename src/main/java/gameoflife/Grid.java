package gameoflife;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grid {

    private final int width;
    private final int height;
    private final Set<Cell> activeCells;

    public Grid(int width, int height) {
        this(width, height, Collections.<Cell>emptySet());
    }

    public Grid(int width, int height, Set<Cell> activeCells) {
        this.width = width;
        this.height = height;
        this.activeCells = activeCells;
    }

    public Grid nextIteration() {

        Set<Cell> newActiveCells = new HashSet<Cell>();
        for (Cell activeCell : activeCells) {
            final Set<Cell> neighbours = activeCell.getNeighbours();
            Set<Cell> activeNeighbours = new HashSet<Cell>() {{
                addAll(neighbours);
                retainAll(activeCells);
            }};
            if (2 == activeNeighbours.size() || 3 == activeNeighbours.size()) {
                newActiveCells.add(activeCell);
            }
        }
        return new Grid(width, height, newActiveCells);
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

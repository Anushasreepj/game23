package gameoflife;

import java.awt.*;
import java.util.Collections;
import java.util.Set;

public class Grid {

    private final int width;
    private final int height;
    private final Set<Point> activeCells;

    public Grid(int width, int height) {
        this(width, height, Collections.<Point>emptySet());
    }

    public Grid(int width, int height, Set<Point> activeCells) {
        this.width = width;
        this.height = height;
        this.activeCells = activeCells;
    }

    public Grid nextIteration() {
        return new Grid(width, height);
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
}

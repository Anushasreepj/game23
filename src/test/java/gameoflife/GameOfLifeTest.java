package gameoflife;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;

public class GameOfLifeTest {

    @Test
    public void Grid_equality_is_correct() {

        Grid grid1 = new Grid(4, 3);
        Grid grid2 = new Grid(4, 3);
        Grid grid3 = new Grid(5, 2);
        Grid grid4 = new Grid(4, 3, new HashSet<Cell>() {{
            add(new Cell(1,2));
        }});
        Grid grid5 = new Grid(4, 3, new HashSet<Cell>() {{
            add(new Cell(1,2));
        }});
        Grid grid6 = new Grid(4, 3, new HashSet<Cell>() {{
            add(new Cell(2,3));
            add(new Cell(3,4));
        }});

        Assert.assertThat(grid1, equalTo(grid2));
        Assert.assertThat(grid1, not(equalTo(grid3)));
        Assert.assertThat(grid4, equalTo(grid5));
        Assert.assertThat(grid5, not(equalTo(grid6)));
        Assert.assertThat(grid1, not(equalTo(grid4)));

    }

    @Test
    public void All_dead_becomes_all_dead() {

        Grid initial = new Grid(2, 2);
        Grid expected = new Grid(2, 2);

        Grid iteration1 = initial.nextIteration();

        Assert.assertThat(iteration1, equalTo(expected));
    }

    @Test
    public void Single_cell_dies() {

        Grid initial = new Grid(2, 2, new HashSet<Cell>() {{
            add(new Cell(0, 0));
        }});
        Grid expected = new Grid(2, 2);

        Grid iteration1 = initial.nextIteration();

        Assert.assertThat(iteration1, equalTo(expected));
    }

    @Test
    public void Two_cells_both_die() {

        Grid initial = new Grid(2, 2, new HashSet<Cell>() {{
            add(new Cell(0, 0));
            add(new Cell(1, 0));
        }});
        Grid expected = new Grid(2, 2);

        Grid iteration1 = initial.nextIteration();

        Assert.assertThat(iteration1, equalTo(expected));
    }

    @Test
    public void Cell_knows_its_neighbours() {

        Cell cell = new Cell(2, 3);
        Set<Cell> expectedNeighbours = new HashSet<Cell>() {{
            add(new Cell(1, 2));
            add(new Cell(2, 2));
            add(new Cell(3, 2));
            add(new Cell(1, 3));
            add(new Cell(3, 3));
            add(new Cell(1, 4));
            add(new Cell(2, 4));
            add(new Cell(3, 4));
        }};

        Set<Cell> neighbours = cell.getNeighbours();

        Assert.assertThat(neighbours, equalTo(expectedNeighbours));


    }
}

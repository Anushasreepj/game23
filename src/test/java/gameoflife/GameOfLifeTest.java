package gameoflife;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;

public class GameOfLifeTest {

    @Test
    public void Grid_equality_is_correct() {

        Grid grid1 = new Grid(4, 3);
        Grid grid2 = new Grid(4, 3);
        Grid grid3 = new Grid(5, 2);
        Grid grid4 = new Grid(4, 3, new HashSet<Point>() {{
            add(new Point(1,2));
        }});
        Grid grid5 = new Grid(4, 3, new HashSet<Point>() {{
            add(new Point(1,2));
        }});
        Grid grid6 = new Grid(4, 3, new HashSet<Point>() {{
            add(new Point(2,3));
            add(new Point(3,4));
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
}

package prep;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * @author Steve Ash
 */
public class GraphTest {

    @Test
    public void shouldGetTopoSort() throws Exception {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 4);
        graph.addEdge(6, 5);
        List<Integer> result = graph.topologicalSort();
        assertEquals("[1, 2, 3, 6, 5, 4]", result.toString());
    }
}
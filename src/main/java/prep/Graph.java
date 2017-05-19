package prep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Steve Ash
 */
public class Graph<T> {
    private Map<T, Set<T>> outEdges = new HashMap<>();
    private Map<T, Set<T>> inEdges = new HashMap<>();
    private Set<T> vertices = new HashSet<>();
    private Set<T> marked = new HashSet<>();

    public void addEdge(T from, T to) {
        vertices.add(from);
        vertices.add(to);
        putEdge(from, to, outEdges);
        putEdge(to, from, inEdges);
    }

    private void putEdge(T from, T to, Map<T, Set<T>> edges) {
        Set<T> tos = edges.get(from);
        if (tos == null) {
            tos = new HashSet<>();
            edges.put(from, tos);
        }
        tos.add(to);
    }

    public void mark(T vertex) {
        marked.add(vertex);
    }

    public boolean isMarked(T vertex) {
        return marked.contains(vertex);
    }

    public void resetMarks() {
        marked.clear();
    }

    public List<T> topologicalSort() {
        resetMarks();
        List<T> result = new ArrayList<>(vertices.size());
        Set<T> cycleChecker = new HashSet<T>();
        for (T vertex : vertices) {
            cycleChecker.clear();
            cycleChecker.add(vertex);
            emitFrom(vertex, result, cycleChecker);
        }
        return result;
    }

    private void emitFrom(T vertex, List<T> result, Set<T> cycleChecker) {
        if (isMarked(vertex)) {
            return;
        }
        Set<T> parents = inEdges.get(vertex);
        if (parents != null) {
            for (T parent : parents) {
                if (!cycleChecker.add(parent)) {
                    throw new IllegalArgumentException("There is a cycle in the graph closed by " + vertex +
                            " to parent " + parent);
                }
                emitFrom(parent, result, cycleChecker);
            }
        }
        mark(vertex);
        result.add(vertex);
    }
}

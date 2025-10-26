package me.belyaikin.primskruskals.algorithm.prim;

import me.belyaikin.primskruskals.algorithm.Algorithm;
import me.belyaikin.primskruskals.algorithm.ExecutionResults;
import me.belyaikin.primskruskals.graph.Graph;
import me.belyaikin.primskruskals.graph.Vertex;

import java.util.Collection;
import java.util.Comparator;

public class Prim<T> implements Algorithm {
    private final Graph<T> graph;

    public Prim(Graph<T> graph) {
        this.graph = graph;
    }

    @Override
    public ExecutionResults run() {
        int operations = 0;
        long startTime = System.nanoTime();

        /// ---
        if (!graph.vertices().isEmpty()) graph.vertices().getFirst().setVisited(true);

        while(graph.vertices().stream().anyMatch(vertex -> !vertex.isVisited())) {
            graph.vertices().stream().filter(Vertex::isVisited)
                    .map(Vertex::getNeighbors)
                    .flatMap(Collection::stream)
                    .filter(tNeighbor -> !tNeighbor.isVisited())
                    .min(Comparator.comparingInt(n -> n.edge().getWeight()))
                    .ifPresent(candidate -> {
                        candidate.vertex().setVisited(true);
                        candidate.edge().setIncluded(true);
                    });

            operations++;
        }
        /// ---

        long stopTime = System.nanoTime();

        return new ExecutionResults(operations, stopTime - startTime);
    }
}

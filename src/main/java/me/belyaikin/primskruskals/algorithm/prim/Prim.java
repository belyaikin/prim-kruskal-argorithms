package me.belyaikin.primskruskals.algorithm.prim;

import me.belyaikin.primskruskals.algorithm.Algorithm;
import me.belyaikin.primskruskals.algorithm.ExecutionResults;
import me.belyaikin.primskruskals.graph.Graph;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Prim<T> implements Algorithm {
    private final Graph<T> graph;

    public Prim(Graph<T> graph) {
        this.graph = graph;
    }

    @Override
    public ExecutionResults run() {
        AtomicInteger operations = new AtomicInteger();
        long startTime = System.nanoTime();

        /// ---
        if (!graph.vertices().isEmpty()) {
            graph.vertices().getFirst().setVisited(true);

            operations.getAndIncrement();
        }

        while (graph.vertices().stream().anyMatch(v -> {
            operations.getAndIncrement();

            return !v.isVisited();
        })) {
            var candidate = graph.vertices().stream()
                    .filter(v -> {
                        operations.getAndIncrement();

                        return v.isVisited();
                    })
                    .map(v -> {
                        operations.getAndIncrement();

                        return v.getNeighbors();
                    })
                    .flatMap(Collection::stream)
                    .filter(neighbor -> {
                        operations.getAndIncrement();

                        return !neighbor.isVisited();
                    })
                    .min((n1, n2) -> {
                        operations.getAndIncrement();

                        return Integer.compare(n1.edge().getWeight(), n2.edge().getWeight());
                    });

            if (candidate.isPresent()) {
                var n = candidate.get();
                n.vertex().setVisited(true);
                n.edge().setIncluded(true);

                operations.addAndGet(2);
            }
        }
        /// ---

        long stopTime = System.nanoTime();

        return new ExecutionResults(operations.get(), stopTime - startTime);
    }
}

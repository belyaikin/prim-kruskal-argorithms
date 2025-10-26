package me.belyaikin.primskruskals.prim;

import me.belyaikin.primskruskals.graph.Graph;
import me.belyaikin.primskruskals.graph.Vertex;

import java.util.Collection;
import java.util.Comparator;

public record Prim<T>(Graph<T> graph) {
    public void run() {
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
        }
    }
}

package prim;

import graph.Vertex;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public record Prim<T>(List<Vertex<T>> graph) {
    public void run() {
        if (!graph.isEmpty()) graph.getFirst().setVisited(true);

        while(graph.stream().anyMatch(vertex -> !vertex.isVisited())) {
            graph.stream().filter(Vertex::isVisited)
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

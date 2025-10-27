package me.belyaikin.primskruskals.algorithm.kruskal;

import me.belyaikin.primskruskals.algorithm.Algorithm;
import me.belyaikin.primskruskals.algorithm.ExecutionResults;
import me.belyaikin.primskruskals.graph.Edge;
import me.belyaikin.primskruskals.graph.Graph;
import me.belyaikin.primskruskals.graph.Neighbor;
import me.belyaikin.primskruskals.graph.Vertex;

import java.util.*;

public class Kruskal<T> implements Algorithm {
    private final Graph<T> graph;

    public Kruskal(Graph<T> graph) {
        this.graph = graph;
    }

    @Override
    public ExecutionResults run() {
        int[] operations = {0};
        long startTime = System.nanoTime();

        List<EdgeWrapper<T>> edges = collectEdges(operations);

        edges.sort(Comparator.comparingInt(e -> e.edge.getWeight()));

        UnionFind<T> uf = new UnionFind<>(graph.vertices());

        int totalCost = 0;
        int edgeCount = 0;

        for (EdgeWrapper<T> ew : edges) {
            Vertex<T> u = ew.u;
            Vertex<T> v = ew.v;

            Vertex<T> rootU = uf.find(u, operations);
            Vertex<T> rootV = uf.find(v, operations);

            if (!rootU.equals(rootV)) {
                uf.union(u, v, operations);
                ew.edge.setIncluded(true);
                totalCost += ew.edge.getWeight();
                edgeCount++;

                if (edgeCount == graph.vertices().size() - 1)
                    break;
            }
        }

        long endTime = System.nanoTime();
        return new ExecutionResults(operations[0], endTime - startTime);
    }

    private static class EdgeWrapper<T> {
        Vertex<T> u;
        Vertex<T> v;
        Edge edge;

        EdgeWrapper(Vertex<T> u, Vertex<T> v, Edge edge) {
            this.u = u;
            this.v = v;
            this.edge = edge;
        }
    }

    private List<EdgeWrapper<T>> collectEdges(int[] ops) {
        List<EdgeWrapper<T>> edges = new ArrayList<>();
        Set<Edge> seen = new HashSet<>();

        for (Vertex<T> vertex : graph.vertices()) {
            for (Neighbor<T> neighbor : vertex.getNeighbors()) {
                Edge edge = neighbor.edge();
                ops[0]++;

                if (!seen.contains(edge)) {
                    seen.add(edge);
                    edges.add(new EdgeWrapper<>(vertex, neighbor.vertex(), edge));
                }
            }
        }
        return edges;
    }

    static class UnionFind<T> {
        private final Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();
        private final Map<Vertex<T>, Integer> rank = new HashMap<>();

        public UnionFind(List<Vertex<T>> nodes) {
            for (Vertex<T> node : nodes) {
                parent.put(node, node);
                rank.put(node, 0);
            }
        }

        public Vertex<T> find(Vertex<T> node, int[] ops) {
            ops[0]++;
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node), ops));
            }
            return parent.get(node);
        }

        public void union(Vertex<T> a, Vertex<T> b, int[] ops) {
            Vertex<T> rootA = find(a, ops);
            Vertex<T> rootB = find(b, ops);

            if (rootA.equals(rootB)) return;

            ops[0]++;
            int rankA = rank.get(rootA);
            int rankB = rank.get(rootB);

            if (rankA < rankB) {
                parent.put(rootA, rootB);
            } else if (rankA > rankB) {
                parent.put(rootB, rootA);
            } else {
                parent.put(rootB, rootA);
                rank.put(rootA, rankA + 1);
            }
        }
    }
}

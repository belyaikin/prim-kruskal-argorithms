package me.belyaikin.primskruskals;

import me.belyaikin.primskruskals.algorithm.ExecutionResults;
import me.belyaikin.primskruskals.algorithm.kruskal.Kruskal;
import me.belyaikin.primskruskals.algorithm.prim.Prim;
import me.belyaikin.primskruskals.graph.Graph;
import me.belyaikin.primskruskals.json.GraphParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmTester {
    public GraphParser parser;

    private List<Graph<String>> graphs = new ArrayList<>();

    public AlgorithmTester(GraphParser parser) {
        this.parser = parser;

        graphs.addAll(parser.getGraphs());
    }

    public Map<Integer, Map<String, ExecutionResults>> getExecutionResults() {
        Map<Integer, Map<String, ExecutionResults>> resultsMap = new HashMap<>();

        for (Graph<String> graph : graphs) {
            Graph<String> graphCopy = graph;
            ExecutionResults primsResults = new Prim<>(graph).run();
            ExecutionResults kruskalsResults = new Kruskal<>(graphCopy).run();

            Map<String, ExecutionResults> results = new HashMap<>();
            results.put("prims", primsResults);
            results.put("kruskals", kruskalsResults);

            resultsMap.put(graph.id(), results);
        }

        return resultsMap;
    }
}

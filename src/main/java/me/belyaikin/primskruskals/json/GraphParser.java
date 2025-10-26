package me.belyaikin.primskruskals.json;

import me.belyaikin.primskruskals.graph.Edge;
import me.belyaikin.primskruskals.graph.Graph;
import me.belyaikin.primskruskals.graph.Vertex;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public record GraphParser(String json) {
    public List<Graph<String>> getGraphs() {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonGraphs = jsonObject.getJSONArray("graphs");

        List<Graph<String>> graphs = new ArrayList<>();

        for (int i = 0; i < jsonGraphs.length(); i++) {
            JSONObject graphJson = jsonGraphs.getJSONObject(i);

            JSONArray verticesJson = graphJson.getJSONArray("vertices");
            Map<String, Vertex<String>> vertexMap = new LinkedHashMap<>();
            for (int j = 0; j < verticesJson.length(); j++) {
                String data = verticesJson.getString(j);
                vertexMap.put(data, new Vertex<>(data));
            }

            JSONArray edgesJson = graphJson.getJSONArray("edges");
            for (int j = 0; j < edgesJson.length(); j++) {
                JSONObject edgeJson = edgesJson.getJSONObject(j);

                String from = edgeJson.getString("from");
                String to = edgeJson.getString("to");
                int weight = edgeJson.getInt("weight");

                Vertex<String> fromVertex = vertexMap.get(from);
                Vertex<String> toVertex = vertexMap.get(to);

                if (fromVertex == null || toVertex == null) {
                    throw new IllegalArgumentException("Invalid edge reference: " + from + " -> " + to);
                }

                Edge edge = new Edge(weight);
                fromVertex.addNeighbor(toVertex, edge);
            }

            graphs.add(new Graph<>(new ArrayList<>(vertexMap.values())));
        }

        return graphs;
    }
}

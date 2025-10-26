package me.belyaikin.primskruskals.graph;

public class Edge {
    private final int weight;
    private boolean included;

    public Edge(int weight, boolean included) {
        this.weight = weight;
        this.included = included;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }
}

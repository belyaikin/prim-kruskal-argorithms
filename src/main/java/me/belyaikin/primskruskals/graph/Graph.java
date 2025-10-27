package me.belyaikin.primskruskals.graph;

import java.util.List;

public record Graph<T>(int id, List<Vertex<T>> vertices) {

}

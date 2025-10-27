package me.belyaikin.primskruskals;

import me.belyaikin.primskruskals.json.GraphParser;

public class Main {
    public static void main(String[] args) {
        String json = """
                {
                  "graphs": [
                    {
                      "id": 0,
                      "vertices": ["A", "B", "C", "D", "E"],
                      "edges": [
                        {"from": "A", "to": "B", "weight": 4},
                        {"from": "A", "to": "C", "weight": 3},
                        {"from": "B", "to": "C", "weight": 2},
                        {"from": "B", "to": "D", "weight": 5},
                        {"from": "C", "to": "D", "weight": 7},
                        {"from": "C", "to": "E", "weight": 8},
                        {"from": "D", "to": "E", "weight": 6}
                      ]
                    },
                    {
                      "id": 1,
                      "vertices": ["A", "B", "C", "D"],
                      "edges": [
                        {"from": "A", "to": "B", "weight": 1},
                        {"from": "A", "to": "C", "weight": 4},
                        {"from": "B", "to": "C", "weight": 2},
                        {"from": "C", "to": "D", "weight": 3},
                        {"from": "B", "to": "D", "weight": 5}
                      ]
                    }
                  ]
                }
                """;

        AlgorithmTester tester = new AlgorithmTester(new GraphParser(json));

        System.out.println(tester.getExecutionResults());
    }
}
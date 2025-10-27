package me.belyaikin.primskruskals;

import me.belyaikin.primskruskals.json.GraphParser;
import me.belyaikin.primskruskals.json.JsonFileProcessor;

public class Main {
    static void main() {
        AlgorithmTester tester = new AlgorithmTester(
                new GraphParser(
                        JsonFileProcessor.getInputJsonFile("input.json")
                )
        );

        JsonFileProcessor.createResultsJsonFile(
                "output.json",
                tester.getExecutionResults().toString()
        );
    }
}
package me.belyaikin.primskruskals;

import me.belyaikin.primskruskals.json.GraphParser;
import me.belyaikin.primskruskals.json.JsonFileProcessor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
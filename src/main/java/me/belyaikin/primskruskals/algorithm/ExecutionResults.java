package me.belyaikin.primskruskals.algorithm;

public class ExecutionResults {
    public int operations;
    public long executionTime;

    public ExecutionResults(int operations, long executionTime) {
        this.operations = operations;
        this.executionTime = executionTime;
    }

    public int getOperations() {
        return operations;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "ExecutionResults{" +
                "operations=" + operations +
                ", executionTime=" + executionTime +
                '}';
    }
}

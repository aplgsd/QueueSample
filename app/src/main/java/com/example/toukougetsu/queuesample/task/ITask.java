package com.example.toukougetsu.queuesample.task;

public interface ITask extends Comparable<ITask> {
    void run();

    void setPriority(Priority priority);

    Priority getPriority();
}

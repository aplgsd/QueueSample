package com.example.toukougetsu.queuesample.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TaskQueue {

    private BlockingQueue<ITask> mTaskQueue;

    private TaskExecutor[] mTaskExecutors;

    public TaskQueue(int size) {
        mTaskQueue = new LinkedBlockingDeque<>();
        mTaskExecutors = new TaskExecutor[size];
    }

    public void start() {
        stop();

        for (int i = 0; i < mTaskExecutors.length; i++) {
            mTaskExecutors[i] = new TaskExecutor(mTaskQueue);
            mTaskExecutors[i].start();
        }
    }

    public void stop() {
        if (mTaskExecutors != null) {
            for (TaskExecutor taskExecutor : mTaskExecutors) {
                if (taskExecutor != null) taskExecutor.quit();
            }
        }
    }

    public <T extends ITask> int add(T task) {
        if (!mTaskQueue.contains(task)) {
            mTaskQueue.add(task);
        }

        return mTaskQueue.size();
    }
}

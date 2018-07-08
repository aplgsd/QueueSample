package com.example.toukougetsu.queuesample.task;

import java.util.concurrent.BlockingQueue;

// 窗口
public class TaskExecutor extends Thread {

    // 在窗口拍的队，这个队里面是办事的人。
    private BlockingQueue<ITask> taskQueue;

    // 这个办事窗口是否在等待着办事。
    private boolean isRunning = true;

    public TaskExecutor(BlockingQueue<ITask> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void quit() {
        isRunning = false;
        interrupt();
    }

    @Override
    public void run() {
        while(isRunning) {
            ITask iTask;

            try {
                iTask = taskQueue.take();
            } catch (InterruptedException e) {
                if (!isRunning) {
                    interrupt();
                    break;
                }

                continue;
            }

            iTask.run();
        }
    }

}

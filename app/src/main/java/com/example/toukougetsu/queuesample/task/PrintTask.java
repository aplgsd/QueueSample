package com.example.toukougetsu.queuesample.task;

import android.support.annotation.NonNull;
import android.util.Log;

public class PrintTask implements ITask {

    private int id;

    public PrintTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException iqnored) {

        }

        Log.d("Queue", "my id is ===> " + id);
    }

    @Override
    public void setPriority(Priority priority) {

    }

    @Override
    public Priority getPriority() {
        return null;
    }

    @Override
    public int compareTo(@NonNull ITask iTask) {
        return 0;
    }
}

package com.example.toukougetsu.queuesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.toukougetsu.queuesample.task.PrintTask;
import com.example.toukougetsu.queuesample.task.TaskQueue;
import com.example.toukougetsu.queuesample.task2.ConcurrentLinkedQueueSample;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TaskQueue taskQueue = new TaskQueue(1);
//                taskQueue.start();
//
//                for (int i = 0; i < 10; i++) {
//                    PrintTask task = new PrintTask(i);
//                    taskQueue.add(task);
//                }
                ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
                Thread producer = new Thread(new Producer(queue));
                Thread consumer = new Thread(new Consumer(queue));
                producer.start();
                consumer.start();
            }
        });
    }

    // the producer puts strings on the queue
    class Producer implements Runnable {

        ConcurrentLinkedQueue<String> queue;
        Producer(ConcurrentLinkedQueue<String> queue){
            this.queue = queue;
        }
        public void run() {
            System.out.println("Producer Started");
            try {
                for (int i = 1; i < 10; i++) {
                    queue.add("String" + i);
                    System.out.println("Added: String" + i);
                    Thread.currentThread().sleep(200);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // the consumer removes strings from the queue
    class Consumer implements Runnable {

        ConcurrentLinkedQueue<String> queue;
        Consumer(ConcurrentLinkedQueue<String> queue){
            this.queue = queue;
        }
        public void run() {
            String str;
            System.out.println("Consumer Started");
            for (int x = 0; x < 10; x++) {
                while ((str = queue.poll()) != null) {
                    System.out.println("Removed: " + str);
                }
                try {
                    Thread.currentThread().sleep(500);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

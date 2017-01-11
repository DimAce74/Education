package ru.itis;

import java.util.Deque;
import java.util.LinkedList;

public class WorkQueue {
    // количество потоков
    private final int threadsCount;

    // потоки
    private final PoolWorker[] threads;

    // задачи
    private final Deque<Runnable> tasks;

    public WorkQueue(int threadsCount) {
        this.threadsCount = threadsCount;

        tasks = new LinkedList<Runnable>();

        threads = new PoolWorker[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        // блокируем очередь задач
        synchronized (tasks) {
            tasks.add(task);
            // метод класса Object, который оповещает поток
            // о том, что объект свободен
            tasks.notify();
        }
    }
    private class PoolWorker extends Thread {
        public void run() {
            // текущая задача из очереди задач
            Runnable task;
            // бесконечный цикл
            while (true) {
                // блокируем очередь
                synchronized (tasks) {
                    // пока очередь пустая
                    while (tasks.isEmpty()) {
                        try {
                            // попали в список ожидания на этом объекте
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalArgumentException();
                        }
                    }
                }
                // достаем задачу из очередь
                task = tasks.removeFirst();

                task.run();
            }
        }
    }

}

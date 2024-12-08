package concurrency.virtualthreads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Virtual Threads are officially available
 * since Java 21
 * <p>
 * Key features of Virtual Threads:
 * ** Virtual threads are always daemon threads, meaning they'll keep the containing JVM process alive until they complete;
 * ** It's impossible to change priority of virtual threads;
 * ** Semaphores should be used to control the number of virtual threads when accessing a resource like a datastore
 */
public class VirtualThreadsMain {

    public static void main(String[] args) {
        singleVirtualThread();

        virtualThreadPool();

        platformThreadPool();

        virtualExecutor();

        virtualVsPlatformDiff(true);
        virtualVsPlatformDiff(false);
    }

    private static void singleVirtualThread() {
        Thread vt1 = Thread.ofVirtual().start(() -> System.out.println("I'm virtual, at last!!!"));
        Thread vt2 = Thread.startVirtualThread(() -> System.out.println("I'm virtual as well!"));

        System.out.printf("Thread info: name=%s, threadId=%s, isVirtual=%s, isAlive=%s, state=%s %n",
                vt1, vt1.threadId(), vt1.isVirtual(), vt1.isAlive(), vt1.getState());

        System.out.printf("Thread info: name=%s, threadId=%s, isVirtual=%s, isAlive=%s, state=%s %n",
                vt2, vt2.threadId(), vt2.isVirtual(), vt2.isAlive(), vt2.getState());
    }

    private static void virtualThreadPool() {
        // Start a virtual thread to run a task
        Thread.ofVirtual().factory().newThread(() -> System.out.println("I'm thread from Virtual ThreadFactory")).start();
    }

    private static void platformThreadPool() {
        // Start a platform thread to run a task
        Thread.ofPlatform().factory().newThread(() -> System.out.println("I'm thread from Platform ThreadFactory")).start();

        // Start a daemon thread to run a task
        Thread daemon1 = Thread.ofPlatform().daemon().start(() -> System.out.println("I'm daemon thread from Platform ThreadFactory"));

        // Create an unstarted thread with name "duke",
        // its start() method must be invoked to schedule it to execute.
        Thread dukeNukem = Thread.ofPlatform().name("duke-nukem").unstarted(() -> System.out.println("I'm Duke, I'll Nuke-em!!!"));
        dukeNukem.start();

        // A ThreadFactory that creates daemon threads named "worker-0", "worker-1", ...
        ThreadFactory factory = Thread.ofPlatform().daemon().name("worker-", 0).factory();
        Thread d1 = factory.newThread(() -> System.out.println("I'm daemon 1 thread from Platform ThreadFactory"));
        Thread d2 = factory.newThread(() -> System.out.println("I'm daemon 2 thread from Platform ThreadFactory"));
        d1.start();
        d2.start();
    }

    private static void virtualExecutor() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Executor thread 1"));
            executor.submit(() -> System.out.println("Executor thread 2"));
        }
    }

    private static void virtualVsPlatformDiff(boolean vThreads) {
        System.out.println("Using vThreads: " + vThreads);

        long start = System.currentTimeMillis();

        Random random = new Random();
        Runnable runnable = () -> {
            double i = random.nextDouble(1000) % random.nextDouble(1000);
        };
        for (int i = 0; i < 50000; i++) {
            if (vThreads) {
                Thread.startVirtualThread(runnable);
            } else {
                Thread t = new Thread(runnable);
                t.start();
            }
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.printf("Run time for %s %d ms%n", (vThreads ? "virtual threads" : "regular threads"), timeElapsed);
    }

}

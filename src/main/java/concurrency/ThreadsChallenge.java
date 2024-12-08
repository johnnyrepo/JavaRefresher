package concurrency;

public class ThreadsChallenge {

    public static void main(String[] args) {
        Thread evenThread = new EvenNumbersThread();
        Runnable runnable = () -> {
            for (int i = 1; i <= 9; i += 2) {
                System.out.printf("%d ", i);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread oddThread = new Thread(runnable);

        evenThread.start();
        oddThread.start();
    }

}

class EvenNumbersThread extends Thread {
    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.printf("%d ", i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package day18;

class Communication implements Runnable {
    private int number = 1;
    private Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                } else {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Communication communication = new Communication();
        Thread t1 = new Thread(communication);
        Thread t2 = new Thread(communication);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}

package day18;

public class ThreadTest01 {
    public static void main(String[] args) {
        // 3.创建MyThread类的对象
        MyThread myThread = new MyThread();
        // 4.通过此对象调用Thread类的start()方法，启动线程
        myThread.start();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i + "**********main()**********");
            }
        }
    }
}

// 1.创建一个继承于Thread类的子类
class MyThread extends Thread {
    // 2.重写run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
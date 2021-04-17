package day18;

public class ThreadTest02 {
    public static void main(String[] args) {
        // 3.创建实现类的对象
        MThread mThread = new MThread();
        // 4.将实现类的对象传递给Thread类的构造器，创建Thread类的对象
        Thread t1 = new Thread(mThread);
        // 5.通过Thread类的对象启动线程
        t1.start();
    }
}
// 1.创建一个类，实现Runnable接口
class MThread implements Runnable {
    // 2.实现run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
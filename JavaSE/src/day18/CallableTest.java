package day18;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 1.创建一个Callable接口的实现类
class MyCallableThread implements Callable<Integer> {
    // 2.重写call()方法，将此线程需要执行的操作声明在call()方法中，call()方法可以有返回值，且可以抛出异常
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        // 3.创建第一步中实现类的对象
        MyCallableThread myCallableThread = new MyCallableThread();
        // 4.将实现类对象作为参数传递给FutureTask类的构造器，创建FutureTask对象
        FutureTask<Integer> futureTask = new FutureTask<>(myCallableThread);
        // 5.将FutureTask对象作为参数传递给Thread类的构造器，创建Thread对象
        Thread thread = new Thread(futureTask);
        // 6.通过Thread对象调用start()方法，启动线程
        thread.start();
        try {
            // 7.获取Callable实现类中重写的call()方法的返回值
            Integer sum = futureTask.get(); // get()方法的返回值即为传给FutureTask构造器的实现类中重写的call()方法的返回值
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

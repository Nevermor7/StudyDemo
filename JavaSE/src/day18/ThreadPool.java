package day18;

import java.util.concurrent.*;

/**
 * 使用线程池创建线程
 */
public class ThreadPool {
    public static void main(String[] args) {
        // 1.创建指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(executorService.getClass()); // 查看executorService实际上是哪个类的对象
        ThreadPoolExecutor service = (ThreadPoolExecutor) executorService;

        // 通过service就可以管理线程池了
        service.setCorePoolSize(15);
        service.setMaximumPoolSize(100);
        service.setKeepAliveTime(20, TimeUnit.SECONDS);

        // 2.执行指定线程的操作，需要提供实现Runnable接口或Callable接口的实现类的对象。submit()方法可以获取返回值
        // service.execute(Runnable runnable);
        Future<Integer> future1 = executorService.submit(new NumberThread1());
        Future<Integer> future2 = executorService.submit(new NumberThread2());
        try {
            Integer sum1 = future1.get();
            Integer sum2 = future2.get();
            System.out.println("偶数和为：" + sum1);
            System.out.println("奇数和为：" + sum2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 3.关闭连接池
        executorService.shutdown();
    }
}
class NumberThread1 implements Callable<Integer> {
    // 输出100以内的偶数，并获取总和
    private int sum = 0;
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
                sum += i;
            }
        }
        return sum;
    }
}
class NumberThread2 implements Callable<Integer> {
    // 输出100以内的奇数，并获取总和
    private int sum = 0;
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
                sum += i;
            }
        }
        return sum;
    }
}
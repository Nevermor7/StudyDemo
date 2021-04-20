package day29.stream;

import day29.methodref.Employee;
import day29.methodref.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 测试Stream的实例化
 * @author Bruce
 * @create 2021/04/20 17:43
 */
public class StreamAPITest1 {
    // 创建Stream的方式一：通过集合
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();
        // Collection中的default Stream<E> stream()方法，返回一个顺序流
        Stream<Employee> stream = list.stream();
        // Collection中的default Stream<E> parallelStream()方法，返回一个并行流
        Stream<Employee> parallelStream = list.parallelStream();
    }
    // 创建Stream的方式二：通过数组
    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        IntStream stream = Arrays.stream(arr);
    }
    // 创建Stream的方式三：of()
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    }
    // 创建Stream的方式四：创建无限流
    @Test
    public void test4() {
        // 迭代
        // 遍历前10个偶数，limit限制个数，forEach终止操作
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);
        // 生成
        // 生成十个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}

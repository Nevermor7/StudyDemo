package day29.stream;

import day29.methodref.Employee;
import day29.methodref.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 * @author Bruce
 * @create 2021/04/20 18:17
 */
public class StreamAPITest2 {
    // 1.筛选与切片
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();
        // filter(Predicate p)：接收Lambda，从流中排除某些元素
        // 筛选工资大于7000的Employee对象
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println();

        // distinct()：筛选，通过流所生成元素的`hashCode()`和`equals()`去除重复元素
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 41, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.stream().distinct().forEach(System.out::println);
        System.out.println();

        // limit(long maxSize)：截断流，使其元素不超过指定数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
        // skip(long n)：跳过元素，返回一个舍弃了前`n`个元素的流。若流中元素总数不足`n`个，则返回一个空流。
        list.stream().skip(3).forEach(System.out::println);
    }

    // 2.映射
    @Test
    public void test2() {
        // map(Function f)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println();

        // 练习1：获取姓名长度大于3的员工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();

        // flatMap(Function f)：接收一个函数作为参数，把流中的每个值都换成另一个流，然后把所有流连接成一个流
        // flatMap()和map()的区别：
        // map返回的结果：[[a,a],[b,b],[c,c],[d,d]]，不会对流中流做处理，直接返回流的流
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest2::fromStringToCharacterStream);
        streamStream.forEach(stream -> { // 流中流的遍历需要多层循环，明显更麻烦
            stream.forEach(System.out::println);
        });
        // flatMap返回的结果：[a,a,b,b,c,c,d,d]，会把流中流的元素提取出来再组装成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest2::fromStringToCharacterStream);
        characterStream.forEach(System.out::println); // 一层遍历解决
    }
    // 将字符串转换为字符stream
    public static Stream<Character> fromStringToCharacterStream(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 3.排序
    @Test
    public void test3() {
        // sorted()：产生一个新流，其中的元素按自然顺序排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);
        // 抛出异常：java.lang.ClassCastException
        // 原因：Employee类没有实现Comparable接口，无法进行自然排序
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com)：产生一个新流，其中的元素按比较器顺序排序
        List<Employee> employees = EmployeeData.getEmployees();
        // 按年龄排列，年龄相等的按工资排序
        employees.stream().sorted( (e1, e2) -> {
            int ageComp = Integer.compare(e1.getAge(), e2.getAge());
            if (ageComp != 0) {
                return ageComp;
            } else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);
    }
}

package day29.stream;

import day29.methodref.Employee;
import day29.methodref.EmployeeData;
import org.junit.Test;
import sun.plugin2.gluegen.runtime.CPU;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试Stream的终止操作
 *
 * @author Bruce
 * @create 2021/04/20 20:13
 */
public class StreamAPITest3 {
    // 1.匹配与查找
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        // allMatch(Predicate p)：检查是否匹配所有元素
        // 练习：是否所有员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println("是否所有员工的年龄都大于18?\t" + allMatch);

        // anyMatch(Predicate p)：检查是否有一个元素匹配
        // 练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println("是否存在员工的工资大于10000?\t" + anyMatch);

        // noneMatch(Predicate p)：检查是否没有匹配所有元素
        // 练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println("是否没有员工姓“雷”?\t" + noneMatch);

        // findFirst()：返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        // findAny()：返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);

        // count()：返回流中元素总数
        // 练习：工资大于5000的员工人数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println("工资大于5000的员工人数为：" + count);

        // max(Comparator c)：返回流中最大值
        // 练习：返回最高的工资
        // 写法一
        OptionalDouble max = employees.stream().mapToDouble(Employee::getSalary).max();
        System.out.println("最高工资为：" + max);
        // 写法二
        Optional<Double> max1 = employees.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println("最高工资为：" + max1);

        // min(Comparator c)：返回流中最小值
        // 练习：返回工资最低的员工
        // 写法一
        Optional<Employee> min = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println("工资最低的员工是：" + min);
        // 写法二
        Optional<Employee> min1 = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("工资最低的员工是：" + min1);

        // forEach(Consumer c)：内部迭代
        employees.parallelStream().forEach(System.out::println);
        // 集合中的遍历
        employees.forEach(System.out::println);
    }
    // 2.规约
    @Test
    public void test2() {
        // reduce(T iden, BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回T
        // 练习：计算1-10自然数的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        // reduce(BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        // 计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> reduce1 = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println("公司所有员工工资的总和:" + reduce1);
    }

    // 3.收集
    @Test
    public void test3() {
        // collect(Collector c)：将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
        // 练习1：查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println();

        Set<Employee> collect1 = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        collect1.forEach(System.out::println);

        Collection<Employee> collection = employees.stream().collect(Collectors.toCollection(ArrayList::new));

        Long count = employees.stream().collect(Collectors.counting());

        Integer ageSum = employees.stream().collect(Collectors.summingInt(Employee::getAge));

        Double ageAvg = employees.stream().collect(Collectors.averagingInt(Employee::getAge));

        IntSummaryStatistics intSummaryStatistics = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
        intSummaryStatistics.getAverage();
        intSummaryStatistics.getCount();
        intSummaryStatistics.getSum();
        intSummaryStatistics.getMax();
        intSummaryStatistics.getMin();

        String nameStr = employees.stream().map(Employee::getName).collect(Collectors.joining());

        Optional<Employee> max = employees.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        Optional<Employee> min = employees.stream().collect(Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)));

        Integer ageTotal = employees.stream().collect(Collectors.reducing(0, Employee::getAge, Integer::sum));

        Integer size = employees.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(size);

        Map<Integer, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        map.forEach((key, value) -> System.out.println(key + "---" + value));

        Map<Boolean, List<Employee>> map1 = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        map1.forEach((key,value) -> System.out.println("工资是否大于5000：" + key + "，员工列表：" + value));
    }
}

package day29.stream;

import day29.methodref.Employee;
import day29.methodref.EmployeeData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bruce
 * @create 2021/04/22 0:15
 */
public class DoubleStreamTest {

    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().mapToDouble(Employee::getAge).forEach(System.out::println);
//        employees.stream().collect(Collectors.toMap())
    }
}

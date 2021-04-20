package day28;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Bruce
 * @create 2021/04/19 14:01
 */
public class NewInstanceTest {

    @Test
    public void test1() {
        // 1.获取Class类对象
        Class<Person> clazz = Person.class;
        // 2.获取构造器
        Constructor<Person> cons = null;
        Person p = null;
        try {
            cons = clazz.getConstructor();
            // 3.创建运行时类对象
            p = cons.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(p);
    }
}

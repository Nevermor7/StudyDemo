package day28;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Bruce
 * @create 2021/04/18 18:40
 */
public class ReflectionTest {
    // 反射之前，对于Person的操作
    @Test
    public void test1() {
        // 1.创建Person类的对象
        Person p1 = new Person("Tom", 12);
        // 2.通过对象，调用其内部的属性和方法
        p1.age = 10;
        System.out.println(p1.toString());
        p1.show();
        // 在Person类的外部，不可以通过Person类的对象直接调用其内部私有的结构
    }

    // 反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        // 1.获取Person类的类对象
        Class clazz = Person.class;
        // 2.获取Person的对象。可以通过Class.newInstance()或Constructor.newInstance()
        // Object obj = clazz.newInstance();
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        // 3.通过反射，调用对象指定的属性、方法
        // 调用属性
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(p,10);
        System.out.println(p.toString());
        // 调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);
        // 通过反射，可以调用Person类的私有结构
        // 调用私有的构造器
        System.out.println("********************************");
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Object obj1 = constructor1.newInstance("Jerry");
        Person p1 = (Person) obj1;
        System.out.println(p1);
        // 调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "HanMeimei");
        System.out.println(p1);
        // 调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "中国");// 相当于p1.showNation("中国");也有返回值
        System.out.println(nation);
    }
}

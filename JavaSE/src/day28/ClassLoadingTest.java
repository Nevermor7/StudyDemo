package day28;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Bruce
 * @create 2021/04/19 10:33
 */
public class ClassLoadingTest {

    @Test
    public void test1() {
        // 对于自定义的类，使用系统类加载器加载
        ClassLoader classLoader1 = ClassLoadingTest.class.getClassLoader();
        System.out.println(classLoader1); // sun.misc.Launcher$AppClassLoader@18b4aac2
        // 调用系统类加载器的getParent()方法，获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2); // sun.misc.Launcher$ExtClassLoader@27716f4
        // 调用扩展类加载器的getParent()方法，无法获取引导类加载器
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3); // null
        // 通过Java的核心类也无法获取引导类加载器
        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4); // null
    }

    @Test
    public void test2() {
        // 配置文件在src下
        // IO流读取配置文件：默认路径为当前module下
        Properties pros = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src\\jdbc.properties");
            pros.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 使用类加载器读取配置文件：默认路径为src目录下
//        ClassLoader classLoader = ClassLoadingTest.class.getClassLoader();
//        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
//        try {
//            pros.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ", password = " + password);
    }

    public static void main(String[] args) {
        // 主动引用
        /*A a = new A(); // 会导致A和Father类的初始化
        System.out.println(A.m); // 会导致A和Father类的初始化
        try {
            Class.forName("day28.A"); // 会导致A和Father类的初始化
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        // 被动引用
        A[] array = new A[5]; // 不会导致A和Father类的初始化
        System.out.println(A.b); // 只初始化Father类
        System.out.println(A.M); // 不会导致A和Father类的初始化
    }
    static {
        System.out.println("main所在的类"); // 最先初始化main()方法所在的类
    }
}
class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}
class A extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}
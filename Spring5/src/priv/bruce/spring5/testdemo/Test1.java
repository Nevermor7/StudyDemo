package priv.bruce.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bruce
 * @create 2021/04/21 10:05
 */
public class Test1 {
    @Test
    public void testAdd() {
        // 1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        ApplicationContext context1 = new ClassPathXmlApplicationContext("bean1.xml");
        System.out.println(context1==context);

        // 2.获取配置文件中创建的对象
        User user = context.getBean("user", User.class);

        System.out.println(user);
        user.add();
    }
}

package day29;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理举例
 *
 * @author Bruce
 * @create 2021/04/19 21:00
 */
interface Human {
    String getBelief();
    void eat(String food);
}
// 被代理类
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
/*
要想实现动态代理，需要解决哪些问题？
问题一：如何根据加载到内存中的被代理类，动态地创建一个代理类及其对象。
问题二：当通过代理类的对象调用方法时，如何动态地去调用被代理类中的同名方法。
 */
// 2.创建一个代理工厂，用于根据被代理类动态生成代理类
class ProxyFactory {
    // 调用此方法，返回一个代理类的对象。解决问题一
    public static Object getProxyInstance(Object obj) { // obj：被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        // 3.将被代理类对象传递给实现InvocationHandler接口的类
        handler.bind(obj);
        // 4.通过java.lang.reflect.Proxy类的静态方法newProxyInstance创建并返回代理类
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}
// 1.创建一个实现InvocationHandler接口的类，重写invoke方法
class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    // 获取被代理类对象
    public void bind(Object obj) {
        this.obj = obj;
    }

    // 当通过代理类的对象调用方法a时，就会自动地调用如下地invoke()方法
    // 将被代理类要执行地方法a的功能声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // method就是代理类对象proxy调用的方法，传入被代理类对象obj即被代理类也调用了method方法
        Object returnValue = method.invoke(obj, args);
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        // proxyInstance为代理类对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        // 通过代理类对象调用方法时，会自动调用被代理类中的同名方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("重庆火锅");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();
    }
}

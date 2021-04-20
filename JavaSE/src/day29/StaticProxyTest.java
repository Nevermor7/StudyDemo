package day29;

/**
 * 静态代理举例
 *
 * @author Bruce
 * @create 2021/04/19 20:53
 */
interface ClothFactory {
    void produceCloth();
}
// 代理类
class ProxyClothFactory implements ClothFactory {

    private ClothFactory factory; // 拿被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂开始准备......");
        factory.produceCloth();
        System.out.println("代理工厂收尾工作......");
    }
}
// 被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产运动服......");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        ClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }
}

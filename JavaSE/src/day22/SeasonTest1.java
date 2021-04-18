package day22;

/**
 * @author Bruce
 * @create 2021-04-17 17:22
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        System.out.println(summer);
        System.out.println(Season1.class.getSuperclass());
        Season1.values();
        Season2 autumn = Season2.AUTUMN;
        autumn.show();
    }
}

// 使用enum关键字定义枚举类
enum Season1 {
    // 1.提供当前枚举类的对象，传入的参数和构造器一致。多个对象之间用","隔开，最后一个对象以";"结束
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","冰天雪地");
    // 2.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    // 3.私有化构造器，并给对象属性赋值
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }


    // 4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 使用enum定义的枚举类默认继承于java.lang.Enum类，Enum类中重写了toString()方法
    // 4.其他诉求2：提供toString()方法
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }
}
enum Season2 implements Info{
    SPRING{
        @Override
        public void show() {
            System.out.println("春天在哪里");
        }
    },
    SUMMER{
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN{
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER{
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    @Override
    public void show() {
        System.out.println("这是一个季节枚举类");
    }
}
interface Info {
    void show();
}
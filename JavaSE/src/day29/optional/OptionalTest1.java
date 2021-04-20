package day29.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author Bruce
 * @create 2021/04/20 23:00
 */
public class OptionalTest1 {

    @Test
    public void test1() {
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }

    @Test
    public void test2() {
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
    }

    @Test
    public void test3() {
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    // 原始写法
    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }
    // 原始写法优化
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }
    // 使用Optinal类
    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("黄蓉")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.of(girl);
        Girl girl1 = girlOptional.orElse(new Girl("小龙女"));
        return girl1.getName();
    }

}

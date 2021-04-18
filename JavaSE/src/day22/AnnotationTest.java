package day22;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * @author Bruce
 * @create 2021/04/18 0:42
 */
public class AnnotationTest {

    /**
     *
     * @param name
     * @param age
     */
    public void show(String name, int age){

    }

}
@Documented
@interface MyAnnotation {

}
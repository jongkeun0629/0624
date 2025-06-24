import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface CustomInfo{
    String author();
    String date();
    int version() default 1;
}

@CustomInfo(author = "jongkeun", date = "2025-06-24", version = 2)
class Demo{

    @CustomInfo(author = "method", date = "2025-06-27")
    public void display(){
        System.out.println("display method");
    }
}

public class Main {
    public static void main(String[] args) {
        Demo demo = new Demo();

        Class<?> demoClass = demo.getClass();

        // 클래스
        if (demoClass.isAnnotationPresent(CustomInfo.class)){
            CustomInfo classInfo = demoClass.getAnnotation(CustomInfo.class);
            System.out.println("<Class>");
            System.out.println("Author: " + classInfo.author());
            System.out.println("Date: " + classInfo.date());
            System.out.println("Version: " + classInfo.version());
        }

        System.out.println();

        // 메서드
        try {
            Method m = demoClass.getMethod("display");

            if (m.isAnnotationPresent(CustomInfo.class)){
                CustomInfo mi = m.getAnnotation(CustomInfo.class);
                System.out.println(
                        "<Method>\nAuthor: " + mi.author() +
                        ", Date: " + mi.date() +
                        ", Version: " + mi.version()
                );
            }

        } catch (NoSuchMethodException e){
            e.getStackTrace();
        }
    }
}
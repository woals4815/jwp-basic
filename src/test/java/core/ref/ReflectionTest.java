package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.util.Arrays;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        Arrays.asList(clazz.getDeclaredMethods()).forEach(System.out::println);
        Arrays.asList(clazz.getDeclaredFields()).forEach(System.out::println);
        Arrays.asList(clazz.getDeclaredConstructors()).forEach(System.out::println);
        Arrays.asList(clazz.getDeclaredAnnotations()).forEach(System.out::println);
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
    }
}

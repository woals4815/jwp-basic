package core.ref;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    public void newInstanceWithConstructorArgs() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        Constructor<User> constructor = (Constructor<User>) clazz.getDeclaredConstructors()[0];
        User user = constructor.newInstance(
                "userId",
                "name",
                "password",
                "email"
        );
        Assert.assertEquals("userId", user.getUserId());
    }
    
    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        Student student = new Student();

        Field name = clazz.getDeclaredField("name");
        Field age = clazz.getDeclaredField("age");

        name.setAccessible(true);
        age.setAccessible(true);

        name.set(student, "name");
        age.setInt(student, 20);

        Assert.assertEquals("name", student.getName());
        Assert.assertEquals(20, student.getAge());
    }
}

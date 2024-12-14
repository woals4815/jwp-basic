package core.ref;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Arrays.asList(clazz.getDeclaredMethods()).forEach(
                (method -> {
                    Pattern pattern = Pattern.compile("^test");
                    String methodName = method.getName();
                    Matcher matcher = pattern.matcher(methodName);
                    if (matcher.find()) {
                        try {
                            method.invoke(clazz.newInstance());
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
        );
    }
}

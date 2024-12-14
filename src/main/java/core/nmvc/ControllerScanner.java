package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerScanner {
    private static final Logger log = LoggerFactory.getLogger(ControllerScanner.class);
    private Map<Class<?>, Object> controllers = new HashMap<>();
    public ControllerScanner() {
        Reflections refs = new Reflections("next");
        Set<Class<?>> annotated = refs.getTypesAnnotatedWith(Controller.class);
        this.createInstances(annotated);
    }

    public Map<Class<?>, Object> getControllers() {
        return this.controllers;
    }

    private void createInstances(Set<Class<?>> annotated) {
        annotated.forEach((annotatedClass) -> {
            try {
                this.controllers.put(annotatedClass, annotatedClass.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

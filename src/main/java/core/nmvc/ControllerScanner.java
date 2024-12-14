package core.nmvc;

import core.annotation.Controller;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 1. @Controller 설정이 돼 있는 모든 Class를 찾는다
 * 2. 각 클래스에 대한 instance를 생성한다.
 */
public class ControllerScanner {
    private Map<Class<?>, Object> controllers = new HashMap<>();

    public ControllerScanner() {
        Reflections refs = new Reflections("next");
        Set<Class<?>> annotateds = refs.getTypesAnnotatedWith(Controller.class);
        this.createControllers(annotateds);
    }

    Map<Class<?>, Object> getControllers() {
        return this.controllers;
    }

    private void createControllers(Set<Class<?>> annotateds) {
        annotateds.forEach((annotate) -> {
            try {
                controllers.put(annotate, annotate.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }


}

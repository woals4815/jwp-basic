package core.nmvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.reflections.ReflectionUtils;

public class AnnotationHandlerMapping implements HandlerMapping {
    private Object[] basePackage;
    private Map<Class<?>, Object> controllers;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
        ControllerScanner scanner = new ControllerScanner();
        controllers = scanner.getControllers();
    }

    public void initialize() {
        controllers.entrySet().forEach((controller) -> {
            Class<?> clazz = controller.getKey();
            Object instance = controller.getValue();
            Set<Method> annotatedMethods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));

            annotatedMethods.forEach((method) -> {
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                HandlerKey handlerKey = createHandlerKey(rm);
                this.handlerExecutions.put(handlerKey, new HandlerExecution(
                        instance,
                        method
                ));
            });
        });
    }

    private HandlerKey createHandlerKey(RequestMapping rm) {
        return new HandlerKey(rm.value(), rm.method());
    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return handlerExecutions.get(new HandlerKey(requestUri, rm));
    }
}

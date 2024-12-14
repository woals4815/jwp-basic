package core.nmvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationHandlerMapping implements HandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(AnnotationHandlerMapping.class);
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        ControllerScanner scanner = new ControllerScanner();
        Map<Class<?>, Object> controllers = scanner.getControllers();
        controllers.entrySet().stream().forEach(entry -> {
            Class<?> clazz = entry.getKey();
            Object controller = entry.getValue();
            Set<Method> methods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));
            methods.forEach((method) -> {
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                HandlerKey handlerKey = this.createHandlerKey(rm);
                this.handlerExecutions.put(handlerKey, new HandlerExecution(
                        controller,
                        method
                ));
            });
        });
    }

    private HandlerKey createHandlerKey(RequestMapping rm) {
        return new HandlerKey(rm.value(), rm.method());
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        HandlerExecution handlerExecution =  handlerExecutions.get(new HandlerKey(requestUri, rm));
        return handlerExecution;
    }
}

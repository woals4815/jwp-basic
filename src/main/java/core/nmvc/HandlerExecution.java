package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;

import java.lang.reflect.Method;

public class HandlerExecution {
    private Object declaredClass;
    private Method invokeMethod;


    public HandlerExecution(
            Object declaredClass,
            Method method
    ) {
        this.declaredClass = declaredClass;
        this.invokeMethod = method;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return (ModelAndView) this.invokeMethod.invoke(declaredClass, request, response);
    }
}

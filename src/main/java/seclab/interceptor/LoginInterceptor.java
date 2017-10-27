package seclab.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import seclab.annotation.LoginAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

import static seclab.config.Constants.SESSION_KEY_PREFIX;


/**
 * User: Harlan1994
 * Date: 2017/7/29
 * Time: 10:43
 * Description: 登录拦截器，如果没有登录，将会跳转到登录界面
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute(SESSION_KEY_PREFIX) == null) {
                response.sendRedirect("/login");
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
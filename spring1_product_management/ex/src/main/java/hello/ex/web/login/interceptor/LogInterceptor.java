package hello.ex.web.login.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String UUID = "UUID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = java.util.UUID.randomUUID().toString();
        request.setAttribute(UUID, uuid);

        log.info("REQUEST [{}][{}][{}][{}]", uuid, requestURI, request.getDispatcherType(), LocalDateTime.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(UUID);

        log.info("RESPONSE [{}][{}][{}][{}]", uuid, requestURI, request.getDispatcherType(), LocalDateTime.now());
    }
}

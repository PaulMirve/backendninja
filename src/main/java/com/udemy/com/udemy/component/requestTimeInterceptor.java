package com.udemy.com.udemy.component;

import com.udemy.com.udemy.repository.LogRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component("RequestTimeInterceptor")
public class requestTimeInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("logRepository")
    private LogRepository logRepository;

    private static final Log log = LogFactory.getLog(requestTimeInterceptor.class);

    @Override
    //Este método se ejecuta primero antes de ejecutar el return del controlador
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }
    //Este método se ejecuta segundo y ants de ejecutar el return del método del controlador
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long)request.getAttribute("startTime");
        String url = request.getRequestURL().toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if(auth != null&& auth.isAuthenticated() ){
            username = auth.getName();
        }
        logRepository.save( new com.udemy.com.udemy.entity.Log(new Date(), auth.getDetails().toString()
                , username, url));
        log.info("URL to: '"+url+"' -- in: '"
                + (System.currentTimeMillis() - startTime) + "' ms");
    }
}

package org.jack.rock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SessionConfig
 *
 * @author zhengzhe17
 * @CreateTime 2020/8/26
 */
@Configuration
public class SessionConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/create")
                .addPathPatterns("/*");
    }

    @Configuration
    public class SessionInterceptor implements HandlerInterceptor{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession httpSession = request.getSession();
            if(httpSession.getAttribute(httpSession.getId()) != null) {
                return true;
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"code\": 500,\"msg\": \"session失效，请重新登录\"}");
            return false;
        }
    }
}

package com.elf.core.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: elf
 * @description: WebServiceAuthFilter
 * @author: Liyiming
 * @create: 2018-05-27 12:35
 **/
public class WebServiceAuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(WebServiceAuthFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String username = request.getParameter("elf_username");
        String password = request.getParameter("elf_password");

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            filterChain.doFilter(request, response);
        } else {
            try {
                HttpSession httpSession = ((HttpServletRequest) request).getSession(true);

                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(true);
                subject.login(token);

//                UserService userService = SpringUtils.getBean(UserService.class);
//                final User loginUser = userService.getUserByAccount(username);
//                Context userContext = new ContextImpl();
//                userContext.setCurrentUser(loginUser);
//                httpSession.setAttribute(Global.USER_SESSION, userContext);
//                ContextHolder.setContext(userContext);
                filterChain.doFilter(request, response);
            } catch (AuthenticationException e) {
                logger.error(e.getMessage());
//                filterChain.doFilter(request, response);
                throw e;
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

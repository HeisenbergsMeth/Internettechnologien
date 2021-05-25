package de.falkmarinov.Internettechnologien.filter;

import de.falkmarinov.Internettechnologien.handler.UserSessionHandler;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "*.xhtml")
public class AuthenticationFilter implements Filter {

    @Inject
    private UserSessionHandler userSessionHandler;

    private final List<String> matchers = new ArrayList<>();

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    @Override
    public void init(FilterConfig filterConfig) {
        matchers.add("/user.xhtml");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        httpServletRequest = (HttpServletRequest) request;
        httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        if (userSessionHandler.isLoggedIn() && (requestURI.contains("/login.xhtml") || (requestURI.contains("/register.xhtml")))) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user.xhtml");
        } else if (needPrivileges(requestURI)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean needPrivileges(String URI) {
        URI = URI.replace(httpServletRequest.getContextPath(), "");
        return matchers.contains(URI) && !userSessionHandler.isLoggedIn();
    }
}

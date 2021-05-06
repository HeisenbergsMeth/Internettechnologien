package de.falkmarinov.Internettechnologien.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*")
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        chain.doFilter(request, response);
    }
}

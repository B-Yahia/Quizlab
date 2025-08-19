package Backend.QuizLab.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionDebugFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String sid = (session == null) ? "no-session" : session.getId();
        chain.doFilter(request, response);
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.printf("DEBUG: %s %s | sid=%s | auth=%s%n",
                req.getMethod(), req.getRequestURI(), sid,
                auth == null ? "null" : auth.getName());
    }
}

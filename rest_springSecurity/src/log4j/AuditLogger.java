package log4j;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditLogger implements javax.servlet.Filter {

    /*
     * create table audit ( logId int PRIMARY KEY auto_increment, timestamp TIMESTAMP default 0,
     * level varchar(255) default null, class varchar(255) default null, user varchar(255) default
     * null, requestURI varchar(255) default null, message text default null );
     */

    static {
        String path = AuditLogger.class.getClassLoader().getResource("").getPath();
        path = path.substring(0, path.indexOf("classes"));
        PropertyConfigurator.configure(path + "log4j.properties");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context != null) {
                Authentication authentication = context.getAuthentication();
                if (authentication != null) {
                    MDC.put("user", authentication.getName());
                    MDC.put("protocol", request.getProtocol());
                    MDC.put("requestURI", ((HttpServletRequest) request).getRequestURI().toString());
                    MDC.put("ip", request.getRemoteAddr());

                    MDC.put("cookie", ((HttpServletRequest) request).getHeader("Cookie"));
                    MDC.put("useragent", ((HttpServletRequest) request).getHeader("User-Agent"));

                }
            }

            Logger.getLogger(AuditLogger.class.getName()).log(Level.INFO, MDC.getContext());

            chain.doFilter(request, response);

        } finally {
            MDC.clear();
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

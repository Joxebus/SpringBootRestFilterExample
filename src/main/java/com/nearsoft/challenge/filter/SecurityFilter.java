package com.nearsoft.challenge.filter;

import com.nearsoft.challenge.entity.UserAuthorization;
import com.nearsoft.challenge.service.UserAuthorizationService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends GenericFilterBean {

    private UserAuthorizationService userAuthorizationService;

    public SecurityFilter(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String username = req.getHeader("user");
        String token = req.getHeader("authorization");
        UserAuthorization userAuthorization =
                userAuthorizationService.findByUsernameAndToken(username, token);
        if (userAuthorization == null) {
            notAuthorized((HttpServletResponse) response, request.getContentType());
            return;
        }
        chain.doFilter(request, response);
    }

    private void notAuthorized(HttpServletResponse response, String contentType) throws IOException {
        HttpServletResponse httpResponse = response;
        httpResponse.setContentType(contentType);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
    }
}

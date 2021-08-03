package com.example.taxservice.configuration;

import com.example.taxservice.entity.Role;
import com.example.taxservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MySimpleUrlAuthenticcationHandler implements AuthenticationSuccessHandler {
    //protected Log logger = LogFactory.getLog(this.getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAtributes(httpServletRequest);
    }

    protected void clearAuthenticationAtributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null){
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {

        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put(Role.USER.getAuthority(), "/user/home");
        roleTargetUrlMap.put(Role.ADMIN.getAuthority(), "/admin/home");

        String currentRole = ((User)authentication.getPrincipal()).getRole().getAuthority();
        if (roleTargetUrlMap.containsKey(currentRole)) {
            return roleTargetUrlMap.get(currentRole);
        }

        throw new IllegalStateException();
    }
}

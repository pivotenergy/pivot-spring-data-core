package com.pivotenergy.domain;

import com.pivotenergy.security.JWTAuthentication;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof JWTAuthentication)) {
            return "_ANONYMOUS_";
        }

        return ((JWTAuthentication)authentication).getUserId();
    }
}

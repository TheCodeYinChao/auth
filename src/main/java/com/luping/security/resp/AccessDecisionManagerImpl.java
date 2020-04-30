package com.luping.security.resp;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * description: AccessDecisionManagerImpl <br>
 * date: 2020/4/30 16:58 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class AccessDecisionManagerImpl extends AbstractAccessDecisionManager {
    public AccessDecisionManagerImpl(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

    }
}

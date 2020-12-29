package com.luping.security.config.vote;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * dsc: GlobalMethodSecurityConfig
 * date: 2020/12/29 13:56
 * author: zyc
 * 扩展原有的鉴权协议
 */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        ExpressionBasedPreInvocationAdvice expressionAdvice = new ExpressionBasedPreInvocationAdvice();
        expressionAdvice.setExpressionHandler(getExpressionHandler());
        decisionVoters
                .add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));
        decisionVoters.add(new RoleVoter());//自带的RoleVoter
        decisionVoters.add(new AuthenticatedVoter());//自带的AuthenticatedVoter

        decisionVoters.add(new AdminVoter());
        return new AffirmativeBased(decisionVoters); //AffirmativeBased一票赞同即通过
    }


    /**
     * 拥有admin权限的角色，直接包含所有权限
     *
     * @author Ryan Miao
     * @date 2019/6/12 20:00
     */
    public class AdminVoter implements AccessDecisionVoter<Object> {

        private static final String ADMIN = "admin";

        @Override
        public boolean supports(ConfigAttribute attribute) {
            return true;
        }

        @Override
        public int vote(Authentication authentication, Object object,
                        Collection<ConfigAttribute> attributes) {
            if (authentication == null) {
                return ACCESS_DENIED;
            }
            int result = ACCESS_ABSTAIN;
            Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

            for (ConfigAttribute attribute : attributes) {
                if (this.supports(attribute)) {
                    result = ACCESS_DENIED;

                    // Attempt to find a matching granted authority
                    for (GrantedAuthority authority : authorities) {
                        if (ADMIN.equals(authority.getAuthority())) {
                            return ACCESS_GRANTED;
                        }
                    }
                }
            }

            return result;
        }

        Collection<? extends GrantedAuthority> extractAuthorities(
                Authentication authentication) {
            return authentication.getAuthorities();
        }

        @Override
        public boolean supports(Class clazz) {
            return true;
        }
    }
}

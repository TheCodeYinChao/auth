package com.luping.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * description: JwtAuthenticatioToken <br>
 * date: 2020/4/29 11:30 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class JwtAuthenticatioToken  extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private String token;

    // ~ Constructors
    // ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     */
    public JwtAuthenticatioToken(Object principal, Object credentials) {
        super(principal,credentials);
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public JwtAuthenticatioToken(Object principal, Object credentials,
                                               Collection<? extends GrantedAuthority> authorities) {
        super( principal, credentials,authorities);
        super.setAuthenticated(true); // must use super, as we override
    }

    public JwtAuthenticatioToken(Object principal, Object credentials, String token) {
        super(principal, credentials);
        this.token = token;
    }

    public JwtAuthenticatioToken(Object principal, Object credentials,
                                 Collection<? extends GrantedAuthority> authorities, String token) {
        super( principal, credentials,authorities);
        this.token = token;
//        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ========================================================================================================


    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.luping.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * description: GrantedAuthorityImpl <br>
 * date: 2020/4/29 11:41 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String role;

    public GrantedAuthorityImpl(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SimpleGrantedAuthority) {
            return role.equals(((SimpleGrantedAuthority) obj).getAuthority());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }
}

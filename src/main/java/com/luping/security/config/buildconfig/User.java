package com.luping.security.config.buildconfig;

/**
 * description: User <br>
 * date: 2020/8/12 17:00 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class User {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

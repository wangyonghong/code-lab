package me.yonghong.demo.container.hashmap;

import java.util.Date;
import java.util.Objects;

/**
 * @author yonghongwang#163.com
 * @since 2021/5/18
 */
public class LoginInfo {

    private String username;

    private Date loginTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        LoginInfo loginInfo = (LoginInfo)o;
        return Objects.equals(username, loginInfo.username);
    }
}

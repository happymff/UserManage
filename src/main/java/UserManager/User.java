package UserManager;
/**
 * Created by mff on 2017/10/31.
 */

import java.util.Date;

public class User {
    private String userId;   //用户ID
    private String userName;  //用户名称
    private String password;  //用户密码
    private String contactTel;  //用户电话
    private String email;   //电子邮件
    private Date createDate;  //创建日期

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactTel() {
        return contactTel == null ? "" : contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}


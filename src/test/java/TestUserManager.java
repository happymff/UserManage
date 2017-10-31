/**
 * Created by mff on 2017/10/31.
 */


import java.util.List;
import java.util.Scanner;

import junit.framework.TestCase;

public class TestUserManager extends TestCase {

    //测试删除操作
    public void testDelUser() {
        Scanner str = new Scanner(System.in);
        System.out.print("请输入要删除的用户Id:");
        String userId = str.next();
        UserManager.getInstance().delUser(userId);
    }

    //测试查询操作
    public void testGetUsers() {
        List<User> users = UserManager.getInstance().getUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.print(user.getUserName());//获取名字
        }
    }

    //测试用户添加
    public void testAddUser() {
        User user = new User();
        Scanner str = new Scanner(System.in);
        System.out.print("请输入用户Id:");
        String id = str.next();
        System.out.print("请输入用户name:");
        String name = str.next();
        System.out.print("请输入用户password:");
        String password = str.next();
        System.out.print("请输入用户tel:");
        String tel = str.next();
        System.out.print("请输入用户email:");
        String email = str.next();
        user.setUserId(id);
        user.setUserName(name);
        user.setPassword(password);
        user.setContactTel(tel);
        user.setEmail(email);
        UserManager.getInstance().addUser(user);
    }

    //根据用户Id查询
    public void testFindUserById() {
        Scanner str = new Scanner(System.in);
        System.out.print("请输入用户Id:");
        String userId = str.next();
        UserManager.getInstance().findUserById(userId);
        User user = new User();
        System.out.print("用户的名字:" + user.getUserName());
    }
}
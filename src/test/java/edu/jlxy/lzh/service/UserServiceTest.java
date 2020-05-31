package edu.jlxy.lzh.service;

import edu.jlxy.lzh.pojo.User;
import edu.jlxy.lzh.service.Impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"was168","981128",null));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "was168", "admin", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("薛韵")){
            System.out.println("用户名不可用");
        }else{
            System.out.println("用户名可用");
        }
    }
}

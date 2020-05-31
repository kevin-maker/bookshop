package edu.jlxy.lzh.mapper;

import edu.jlxy.lzh.mapper.Impl.UserMapperImpl;
import edu.jlxy.lzh.pojo.User;
import org.junit.Test;

public class UserMapperTest {
    private UserMapper userMapper = new UserMapperImpl();
    @Test
    public void queryUserByUserName() {
        System.out.println(userMapper.queryUserByUserName("薛韵"));
    }
    @Test
    public void saveUsers() {
        System.out.println(userMapper.saveUsers(new User(null,"薛韵","981108",null)));
    }
    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userMapper.queryUserByUsernameAndUserPassword("薛韵","981108"));
    }
}

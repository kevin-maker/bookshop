package edu.jlxy.lzh.service.Impl;

import edu.jlxy.lzh.mapper.Impl.UserMapperImpl;
import edu.jlxy.lzh.mapper.UserMapper;
import edu.jlxy.lzh.pojo.User;
import edu.jlxy.lzh.service.UserService;

public class UserServiceImpl implements UserService {
    private UserMapper userMapper = new UserMapperImpl();
    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void registUser(User user) {
        userMapper.saveUsers(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return 返回null表示登陆失败，有信息表示成功
     */
    @Override
    public User login(User user) {
        return userMapper.queryUserByUsernameAndUserPassword(user.getUsername(),user.getPassword());
    }

    /**
     * 检查用户名是否可用
     *
     * @param username
     * @return 返回true表示用户已存在，返回false表示用户名可用
     */
    @Override
    public boolean existsUsername(String username) {
        if (userMapper.queryUserByUserName(username) == null){
            return false;
        }else{
            return true;
        }
    }
}

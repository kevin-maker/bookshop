package edu.jlxy.lzh.service;

import edu.jlxy.lzh.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null表示登陆失败，有信息表示成功
     */
    User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户已存在，返回false表示用户名可用
     */
    boolean existsUsername(String username);
}

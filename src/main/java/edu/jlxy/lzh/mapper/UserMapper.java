package edu.jlxy.lzh.mapper;

import edu.jlxy.lzh.pojo.User;

public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户
     */
    User queryUserByUserName(String username);

    /**
     * 保存用户信息
     * @param user
     * @return 返回受影响的行数，-1表示失败
     */
    int saveUsers(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，说明用户名或面膜错误
     */
    User queryUserByUsernameAndUserPassword(String username,String password);
}

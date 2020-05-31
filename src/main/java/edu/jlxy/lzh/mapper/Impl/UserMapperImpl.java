package edu.jlxy.lzh.mapper.Impl;

import edu.jlxy.lzh.mapper.BaseMapper;
import edu.jlxy.lzh.mapper.UserMapper;
import edu.jlxy.lzh.pojo.User;

public class UserMapperImpl extends BaseMapper implements UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户
     */
    @Override
    public User queryUserByUserName(String username) {
        String sql = "select * from t_user where username = ?";
        return queryOne(User.class,sql,username);
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @return 返回受影响的行数，-1表示失败
     */
    @Override
    public int saveUsers(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，说明用户名或面膜错误
     */
    @Override
    public User queryUserByUsernameAndUserPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryOne(User.class,sql,username,password);
    }
}

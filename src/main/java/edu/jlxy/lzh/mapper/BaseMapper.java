package edu.jlxy.lzh.mapper;

import edu.jlxy.lzh.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseMapper {
    //使用dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();
    /**
     * 执行增删改
     * @param sql
     * @param params
     * @return 返回受影响的行数，-1表示失败
     */
    public int update(String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个javabean
     * @param cls 返回对象类型
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> T queryOne(Class<T> cls,String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(cls),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回多个javabean
     * @param cls
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T>List<T> queryList(Class<T> cls,String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(cls),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 返回特殊类型
     * @param sql
     * @param params
     * @return
     */
    @SuppressWarnings(value = {"unchecked","rawtypes"})
    public Object querySingleValue(String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}

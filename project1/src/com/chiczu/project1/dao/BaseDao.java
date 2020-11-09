package com.chiczu.project1.dao;

import com.chiczu.project1.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-10-21 15:46
 */
public abstract class BaseDao {

    //使用DBUtils操作資料庫,先導入commons-dbutils-1.3.jar
    private QueryRunner runner = new QueryRunner();

    /**
     * update():執行 insert\ update \ delete 語句
     * @param sql
     * @param args
     * @return 返回受影響的行數;如果返回-1,表示執行失敗
     */
    public int update(String sql,Object...args){

        System.out.println("BaseDao當前執行緒名稱: "+Thread.currentThread().getName());

        Connection conn = JDBCUtils.getConnection();
        try {
            int updateCount = runner.update(conn, sql, args);
            return updateCount;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查詢sql語句,返回一個javabean的物件
     * @param type 返回物件類型
     * @param sql  執行的sql語句
     * @param args sql內佔位符對應的參數值
     * @param <T>  返回類型的泛型
     * @return
     */
    public <T>T queryForOne(Class<T> type,String sql,Object...args){
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<T> handler = new BeanHandler<>(type);
        T t = null;
        try {
            t = runner.query(conn,sql,handler,args);
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 查詢sql語句,返回封裝多個javabean的物件的集合
     * @param type 返回物件類型
     * @param sql  執行的sql語句
     * @param args sql內佔位符對應的參數值
     * @param <T>  返回類型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<T> handler = new BeanListHandler<>(type);
        List<T> list = null;
        try {
            list = runner.query(conn, sql, handler, args);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 執行返回一行一列的sql語句,
     * @param sql   執行的sql語句
     * @param args  sql內佔位符對應的參數值
     * @return
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JDBCUtils.getConnection();
        ScalarHandler handler = new ScalarHandler();
        Object ans = null;
        try {
            ans = runner.query(conn,sql, handler,args);
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}

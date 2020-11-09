package com.chiczu.project1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.mysql.jdbc.Statement;

/**
 * 建立DBCP資料庫連接池
 * 
 * @author Phil
 *
 */
public class JDBCUtils {

	//獲取資料庫連接池
	private static DataSource source;
	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<>();
	static {
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
		Properties pros= new Properties();
		try {
			pros.load(is);
			source = BasicDataSourceFactory.createDataSource(pros);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection conn=connThreadLocal.get();
		if(conn == null) {	
			try {
					conn = source.getConnection();
					System.out.println("555"+conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
		return conn;
	}
	
	 public static void closeResource(Connection conn, Statement ps){
	        if(ps != null){
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(conn != null){
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 public static void closeResource(Connection conn, Statement ps, ResultSet rs){
	        if(ps != null){
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(conn != null){
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs!=null){
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	 /**
	     * 提交事務,並關閉連接,放回資料庫連接池
	     */
	    public static void commitAndClose(){
	        Connection conn = connThreadLocal.get();
	        //如果null不等於null,表示之前使用過連接,操作過資料庫
	        if(conn != null){
	            try {
	                conn.commit();//提交事務
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }finally{
	                try {
	                    conn.close();//關閉連接,釋放資源
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        //一定要執行remove操作,否則會出錯。因tomcat底層使用了執行緒池技術
	        connThreadLocal.remove();
	    }

	    /**
	     * 回滾事務,並關閉連接,放回資料庫連接池
	     */
	    public static void rollbackAndClose(){
	        Connection conn = connThreadLocal.get();
	        //如果null不等於null,表示之前使用過連接,操作過資料庫
	        if(conn != null){

	            try {
	                conn.rollback();//回滾事務
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }finally{
	                try {
	                    conn.close();//關閉連接,釋放資源
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        //一定要執行remove操作,否則會出錯。因tomcat底層使用了執行緒池技術
	        connThreadLocal.remove();
	    }

}

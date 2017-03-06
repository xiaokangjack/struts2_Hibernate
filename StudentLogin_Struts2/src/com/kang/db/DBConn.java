package com.kang.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConn {
	//得到数据库连接
	public static Connection createDBConn(){
		try{
			 Class.forName("com.mysql.jdbc.Driver");//通过静态方法直接调用forName方法加载数据库驱动  
	            String url=new String("jdbc:mysql://192.168.1.148:3306/RegisterSystem");//需要连接的数据库端口  
	            String userString=new String("root");//定义登录数据库的用户名  
	            String psw=new String("root");//定义登录数据库的密码  
	            Connection conn=DriverManager.getConnection(url, userString, psw);//连接数据库  
	            return conn;	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//关闭数据库连接
	public static void closeConn(Connection conn){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

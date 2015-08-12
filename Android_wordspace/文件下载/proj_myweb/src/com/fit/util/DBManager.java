package com.fit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	public static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String URL="jdbc:sqlserver://localhost:1433;dataBaseName=userInfo";
	public static final String DBUSER="sa";
	public static final String DBPWD="123456";
	
	public Connection conn=null;
	public PreparedStatement ptmt=null;
	public ResultSet rs=null;
	
	//建立连接
		public void setConnection(){
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,DBUSER,DBPWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		//创建对象
		
		public void prepareStatement(String sql){
			setConnection();
			try {
				ptmt=conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//增删改
		public void excuteUpdate(String sql,Object...objects) throws SQLException{
			prepareStatement(sql);
			System.out.println(sql);
			if(objects!=null){
				for(int i=0;i<objects.length;i++){
					ptmt.setObject(i+1,objects[i]);
				}
			}
			ptmt.executeUpdate();
		}
		
		//查询
		public ResultSet excuteQuery(String sql,Object ...objects) throws SQLException{
			System.out.println(sql);
			prepareStatement(sql);
			if(objects!=null){
				for(int i=0;i<objects.length;i++){
					ptmt.setObject(i+1, objects[i]);
				}
			}
			this.rs=ptmt.executeQuery();
			return this.rs;
		}

		//释放资源
		public void resource(Connection conn,	PreparedStatement ptmt,ResultSet rs){
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs=null;
			}
			if(ptmt!=null){
				try {
					ptmt.cancel();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs=null;
			}
			if(conn!=null){
				try {
					ptmt.cancel();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn=null;
			}
		}
}


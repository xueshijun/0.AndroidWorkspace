package com.fit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fit.entity.Task;
import com.fit.util.DBManager;

public class UserDAO {
	DBManager db=new DBManager();
	
	public boolean checkUserInfo(String userName,String userPass){
		String sql="select * from userInfo where userName='"+userName+"' and userPass='"+userPass+"' ";
		try {
			db.rs=db.excuteQuery(sql);
			if(db.rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int queryId(String userName,String userPass){
		int userId=0;
		String sql="select userId from userInfo where userName='"+userName+"' and userPass='"+userPass+"' ";
		try {
			db.rs=db.excuteQuery(sql);
			
				if(db.rs.next()){
					userId=db.rs.getInt("userId");
					System.out.println(userId);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public List<Task> queryTask(int userId){
		List<Task> taskList=new ArrayList<Task>(); 
		
		Task task=null;
		String sql="select goodsId,goodsName,goodsAddress,goodsStatus from task where userId='"+userId+"' and  goodsStatus=0";
		try {
			db.rs=db.excuteQuery(sql);
			while(db.rs.next()){
				task=new Task();
				task.setGoodsId(db.rs.getInt("goodsId"));
				task.setGoodsName(db.rs.getString("goodsName"));
				task.setGoodsAddress(db.rs.getString("goodsAddress"));
				task.setGoodsStatus(db.rs.getInt("goodsStatus"));
				taskList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taskList;
	}
	
	public boolean changeStatus(String id){
		String sql="update task set goodsStatus=1 where goodsId="+id+"";
		try {
			db.excuteUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

package com.fit.biz;

import java.util.List;

import com.fit.dao.UserDAO;
import com.fit.entity.Task;

public class UserInfoBIZ {

	UserDAO dao=new UserDAO();
	public boolean checkUser(String userName,String userPass){
		return dao.checkUserInfo(userName, userPass);
	}
	
	public int queryId(String userName,String userPass){
		return dao.queryId(userName, userPass);
	}
	
	public List<Task> queryTask(int userId){
		return dao.queryTask(userId);
	}
	public boolean changeStatus(String id){
		return dao.changeStatus(id);
	}
}

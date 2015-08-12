package com.fit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fit.biz.UserInfoBIZ;
import com.fit.entity.Task;

/**
 * Servlet implementation class Dologin
 */
public class Dologin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserInfoBIZ biz=new UserInfoBIZ();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dologin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		boolean flag=biz.checkUser(userName, userPass);
		//ObjectOutputStream oot = new ObjectOutputStream(response.getOutputStream());
		
		if(flag){
			//out.print("OK");
			int userId=biz.queryId(userName, userPass);
		
				List<Task> taskList=biz.queryTask(userId);
				if(taskList.size()>0){
					for(int i=0;i<taskList.size();i++){
						out.println(taskList.get(i).getGoodsId());
						out.println(taskList.get(i).getGoodsName());
						out.println(taskList.get(i).getGoodsAddress());
						out.println(taskList.get(i).getGoodsStatus());
						System.out.println(taskList.get(i).getGoodsId()+"-------------"+taskList.get(i).getGoodsName()+"--"+taskList.get(i).getGoodsAddress()+taskList.get(i).getGoodsStatus());
					}
				}else{
					out.print("Nothing");
				}
				//oot.writeObject(taskList);
				
		
		}else{
			out.print("ERROR");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

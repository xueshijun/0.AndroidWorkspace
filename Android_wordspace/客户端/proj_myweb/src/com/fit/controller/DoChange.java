package com.fit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fit.biz.UserInfoBIZ;

/**
 * Servlet implementation class DoChange
 */
public class DoChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  UserInfoBIZ biz=new UserInfoBIZ();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String goodsId=request.getParameter("goodsId");
			PrintWriter out=response.getWriter();
			boolean flag1=biz.changeStatus(goodsId);
			if(flag1){
				out.print("success");
			}else{
				out.print("failure");
			}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}

package com.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public insert() {
        super();
      }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		String sql="";
		response.setContentType("text/html");
	//	RequestDispatcher rd=request.getRequestDispatcher("CRUD");
		try{
	
			con=ConnectionManager.getConnection();
			String name=request.getParameter("name");
			String sal=request.getParameter("sal");
			int sal1=Integer.parseInt(sal);
			if((name!=null)&&(sal!=null))
			{
		      sql="insert into EmployeeDetails (name, sal) values(?,?);";
			  ps=con.prepareStatement(sql);
			  ps.setString(1, name);
			  ps.setInt(2, sal1);
			  int x=0;
			  x=ps.executeUpdate();
			  if(x==1)
			  {
				  pw.write("Data inserted");
				 
			  }
			  response.sendRedirect("index.jsp"); 
			}
	}
		catch(Exception e)
		{
			System.out.println(e);
			
		}

}
}

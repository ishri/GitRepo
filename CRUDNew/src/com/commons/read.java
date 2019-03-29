package com.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class read extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public read() {
        super();
       }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection con=null;
		PreparedStatement ps=null;
		String sql=null;
		PrintWriter pw=response.getWriter();
		try{
			con=ConnectionManager.getConnection();
			sql="SELECT  * FROM  EmployeeDetails;";
			ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int eid=rs.getInt(1);
				String name=rs.getString(2);
				int sal=rs.getInt(3);
				/*System.out.println(eid);
				System.out.println(name);
				System.out.println(sal);*/
				pw.print("EID:"+ eid+" ");
				pw.print("Name:"+ name+" ");
				pw.println("Sal:"+ sal+" ");
			/*	request.setAttribute("rs", rs);
				request.setAttribute("eid", eid);
				request.setAttribute("ename", name);
				request.setAttribute("sal", sal);*/
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	//	request.getRequestDispatcher("read.jsp").forward(request, response);
		//response.sendRedirect("read.jsp");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

}

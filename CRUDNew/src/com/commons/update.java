package com.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public update() {
        super();
          }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		PreparedStatement ps=null;
		String sql="";
		ResultSet rs;
		PrintWriter pw=response.getWriter();
		String id=request.getParameter("eid");
		int id1=Integer.parseInt(id);
		String sal=request.getParameter("sal");
		int sal1=Integer.parseInt(sal);
		try{
			con=ConnectionManager.getConnection();
			sql="UPDATE EmployeeDetails SET sal=? WHERE eid=?;";
			ps=con.prepareStatement(sql);
			ps.setInt(1, sal1);
			ps.setInt(2, id1 );
			int x = 0;
			x = ps.executeUpdate();
			if(x==1){
				sql="SELECT * from EmployeeDetails;";
				ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int eid=rs.getInt(1);
				String name1 = rs.getString(2);
				int salary=rs.getInt(3);
				pw.print("EID: " + eid +"\t");
				pw.print("Name: " + name1+"\t");
				pw.print("Salary: " + salary+"\n");
			}
			
			
			}
		//	pw.println("<a href='index.jsp'>Home</a>");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	/*	//response.sendRedirect("update");
		RequestDispatcher rd=request.getRequestDispatcher("/update.jsp");
		rd.forward(request, response);
*/	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}


}

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

public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public delete() {
        super();
           }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("eid");
		Integer eid=Integer.parseInt(id);
		String sql="";
		PrintWriter pw= response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs;
		
		try{
			con=ConnectionManager.getConnection();
			sql="DELETE FROM Employeedetails where eid=?;";
			ps=con.prepareStatement(sql);
			ps.setInt(1, eid);
			int x = 0;
			if(id.equals(eid))
			{
			x = ps.executeUpdate();
			if(x==1){
				sql="SELECT * from EmployeeDetails;";
				ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int eid1=rs.getInt(1);
				String name1 = rs.getString(2);
				int salary=rs.getInt(3);
				pw.print("EID: " + eid +"\t");
				pw.print("Name: " + name1+"\t");
				pw.print("Salary: " + salary+"\n");
			}
			
			
			}
			}
			else
			{
				pw.println("Invalid EID");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}


}

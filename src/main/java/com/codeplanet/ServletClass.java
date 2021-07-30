package com.codeplanet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletClass extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String val=req.getParameter("cpsw");
		String val1=req.getParameter("email");
		String val2=req.getParameter("psw");
		System.out.print(val1 + "  "+ val2);
		req.setAttribute("email", val1);
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application", "root", "ankitdhali");
			PreparedStatement pst = con.prepareStatement("Select * from user where email =?");
			pst.setString(1,val1);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) 
			{
				System.out.println(rs.getString("password"));	
//				if(rs.getString("password").equals(val2)) 
//				{
//					System.out.println("Login Sucessfull");
//				}
//				else 
//				{
//					System.out.println("Password is not correct");
//				}
//			}
//			else 
//			{
//				Statement st = con.createStatement();
//				String query = "insert into user values('"+val1+"','"+val2+ "')";
//				int x = st.executeUpdate(query);
//			   if(x== 1) 
//			   {
//				   System.out.println("Sucessfully signedup");
//			   }
//			   else 
//			   {
//				   	System.out.println("some error occured");
//			   }
			}
			CallableStatement cst=con.prepareCall("{call test(?,?)}");
			cst.setString(1, val1);
			cst.setString(2, val2);
			int x=cst.executeUpdate();
			System.out.println(x);
		
//			Statement st = con.createStatement();
//			String query1="select*from user where email='" + val1 +"'";
//			ResultSet rs =st.executeQuery(query1);
//			if(rs.next())
//			{
//				if(rs.getString("password").equals(val1))
//					System.out.println("You are already signed up");
//			}
//			else
//			{
//				String query ="insert into user values('"+val1+"','"+val2+"')";
//				int x = st.executeUpdate(query);
//				System.out.println(x);
//				if(x==1)
//					System.out.println("You are sucessfully signedup");
//				else
//					System.out.println("There is some error in it");
//			}
//			while(rs.next())
//			{
//				System.out.println("your email is"+rs.getString("email"));
//				System.out.println("your password is"+rs.getShort(2));
//			}
//			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				con.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		RequestDispatcher dispatcher=req.getRequestDispatcher("first.jsp");
//		dispatcher.forward(req,res);
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String val=req.getParameter("cpsw");
		String val1=req.getParameter("email");
		String val2=req.getParameter("psw");
		System.out.print(val1 + "  "+ val2);
		req.setAttribute("email", val1);
		RequestDispatcher dispatcher=req.getRequestDispatcher("first.jsp");
		dispatcher.forward(req, res);
	}

}

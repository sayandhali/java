package com.codeplanet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatepasswordusingotp")
public class UpdatePasswordUsingOtp extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String email=req.getParameter("email");
		String otp=req.getParameter("Otp");
		String psw=req.getParameter("psw");
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application", "root", "ankitdhali");
			PreparedStatement pst = con.prepareStatement("Select otp from user where email =?");
			pst.setString(1,email);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) 
			{	
				if(rs.getString("otp").equals(otp)) 
				{
					PreparedStatement pst1 = con.prepareStatement("update user set password =? where email =?");
					pst1.setString(1,psw);
					pst1.setString(2,email);
					pst1.executeUpdate();
					System.out.println(email+"'s password updated sucessfully");
				}
				else 
				{	
					RequestDispatcher dispatcher=req.getRequestDispatcher("updatepassword.jsp");
					dispatcher.forward(req,res);
					System.out.println("Your otp is invalid");
				}
			}
			else 
			{
				System.out.println("Something is Wrong");
			}
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
	}
}

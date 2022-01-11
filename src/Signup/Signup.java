package Signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
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


/**
 * Servlet implementation class SignUp
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Signup() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String uname=request.getParameter("email");
		String pswd=request.getParameter("password");
		String pswd_confirm=request.getParameter("password-confirm");
		try {
		// load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loaded");
		//Connection establishment
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ep","ep1");
			String sql="insert into register_value values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, uname);
			pstmt.setString(3, pswd);
			pstmt.setString(4, pswd_confirm);
			System.out.println("Values inserted");
		int count = pstmt.executeUpdate();
		if(count > 0) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		}
		System.out.println("Successful!!");
		}
		catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

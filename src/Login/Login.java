package Login;

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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
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
		String uname=request.getParameter("uname");
		String pswd=request.getParameter("pswd");
		try {
		// load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loaded");
		//Connection establishment
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ep","ep1");
			String sql= "select * from register_value where uname = ? and pswd = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			pstmt.setString(2, pswd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("success.html");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("failure.html");
				rd.forward(request, response);
			}

		}
		catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

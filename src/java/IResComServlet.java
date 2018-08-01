/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DHRUV
 */
public class IResComServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    HttpSession session=request.getSession();
                String username=(String)session.getAttribute("n");
    PrintWriter out = response.getWriter();
     RequestDispatcher rd=request.getRequestDispatcher("isolvecomplaint.html");
                        rd.include(request,response);
    
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");
      stmt = conn.createStatement();
      PreparedStatement ps1=conn.prepareStatement("select * from resolved where usr=?");
      ps1.setString(1,username);
      ResultSet rs = ps1.executeQuery();
      out.println("<html>");
    
    out.println("<body>");
    out.println("<center><h1>All Resolved Complaints</h1>");
    out.println("<P ALIGN='center'><TABLE border=1 width=400 height=150 align=center>");
     out.println("<TR>");
     out.println("<TH style='text-align:center'>Complaint ID</TH>");
     out.println("<TH style='text-align:center'>Complaint</TH>");
      out.println("<TH style='text-align:center'>Student ID</TH>");
      out.println("</TR>"); 
      while (rs.next()) {  
        String msg = rs.getString("msg");
        String msgid=rs.getString("msgid");
        String usr=rs.getString("usr");
        String sid=rs.getString("sid");
         out.println("<TR>");
         out.print("<TD style='color:#B22222'>" +msgid + "</TD>");
        out.print("<TD style='color:#B22222'>" +msg + "</TD>");
      out.print("<TD style='color:#B22222'>" +sid+ "</TD>");
        out.println("</TR>");
      }
      out.println("</TABLE></P>");
      out.println("</center>");
    out.println("</body>");
    out.println("</html>");
    } catch (SQLException e) {
      out.println("An error occured while retrieving " + "all complaints: " 
          + e.toString());
    } catch (ClassNotFoundException e) {
      throw (new ServletException(e.toString()));
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
      }
    }
    
    out.close();
  }
}

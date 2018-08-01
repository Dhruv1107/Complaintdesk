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
public class ResolveComplaint extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    
    PrintWriter out = response.getWriter();
     RequestDispatcher rd=request.getRequestDispatcher("isolvecomplaint.html");
                        rd.include(request,response);
    String msgi=request.getParameter("msgid");
    int msgid=Integer.parseInt(msgi);
    String msg=request.getParameter("msg");
    String usr=request.getParameter("usr");
    String sid=request.getParameter("sid");
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");
      stmt = conn.createStatement();
      PreparedStatement ps1=conn.prepareStatement("insert into resolved values(?,?,?,?)");
      ps1.setInt(1,msgid);
      ps1.setString(2,msg);
      ps1.setString(3,usr);
      ps1.setString(4,sid);
      int i = ps1.executeUpdate();
       PreparedStatement ps2=conn.prepareStatement("delete from inchargemsg where msgid=?");
       ps2.setInt(1,msgid);
         int j = ps2.executeUpdate();
                        if(i>0 && j>0)
                        {
                            out.print("<div align='center'><span style='color:#00ff00'>Complaint Resolved</span></div>");
                        }
                   
                        else
                        {
                            out.print("<div align='center'><span style='color:#ff0000'>Oops Complaint not resolved</span></div>");
                        }
    
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

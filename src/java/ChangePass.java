import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ChangePass extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
                HttpSession session=request.getSession();
                String username=(String)session.getAttribute("n");
                String password=(String)session.getAttribute("p");
                String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
                String p3=request.getParameter("p3");
                if (p1.length()!=0 && p2.length()!=0 && p3.length()!=0)
                {
                    if(p2.equals(p3))
                    {
                        if(password.equals(p1))
                        {
                            try
                            {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");
                             PreparedStatement ps=con.prepareStatement("update comdesk set pwd= ? where usr=?");
                             ps.setString(1,p2);
                             ps.setString(2,username);
                               int i= ps.executeUpdate();
                               if(i>0)
                                {
                                RequestDispatcher rd=request.getRequestDispatcher("settings.html");  
                                rd.include(request,response); 
                                out.print("<div align='center'><span style='color:#00ff00'>Your password Changed Successfully</span></div>");
                                }
                               else
                               {
                                  RequestDispatcher rd=request.getRequestDispatcher("settings.html");  
                                rd.include(request,response); 
                                out.print("<div align='center'><span style='color:#ff0000'>Oops error encountered</span></div>"); 
                               }
                            }
                            catch (Exception e2) {System.out.println(e2);}
                            
                        }
                        else
                        {
                             RequestDispatcher rd=request.getRequestDispatcher("settings.html");  
                                rd.include(request,response); 
                                out.print("<div align='center'><span style='color:#ff0000'>Your password is not correct</span></div>"); 
                        }
                    }
                    else
                    {
                        RequestDispatcher rd=request.getRequestDispatcher("settings.html");  
                        rd.include(request,response); 
                        out.print("<div align='center'><span style='color:#ff0000'>Passwords do not match</span></div>");
                    }
                }
                else
                {
                    RequestDispatcher rd=request.getRequestDispatcher("settings.html");  
                    rd.include(request,response); 
                    out.print("<div align='center'><span style='color:#ff0000'>Fields should not be empty</span></div>"); 
                }
                out.close();
        }
}
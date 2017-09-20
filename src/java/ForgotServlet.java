import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.*;
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
public class ForgotServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    String u=request.getParameter("username");
    String q=request.getParameter("sques");
    String a=request.getParameter("ans");
    String user="",pass="",sques="",ans="";
    if(u.length()!=0 && !q.equals("Please select your selected security question") && a.length()!=0 )
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");
        PreparedStatement ps=con.prepareStatement("select * from comdesk where usr=?"); 
        ps.setString(1,u); 
        ResultSet rs=ps.executeQuery();  
                        while(rs.next())
                        {
                            user=rs.getString(1);
                           pass=rs.getString(2);
                           sques=rs.getString(3);
                           ans=rs.getString(4);
                        }
                        if(u.equals(user) && q.equals(sques) && a.equals(ans))
                        {
                            HttpSession session=request.getSession();
                            session.setAttribute("u",u); 
                          RequestDispatcher rd=request.getRequestDispatcher("setnewpass.html");  
                           rd.forward(request,response);
                        }
                        else
                        {
                            RequestDispatcher rd=request.getRequestDispatcher("forgot.html");
                            rd.include(request,response);  
                            out.println("<div align='center'><span style='color:#ff0000'>Invalid Answer</span></div>");
                        }
           
        }
        catch (Exception e2) {System.out.println(e2);}
        
    }
    else
    {
        RequestDispatcher rd=request.getRequestDispatcher("forgot.html");
        rd.include(request,response);  
        out.println("<div align='center'><span style='color:#ff0000'>Fields should not be empty</span></div>");  
    }
        
}
}
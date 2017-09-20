import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
                String usr=request.getParameter("username");
		String p1=request.getParameter("userpass1");
		String p2=request.getParameter("userpass2");
		String m=request.getParameter("sques");
		String p=request.getParameter("ans");
                
               if(n.length()!=0 && p1.length()!=0 && p2.length()!=0 && m.length()!=0 && p.length()!=0)
               {
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");
                if(p1.equals(p2))
                {
                 PreparedStatement available = con.prepareStatement("select usr from comdesk where usr=?");
                 available.setString(1,usr);
                 ResultSet rs = available.executeQuery();
                   if (!rs.next()) //if user name is not occupied by anyone(user name is available)
                   {
                        PreparedStatement ps=con.prepareStatement("insert into comdesk values(?,?,?,?)");
                        ps.setString(1,n);
                        ps.setString(2,p1);
                        ps.setString(3,m);
                        ps.setString(4,p);
                        int i=ps.executeUpdate();
                        if(i>0)
                        {
                            RequestDispatcher rd=request.getRequestDispatcher("register.html");  
                            rd.include(request,response); 
                            out.print("<div align='center'><span style='color:#00ff00'>You are successfully registered</span></div>");
                        }
                   }
                   else//if User Name already exists then display an error
                   {
                            RequestDispatcher rd=request.getRequestDispatcher("register.html");  
                            rd.include(request,response); 
                            out.print("<div align='center'><span style='color:#ff0000'>This User Name already exists</span></div>");
                   }
                 }
                 else if(!p1.equals(p2))
                 {
                    RequestDispatcher rd=request.getRequestDispatcher("register.html");  
                    rd.include(request,response); 
                    out.print("<div align='center'><span style='color:#ff0000'>Passwords do not match</span></div>");
                 }
			
		}catch (Exception e2) {System.out.println(e2);}
               }
               else//If the fields are empty
               {
                   RequestDispatcher rd=request.getRequestDispatcher("register.html");  
                    rd.include(request,response); 
                    out.print("<div align='center'><span style='color:#ff0000'>Fields should not be empty</span></div>");  
               }
               
		out.close();
	}

}

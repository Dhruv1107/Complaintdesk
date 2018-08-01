
import java.io.*;
import java.sql.*;
import javax.servlet.*; 
import javax.servlet.http.*;


public class StoreComServlet extends HttpServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
       response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    String t=request.getParameter("type");
    String d=request.getParameter("desc");
    String de="Select your option";
    HttpSession session=request.getSession();
                String username=(String)session.getAttribute("n");
    String user=null;
    int j=0;
    if(!t.equals(de))
    {
            try
               {
               Class.forName("com.mysql.jdbc.Driver");
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root","");

                 PreparedStatement ps1=con.prepareStatement("select * from incharge where type=?");
                 ps1.setString(1,t);
                 ResultSet rs=ps1.executeQuery();
                 
                       while(rs.next())
                       {
                           
                           user=rs.getString(1);
                           
                       }
                       
                        PreparedStatement ps=con.prepareStatement("insert into inchargemsg(msg,usr,sid) values(?,?,?)");
                        ps.setString(1,d);
                        ps.setString(2,user);
                        ps.setString(3,username);
                        j=ps.executeUpdate();   
                       
                    if(j>0)
                    {
                        RequestDispatcher rd=request.getRequestDispatcher("complaint.html");
                        rd.include(request,response);
                        out.print("<div align='center'><span style='color:#00ff00'>Complaint successfully registered</span></div>");
                    }
                    else
                    {
                        RequestDispatcher rd=request.getRequestDispatcher("complaint.html");
                        rd.include(request,response);
                        out.print("<div align='center'><span style='color:#ff0000'>Complaint not registered</span></div>"); 
                    }
               }
              catch (Exception e2) {System.out.println(e2);}
   
   }
    else if(t.equals("Select your option"))
        {
         RequestDispatcher rd=request.getRequestDispatcher("complaint.html");  
                            rd.include(request,response); 
                            out.print("<div align='center'><span style='color:#00ff00'>Please enter type of complaint</span></div>");    
   }
//    else if(d.length()<20)
//    {
//       RequestDispatcher rd=request.getRequestDispatcher("complaint.html");  
//                            rd.include(request,response); 
//                            out.print("<div align='center'><span style='color:#00ff00'>The complaint should be more than 20 characters</span></div>");  
//    }

   out.close();
   }
}

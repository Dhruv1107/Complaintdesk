import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
public class FirstServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    String p=request.getParameter("userpass");  
    String s=request.getParameter("select"); 
    String s1="Student";
    
      
    if(s.equals(s1))
    {
        if(n.length()!=0 && p.length()!=0)  
        {
            if(LoginDao.validate(n, p))
            {  
                 HttpSession session=request.getSession();  
                   session.setAttribute("n",n);  
                   session.setAttribute("p",p); 
            RequestDispatcher rd=request.getRequestDispatcher("homepage.html"); 
            rd.forward(request,response);  
            }  
            else
            {  
            RequestDispatcher rd=request.getRequestDispatcher("index.html");  
            rd.include(request,response); 
            out.println("<div align='center'><span style='color:#ff0000'>Invalid Username/Password</span></div>");  
            } 
        }
        else
        {
        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
        rd.include(request,response); 
        out.println("<div align='center'><span style='color:#ff0000'>Username/Password should not be empty</span></div>");  
        }
    }
    else
    {
     if(n.length()!=0 && p.length()!=0)  
        {
            if(LoginInc.validate(n, p))
            {  
                 HttpSession session=request.getSession();  
                   session.setAttribute("n",n);  
                   session.setAttribute("p",p); 
            RequestDispatcher rd=request.getRequestDispatcher("incharges.html"); 
            rd.forward(request,response);  
            }  
            else
            {  
            RequestDispatcher rd=request.getRequestDispatcher("index.html");  
            rd.include(request,response); 
            out.println("<div align='center'><span style='color:#ff0000'>Invalid Username/Password</span></div>");  
            } 
        }
        else
        {
        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
        rd.include(request,response); 
        out.println("<div align='center'><span style='color:#ff0000'>Username/Password should not be empty</span></div>");  
        }
    }  
    out.close();  
    }  
}  
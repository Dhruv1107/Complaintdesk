import java.sql.*;  
  
public class LoginInc {  
public static boolean validate(String usr,String pwd){  
boolean status=false;  
try{  
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cd?zeroDateTimeBehavior=convertToNull","root",""); 
      
PreparedStatement ps=con.prepareStatement(  
"select usr,pwd from incharge where usr=? and pwd=?");  
ps.setString(1,usr);  
ps.setString(2,pwd);  
      
ResultSet rs=ps.executeQuery();  
status=rs.next();  
          
}catch(Exception e){System.out.println(e);}  
return status;  
}  
}  
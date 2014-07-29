package campaignui.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.util.Version;

import campaignui.util.CommonUtil;

/**
 * Servlet implementation class GetDetails
 */
@WebServlet("/GetSegDetails")
public class GetSegDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetSegDetails() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  Enumeration<String> requestParameters = request.getParameterNames();
	  String colName= requestParameters.nextElement();
	  if(colName.length()>0)
	  {
	     getSegDetails( colName, request.getParameter(colName),response); 
	  }
	}

	private void getSegDetails(String colName, String val, HttpServletResponse response) {
    // TODO Auto-generated method stub
	  try {
      PrintWriter out = response.getWriter();
    
	  Connection con = CommonUtil.GetConnection();
    Statement st = null;
    ResultSet rs = null;
    String data;
    try {
      st = con.createStatement();
      rs = st.executeQuery("select * from SegDefData where " + colName +" like '%"
          + val + "%'");
     
      ResultSetMetaData meta = rs.getMetaData();
      int colCount = meta.getColumnCount();
      
      out.print("<table border=1>");
      out.print("<tr bgcolor=\"yellow\">");
      for (int col=1; col <= colCount; col++)
        out.println("<td>"+meta.getColumnName(col) + "</td>");
      out.print("</tr>");
      
      while(rs.next()) {
        out.print("<tr>");
        for (int col=1; col <= colCount; col++) 
        {
            Object value = rs.getObject(col);
            if(value != null)
            out.print("<td>"+value.toString()+"</td>");
            else
              out.print("<td>null</td>");
            
        }
        out.print("</tr>");
      }
      
      out.print("</table>");
    } catch (SQLException ex) {
      Logger lgr = Logger.getLogger(Version.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (st != null) {
          st.close();
        }
        if (con != null) {
          con.close();
        }

      } catch (SQLException ex) {
        Logger lgr = Logger.getLogger(Version.class.getName());
        lgr.log(Level.WARNING, ex.getMessage(), ex);
      }
    }
	  } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

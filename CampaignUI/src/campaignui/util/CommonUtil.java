package campaignui.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.corba.se.impl.util.Version;

public class CommonUtil {
  public static Connection GetConnection() {
    Connection con = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/vrm";
    String user = "root";
    String password = "mysqladmin";
    String driver = "com.mysql.jdbc.Driver";
    try {
      Class.forName(driver).newInstance();
      con = DriverManager.getConnection(url, user, password);
    } catch (Exception ex) {
      Logger lgr = Logger.getLogger(Version.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
      ex.printStackTrace();

    }
    return con;
  }

}

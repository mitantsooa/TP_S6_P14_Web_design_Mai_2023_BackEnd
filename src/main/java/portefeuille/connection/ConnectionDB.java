/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portefeuille.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User_HP
 */
public class ConnectionDB {
    public Connection getConnection(String typedata) throws Exception {
        Connection con = null;
        try {
            if (typedata.compareToIgnoreCase("Oracle") == 0) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            } else if (typedata.compareToIgnoreCase("Postgres") == 0) {
                Class.forName("org.postgresql.Driver");
                String url="jdbc:postgresql://containers-us-west-51.railway.app:5821/railway";
                String user = "postgres";
                String passe="UCs3al07qKriVkzxPWIc";
                con = DriverManager.getConnection(url, user, passe);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return con;
    }
}

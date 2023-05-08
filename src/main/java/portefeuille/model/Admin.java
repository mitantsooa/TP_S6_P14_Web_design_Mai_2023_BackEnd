package portefeuille.model;

import java.sql.Connection;
import java.util.ArrayList;

import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

public class Admin {
    int id;
    String login;
    String password;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Admin> Login(Connection con) throws Exception {
        ArrayList<Admin> admin = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            admin = new GeneriqueDAO().Find(this, con);
            admin.get(0).setLogin("******)ààç_è_-(((==e=é)r)aeàraààe&àéeà&àeà&eà&)");
            admin.get(0).setPassword("*******************************************");
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
        return admin;
    }

}

package portefeuille.model;

import java.sql.Connection;
import java.util.ArrayList;

import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

public class Type {
    int id;
    String type;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Type> getTypes(Connection con) throws Exception {
        ArrayList<Type> types = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            types = new GeneriqueDAO().Find(this, con);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
        return types;
    }

    public void CreateType(Connection con) throws Exception {
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            new GeneriqueDAO().Save(this, con);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null && test == 1) {
                con.close();
            }
        }
    }
}

package portefeuille.model;

import java.sql.Connection;
import java.util.ArrayList;

import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

public class Categorie {
    int id;
    String categorie;

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Categorie> getCategories(Connection con) throws Exception {
        ArrayList<Categorie> res = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            res = new GeneriqueDAO().Find(this, con);
            return res;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
    }
}

package portefeuille.model;

import java.sql.Connection;
import java.util.ArrayList;

import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

public class Article {
    int id;
    String titre;
    String contenue;
    int idcategorie;
    String resume;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getId() {
        return id;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public String getContenue() {
        return contenue;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<Article> getArticles(Connection con) throws Exception {
        ArrayList<Article> articles = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            articles = new GeneriqueDAO().Find(new Article(), con);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
        return articles;

    }

    public void CreateArticle(Connection con) throws Exception {
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
            if (test == 1 && con != null) {
                con.close();
            }
        }
    }
}

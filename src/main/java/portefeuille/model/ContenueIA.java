package portefeuille.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import portefeuille.AllAnnotation.PrimaryKey;
import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

public class ContenueIA {

    @PrimaryKey
    int id;

    String titre;
    String introduction;
    int idtype;
    String motcle;
    String conclure;
    String image;
    String article;
    Date date_creation;

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setConclure(String conclure) {
        this.conclure = conclure;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getArticle() {
        return article;
    }

    public String getConclure() {
        return conclure;
    }

    public int getId() {
        return id;
    }

    public int getIdtype() {
        return idtype;
    }

    public String getImage() {
        return image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getMotcle() {
        return motcle;
    }

    public String getTitre() {
        return titre;
    }

    public void CreateContenue(Connection con) throws Exception {
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

    public ArrayList<ContenueIA> getContenue(int offset, int nbrpage, Connection con) throws Exception {
        ArrayList<ContenueIA> contenues = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            int off = offset * nbrpage;
            String sql = "select * from contenueIA offset " + off + " limit " + nbrpage;
            System.out.print("====>>> : " + sql);
            contenues = new GeneriqueDAO().ExecuteRequete(this, sql, con);
            return contenues;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
        //// return contenues;
    }

    public ListePaginate getListePContenue(int offset, int nbrp) throws Exception {
        ListePaginate ls = new ListePaginate();
        Connection con = null;
        try {
            con = new ConnectionDB().getConnection("postgres");
            ls.setListe(getContenue(offset, nbrp, con));
            ls.setNbrpage(getNBRPAGE(nbrp, "select count(*) from contenueia", con));
            ls.setBrparpage(nbrp);
            return ls;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null) {
                con.close();
            }
        }
        /// return ls;
    }

    public void UpdateContenueIA(Connection con) throws Exception {
        int test = 0;
        try {
            if (con != null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            new GeneriqueDAO().UpdateG(this, con);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (test == 1 && con != null) {
                con.close();
            }
        }
    }

    public ContenueIA getOneContenueIA(Connection con) throws Exception {
        ContenueIA cont = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            cont = ((ArrayList<ContenueIA>) new GeneriqueDAO().Find(this, con)).get(0);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null && test == 1) {
                con.close();
            }
        }
        return cont;
    }

    public int getNBRPAGE(int nbrParPage, String sql, Connection con) throws Exception {
        int nbrPage = 0;
        try {
            /// con = new ConnectionDB().getConnection("postgres");
            int nbrPageAll = ((ArrayList<Count>) new GeneriqueDAO().ExecuteRequete(new Count(),
                    sql, con))
                    .get(0).getCount();
            if (nbrPageAll % nbrParPage != 0) {
                return (nbrPageAll / nbrParPage) + 1;
            }
            return (nbrPageAll / nbrParPage);
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<ContenueIA> RechMulti(Date dateM, Date dateP, int type, int offset, int nbpage, Connection con)
            throws Exception {
        ArrayList<ContenueIA> contenue = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            int off = offset * nbpage;
            String sql = "select * from contenueia where date_creation>'" + dateM + "' and date_creation<'" + dateP
                    + "' and idtype=" + type + " offset " + offset + " limit 2";
            System.out.println(sql);
            contenue = new GeneriqueDAO().ExecuteRequete(this, sql, con);
            return contenue;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null && test == 1) {
                con.close();
            }
        }

    }

    public ListePaginate getRechMulti(Date dateM, Date dateP, int type, int offset, int nbpage) throws Exception {
        ListePaginate ls = new ListePaginate();
        Connection con = null;
        try {
            con = new ConnectionDB().getConnection("postgres");
            ArrayList<ContenueIA> liste = RechMulti(dateM, dateP, type, offset, nbpage, con);
            int nbrP = getNBRPAGE(2,
                    "select count(*) from contenueia where date_creation>'" + dateM + "' and date_creation<'" + dateP
                            + "' and idtype=" + type,
                    con);
            ls.setListe(liste);
            ls.setBrparpage(2);
            ls.setNbrpage(nbrP);
            return ls;
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<ContenueIA> getRechercheN(String mot, Connection con) throws Exception {
        ArrayList<ContenueIA> contenues = null;
        int test = 0;
        try {
            if (con == null) {
                con = new ConnectionDB().getConnection("postgres");
                test = 1;
            }
            String sql = " select * from ContenueIA where lower(titre)  like lower('%" + mot
                    + "%')  or lower(introduction) like lower('%" + mot + "%') or lower(motcle) like lower('%" + mot
                    + "%') or lower(conclure) like lower('" + mot + "')";
            System.out.println("===> : " + sql);
            contenues = new GeneriqueDAO().ExecuteRequete(new ContenueIA(), sql, con);

            return contenues;

        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null && test == 1) {
                con.close();
            }
        }
        /// return contenues;
    }

    public ListePaginate getRECHNORMALE(String mot, int offset) throws Exception {
        ListePaginate liste = new ListePaginate();
        Connection con = null;
        try {
            con = new ConnectionDB().getConnection("postgres");
            ArrayList<ContenueIA> conts = getRechercheN(mot, con);
            liste.setListe(conts);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return liste;
    }

}

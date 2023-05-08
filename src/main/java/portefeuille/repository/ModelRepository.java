package portefeuille.repository;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import portefeuille.connection.ConnectionDB;
import portefeuille.genreiquedao.GeneriqueDAO;

@Repository
public class ModelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * public ArrayList<FicheEncheres> getFicheProduit() {
     * 
     * String sql = "SELECT * FROM ficheencheres";
     * return (ArrayList<FicheEncheres>) jdbcTemplate.query(sql,
     * new BeanPropertyRowMapper<FicheEncheres>(FicheEncheres.class));
     * }
     * 
     * public ArrayList<FicheEncheres> getFicheProduitPAGINE(int offset) {
     * 
     * String sql = "SELECT * FROM ficheencheres offset ? limit 4";
     * return (ArrayList<FicheEncheres>) jdbcTemplate.query(sql,
     * new BeanPropertyRowMapper<FicheEncheres>(FicheEncheres.class),offset);
     * }
     * 
     * public ArrayList<FicheEncheres> rechercheAvancer(FicheEncheres fiche, String
     * motCle) {
     * if (motCle == null) {
     * motCle = "";
     * }
     * String sql = "SELECT * FROM ficheencheres WHERE 1=1 ";
     * if (fiche.getPrix() > 0) {
     * sql += " AND prix=" + fiche.getPrix() + " ";
     * }
     * if (fiche.getDatefin() != null) {
     * sql += " AND datefin='" + fiche.getDatefin() + "' ";
     * }
     * if (fiche.getCategorie() != null) {
     * sql += " AND categorie='" + fiche.getCategorie() + "' ";
     * }
     * if (fiche.getStatus() != null) {
     * sql += " AND status='" + fiche.getStatus() + "' ";
     * }
     * sql += " AND (categorie ILIKE '%" + motCle + "%'" + " OR produit ILIKE '%" +
     * motCle + "%' )";
     * System.out.println(sql);
     * return (ArrayList<FicheEncheres>) jdbcTemplate.query(sql,
     * new BeanPropertyRowMapper<FicheEncheres>(FicheEncheres.class));
     * }
     * 
     * public ArrayList<Categorie> getCategorie() {
     * 
     * String sql = "select * from categorie";
     * ArrayList<Categorie> produit = (ArrayList<Categorie>) jdbcTemplate.query(sql,
     * new BeanPropertyRowMapper<Categorie>(Categorie.class));
     * return produit;
     * }
     */

    // public void insertCategorie(Categorie categorie) {
    // String sql = "insert into categorie(categorie,type_categorie)values(?,?)";
    // jdbcTemplate.update(sql, categorie.getCategorie(),
    // categorie.getType_categorie());
    // }

    // public ArrayList<Categorie> getCategories(int type) {
    // String sql = "select * from categorie where type_categorie=" + type;
    // ArrayList<Categorie> categories = (ArrayList<Categorie>)
    // jdbcTemplate.query(sql,
    // new BeanPropertyRowMapper<Categorie>(Categorie.class));
    // return categories;
    // }

    // public void insertDepense(Depense depense) throws Exception {
    // try {
    // new GeneriqueDAO().Save(depense, null);
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // /// throw e;
    // }
    // }

    // public void insertPersonne(Personne pers) {
    // try {
    // new GeneriqueDAO().Save(pers, null);
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // /// throw e;
    // }
    // }

    // public ArrayList<Personne> getPersonnes() {
    // ArrayList<Personne> personnes = null;
    // try {
    // personnes = new GeneriqueDAO().Find(new Personne(), null);
    // } catch (Exception e) {
    // e.printStackTrace();
    // // TODO: handle exception
    // }

    // return personnes;
    // }

    // public ArrayList<Depense> getDepenses(int offset) {
    // ArrayList<Depense> des = null;
    // try {
    // System.out.print("OFFSET : " + offset);
    // if (offset >= 0) {
    // des = new GeneriqueDAO().ExecuteRequete(new Depense(),
    // " select dep.*,cat.categorie from depense dep join categorie cat on
    // dep.idcategorie=cat.id where etat!=5 offset +"
    // + offset + " limit 3",
    // null);
    // } else {
    // System.out.print(
    // " select dep.*,cat.categorie from depense dep join categorie cat on
    // dep.idcategorie=cat.id where etat!=5 ");
    // des = new GeneriqueDAO().ExecuteRequete(new Depense(),
    // " select dep.*,cat.categorie from depense dep join categorie cat on
    // dep.idcategorie=cat.id where etat!=5 ",
    // null);
    // }

    // } catch (Exception e) {
    // e.printStackTrace();
    // // TODO: handle exception
    // }
    // return des;
    // }

    // public void CreateEmprunt(Emprunt emprunt) throws Exception {
    // Connection con = null;
    // try {
    // emprunt.setMontantC(emprunt.getMontant());
    // emprunt.setDateremboursementC(emprunt.getDateremboursement());
    // Depense depense = new Depense();
    // depense.setDaty(emprunt.getDateemprunt());
    // depense.setDescription(emprunt.getMotif());
    // depense.setType(50);
    // depense.setMontantC(emprunt.getMontant());
    // depense.setPriorite(3);
    // GeneriqueDAO dao = new GeneriqueDAO();
    // con = new ConnectionDB().getConnection("postgres");
    // dao.Save(emprunt, con);
    // dao.Save(depense, con);
    // } catch (Exception e) {
    // e.printStackTrace();
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // }

    // public void CreateReportCaisse(ReportCaisse report) throws Exception {
    // try {
    // new GeneriqueDAO().Save(report, null);
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // }
    // }

    // public ReportCaisse getReport(Connection con) {
    // ReportCaisse rep = null;
    // try {
    // ArrayList<ReportCaisse> LSREP = ((ArrayList<ReportCaisse>) new
    // GeneriqueDAO().ExecuteRequete(
    // new ReportCaisse(),
    // "select * from reportcaisse order by datereport desc limit 1", con));
    // if (LSREP.size() > 0) {
    // rep = LSREP.get(0);
    // return rep;
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // return rep;
    // }

    // public double getMontantSUP(Date daty, Connection con) throws Exception {
    // double montant = 0;
    // try {
    // Somme sum = ((ArrayList<Somme>) new GeneriqueDAO().ExecuteRequete(new
    // Somme(),
    // "select sum(montant) as montant from depense where etat is null and daty>'" +
    // daty + "'",
    // con)).get(0);
    // montant = sum.getMontant();
    // return montant;
    // } catch (ArrayIndexOutOfBoundsException e) {
    // return montant;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // }
    // }

    // public double dernierPourcentage() throws Exception {
    // double pourcent = 0;
    // Connection con = null;
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // Somme sum = ((ArrayList<Somme>) new GeneriqueDAO().ExecuteRequete(new
    // Somme(),
    // "select pourcentage as montant from tauxemprunt order by datetaux desc limit
    // 1",
    // con)).get(0);
    // pourcent = sum.getMontant();
    // } catch (ArrayIndexOutOfBoundsException e) {
    // return pourcent;
    // } catch (Exception e) {
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // return pourcent;
    // }

    // public ArrayList<V_EmpruntDetails> getV_EmpruntDetails(int offset) throws
    // Exception {
    // Connection con = null;
    // ArrayList<V_EmpruntDetails> result = null;
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // /// result = new GeneriqueDAO().Find(emprt, con);
    // if (offset >= 0) {
    // result = new GeneriqueDAO().ExecuteRequete(new V_EmpruntDetails(),
    // "select * from v_detailsMontantEmprunt offset " + offset + " limit 3", con);
    // } else {
    // result = new GeneriqueDAO().Find(new V_EmpruntDetails(), con);
    // }
    // return result;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // }

    // public void getPayement(PayementEmprunt payementEmprunt) throws Exception {
    // Connection con = null;
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // new GeneriqueDAO().Save(payementEmprunt, con);
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // }

    // public double TotalPayer(int idemprunt) throws Exception {
    // double total = 0;
    // Connection con = null;
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // Somme sum = ((ArrayList<Somme>) new GeneriqueDAO().ExecuteRequete(new
    // Somme(),
    // "select sum(montant) as montant from payementemprunt where idemprunt=" +
    // idemprunt, con)).get(0);

    // Emprunt emprt = getEmprunt(idemprunt, con);
    // total = emprt.getMontant() - sum.getMontant();
    // } catch (ArrayIndexOutOfBoundsException ex) {

    // } catch (Exception e) {

    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // return total;
    // }

    // public Emprunt getEmprunt(int idemprunt, Connection con) throws Exception {
    // Emprunt emprt = null;
    // try {
    // emprt = ((ArrayList<Emprunt>) new GeneriqueDAO().ExecuteRequete(new
    // Emprunt(),
    // "select * from emprunt where id=" + idemprunt, con)).get(0);
    // return emprt;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // }
    // }

}

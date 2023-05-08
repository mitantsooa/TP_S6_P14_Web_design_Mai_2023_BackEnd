package portefeuille.repository;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import portefeuille.genreiquedao.GeneriqueDAO;

@Repository
public class DepenseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // public void UpdateDepense(Depense depense) throws Exception {
    // try {
    // new GeneriqueDAO().UpdateG(depense, null);
    // } catch (Exception e) {

    // throw e;
    // // TODO: handle exception
    // }
    // }

    // public void DeleteDepense(Depense depense) throws Exception {
    // try {
    // depense.setEtat(5);
    // new GeneriqueDAO().UpdateG(depense, null);
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // }
    // }

    // public ArrayList<Depense> Search(String desc, Date daty1, Date daty2, int
    // idcategorie) {
    // ArrayList<Depense> deps = null;
    // try {
    // String sql = "select dep.*,cat.categorie from depense dep join categorie cat
    // on dep.idcategorie=cat.id where lower(description) like lower('"
    // + desc + "%') and daty>'" + daty1 + "' and daty<'" + daty2 + "' and
    // idcategorie=" + idcategorie;
    // System.out.println(sql);
    // deps = new GeneriqueDAO().ExecuteRequete(new Depense(), sql, null);
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // return deps;
    // }

}

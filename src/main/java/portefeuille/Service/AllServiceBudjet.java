package portefeuille.Service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import portefeuille.connection.ConnectionDB;

import portefeuille.repository.ModelRepository;

public class AllServiceBudjet {

    @Autowired
    ModelRepository modelRep;

    // public Budjet getBudjetNow() throws Exception {
    // Connection con = null;
    // this.modelRep = new ModelRepository();
    // Budjet bud = new Budjet();
    // Timestamp tm = Timestamp.valueOf(LocalDateTime.now());
    // bud.setDaty(tm);
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // ReportCaisse report = this.modelRep.getReport(con);
    // double montantDep = this.modelRep.getMontantSUP(report.getDatereport(), con);
    // double budjet = report.getMontant() - montantDep;
    // bud.setMontant(budjet);
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // return bud;
    // }

    // public boolean ControlleBudjet(double montant) throws Exception {
    // Connection con = null;
    // this.modelRep = new ModelRepository();
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // ReportCaisse report = this.modelRep.getReport(con);
    // double montantDep = this.modelRep.getMontantSUP(report.getDatereport(), con);
    // double budjet = report.getMontant() - montantDep;
    // System.out.print(report.getMontant() + " <<<<<====>>>>>>" + montantDep);
    // if (budjet < montant) {
    // System.out.print(budjet + " <<<<======>>> " + montant);
    // return false;
    // }
    // return true;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // }

    // public boolean TESTRAPORT(double montantE) throws Exception {
    // this.modelRep = new ModelRepository();
    // Connection con = null;
    // try {
    // con = new ConnectionDB().getConnection("postgres");
    // ReportCaisse report = this.modelRep.getReport(con);
    // double montantDep = this.modelRep.getMontantSUP(report.getDatereport(), con);
    // double montant = report.getMontant() - montantDep;
    // double raport = this.modelRep.dernierPourcentage();
    // double monPourcent = montant * raport;
    // System.out.println(montant + " <<< ===== >>>" + raport);
    // System.out.println(monPourcent + " <<<<==== Volaa ===>>>> " + montantE);
    // System.out.println(montantDep + " <<<<====== Depense ");
    // if (monPourcent >= montantE) {
    // return true;
    // }
    // return false;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // } finally {
    // if (con != null) {
    // con.close();
    // }
    // }
    // }

    // public boolean TESTMONTANT(double montant, int idemprunt) throws Exception {
    // try {
    // double total = new ModelRepository().TotalPayer(idemprunt);
    // /// Emprunt emprunt = new ModelRepository().
    // if (total < montant) {
    // throw new Exception("Votre Montant est Superieur au montant Doit ===> Montant
    // doit : " + total);
    // }
    // return true;
    // } catch (Exception e) {
    // throw e;
    // // TODO: handle exception
    // }
    // }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portefeuille.genreiquedao;

import portefeuille.AllAnnotation.ColonneName;
import portefeuille.AllAnnotation.TableName;

import portefeuille.connection.ConnectionDB;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;
import portefeuille.AllAnnotation.NOTPRIS;
import portefeuille.AllAnnotation.PrimaryKey;
import java.sql.Time;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boaykely
 */
public class GeneriqueDAO {

    /// ================== GETT ONE OBJECT ===============================

    public Object getById(Object obj, Connection con) throws Exception {
        try {
            return this.Find(obj, con).get(0);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    /// ==================COUNT ============================================

    public int CountLine(Object obj, Connection con, int limit) throws Exception {
        int nbr = 0;
        try {
            ArrayList tab = this.Find(obj, con);
            String nbrSTR = Integer.toString(tab.size() / limit);
            if (tab.size() % limit == 0) {
                nbr = tab.size() / limit;
            } else {
                nbr = (tab.size() / limit) + 1;
            }
        } catch (Exception ex) {
            throw ex;
            // Logger.getLogger(GeneriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbr;
    }
    /// +====================Execute ALL REQUETE
    /// =========================================================

    public ArrayList ExecuteRequete(Object obj, String req, Connection con) throws Exception {
        int test = 0;
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList result = null;
        if (con == null) {
            try {
                con = new ConnectionDB().getConnection("postgres");
            } catch (Exception ex) {
                throw ex;
            }
            test = 1;
        }
        try {
            stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
            result = this.CreerObject(obj, res);
            return result;
        } catch (Exception e) {
            throw e;
        }

    }

    /// =========================Insert Generalise
    /// ========================================================

    public void Save(Object object, Connection con) throws Exception {
        int test = 0;
        if (con == null) {
            ConnectionDB connect = new ConnectionDB();
            con = connect.getConnection("postgres");
            test = 1;
        }
        PreparedStatement stmt = null;
        try {
            String str = this.getRequeteInsert(object);
            System.out.println("requete insert" + str);
            stmt = con.prepareStatement(str);
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (test == 1) {
                GenreiqueDAO.closeAll(con, stmt, null);
            }
        }
    }
    /// ====================================================================================================

    /// ========================UPDATE GENERALISER
    /// ========================================================

    public void UpdateG(Object object, Connection con) throws Exception {
        int test = 0;
        if (con == null) {
            ConnectionDB connect = new ConnectionDB();
            con = connect.getConnection("postgres");
            test = 1;
        }
        PreparedStatement stmt = null;
        try {
            String str = this.Update(object);
            System.out.println(str);
            stmt = con.prepareStatement(str);
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (test == 1) {
                GenreiqueDAO.closeAll(con, stmt, null);
            }
        }
    }
    /// ========================SElectGeneraliser
    /// =========================================================

    public ArrayList Find(Object object, Connection con) throws NoSuchMethodException, Exception {
        ArrayList vect = new ArrayList();
        Method[] fonction = this.getFX(object, "set");
        int test = 0;
        if (con == null) {
            ConnectionDB connect = new ConnectionDB();
            con = connect.getConnection("postgres");
            test = 1;
        }
        PreparedStatement stmt = null;
        ResultSet res = null;
        try {
            String req = this.GetRequete(object);
            System.out.println(req);
            stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
            vect = this.CreerObject(object, res);
            // System.out.println(vect.size());
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (test == 1) {
                GenreiqueDAO.closeAll(con, stmt, res);
            }
        }
        return vect;
    }

    /// ===============================DELETE GENERALISER
    /// =====================================================

    public void DELETE(Object object, Connection con) throws Exception {
        int test = 0;
        if (con == null) {
            ConnectionDB connect = new ConnectionDB();
            try {
                con = connect.getConnection("postgres");
            } catch (Exception ex) {
                throw ex;
                // Logger.getLogger(GeneriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            test = 1;
        }
        PreparedStatement stmt = null;
        try {
            String str = this.REQDELETE(object);
            System.out.println(str);
            stmt = con.prepareStatement(str);
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (test == 1) {
                GenreiqueDAO.closeAll(con, stmt, null);
            }
        }
    }

    /// =================================SETTER METHOD
    /// ==========================================================
    public Method[] getFX(Object object, String str) throws NoSuchMethodException {
        String[] nomFX = this.getFonctionGS(object, str);
        Method[] fonction = new Method[nomFX.length];
        for (int i = 0; i < nomFX.length; i++) {
            try {
                fonction[i] = this.getClass().getDeclaredMethod(nomFX[i]);
            } catch (NoSuchMethodException ex) {
                continue;
            }

        }
        return fonction;
    }

    /// ========================STring UPDATE
    /// ==============================================================
    public String UpdateSTR(Object object) throws Exception {
        Vector valeur = this.getVAleur(object);
        String[] str = this.getNameAtt(object);
        String val = "";
        for (int i = 0; i < valeur.size(); i++) {
            if (i < valeur.size() - 1) {
                val += str[i] + "='" + valeur.elementAt(i) + "',";
            } else {
                val += str[i] + "='" + valeur.elementAt(i) + "'";
            }
        }
        return val;
    }

    /// ========================UPDATE GENERALISE
    /// ==========================================================
    public String Update(Object object) throws Exception {
        String str = "";
        Method met = this.getPrimarykey(object);
        // System.err.println("METHOD:"+met.getName());
        String nomCol = this.getPrimary(object, met);
        int id = Integer.parseInt(met.invoke(object).toString());
        String nTab = this.getTableName(object);
        String strUP = this.UpdateSTR(object);
        // if(id!=0){}
        str += "update " + nTab + " set " + strUP + " where " + nomCol + "='" + id + "'";
        return str;
    }

    /// ====================REQUETE UPDATE
    /// ===============================================================

    public String REQDELETE(Object object) throws Exception {
        try {
            String str = "";
            Method met = this.getPrimarykey(object);
            // System.err.println("METHOD:"+met.getName());
            String nomCol = this.getPrimary(object, met);
            int id = Integer.parseInt(met.invoke(object).toString());
            String nTab = this.getTableName(object);
            String strUP = this.UpdateSTR(object);
            // if(id!=0){}
            str += "DELETE FROM " + nTab + " where " + nomCol + "='" + id + "'";
            return str;
        } catch (Exception ex) {
            throw ex;
            // Logger.getLogger(GeneriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// =====================GET PRIMARY
    /// KEY===========================================
    public Method getPrimarykey(Object object) throws Exception {
        Method met = null;
        for (int i = 0; i < object.getClass().getDeclaredFields().length; i++) {
            System.err.println("ERTYUIOP");
            if (object.getClass().getDeclaredFields()[i].getAnnotation(PrimaryKey.class) != null) {
                System.err.println("OPOPERTYUIOP");
                try {
                    String name = object.getClass().getDeclaredFields()[i].getName();
                    System.err.println("ATT" + "get" + GeneriqueDAO.setMajuscule(name));
                    met = object.getClass().getDeclaredMethod("get" + GeneriqueDAO.setMajuscule(name));
                } catch (Exception ex) {
                    System.err.println("ERROR" + ex.getMessage());
                    throw ex;
                    // Logger.getLogger(GeneriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return met;
    }

    /// ================== GET IDPRIMARY KEY
    /// ========================================
    public String getPrimary(Object object, Method met) throws Exception {

        String ColName = "";
        try {
            String attribut = this.SetMinuscule(this.MANALAGET(met.getName()));
            System.err.println("ATTRIBUT" + attribut);
            if (object.getClass().getDeclaredField(attribut).getAnnotation(ColonneName.class) != null) {
                ColName = object.getClass().getDeclaredField(attribut).getAnnotation(ColonneName.class).ColonneName();
            } else {
                ColName = attribut;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return ColName;
    }

    /// ======================CONTRUIRE LE
    /// REQUETE======================================
    public String getRequeteInsert(Object object) throws Exception {
        String str = new String();
        try {
            String nomTable = this.getTableName(object);
            String nomcolone = this.SetColone(object);
            String valeur = this.CREERREQ(object);
            str = "insert into " + nomTable + "(" + nomcolone + ")values(" + valeur + ")";
            System.out.println("Requete:==>" + str);
        } catch (Exception ex) {
            throw ex;
        }
        return str;

    }

    /// =========================MAKA AN'LE CLASS an'LE FIELD
    /// ===================================================
    public Class[] getClassField(Object object) {
        Class[] classField = null;
        try {
            classField = new Class[this.getNameAtt(object).length];
        } catch (Exception ex) {
            ex.printStackTrace();
            // Logger.getLogger(GeneriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int n = 0; n < classField.length; n++) {
            classField[n] = object.getClass().getDeclaredFields()[n].getDeclaringClass();
        }
        return classField;
    }

    /// ====================== Creation Object
    /// ============================================================
    public ArrayList CreerObject(Object object, ResultSet res) throws Exception {
        ArrayList vect = new ArrayList();
        Class[] cl = this.getClassField(object);
        String[] nFX = this.getFonctionGS(object, "set");
        String[] nFXGET = this.getFonctionGS(object, "get");
        String[][] nCol = this.getAttributeName(object);
        // Method[]fonction=this.getFX("set");
        // System.err.println(nCol.length+"NOMCLONNE=== setter"+nFX.length);

        while (res.next()) {
            Object obj = object.getClass().getDeclaredConstructor().newInstance();
            for (int i = 0; i < nFX.length; i++) {
                Class ObjetReturn = object.getClass().getDeclaredMethod(nFXGET[i]).getReturnType();
                // System.out.println((this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType()));
                try {
                    if (ObjetReturn.equals(String.class)) {
                        // System.out.println("grfgvrv"+nCol[i]);
                        try {
                            object.getClass().getDeclaredMethod(nFX[i], String.class).invoke(obj,
                                    res.getString(nCol[i][1]));
                            // break;
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } else if (ObjetReturn.equals(Integer.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], int.class).invoke(obj, res.getInt(nCol[i][1]));
                    } else if (ObjetReturn.equals(Double.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], double.class).invoke(obj,
                                res.getDouble(nCol[i][1]));
                    } else if (ObjetReturn.equals(Float.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], float.class).invoke(obj, res.getFloat(nCol[i][1]));
                    } else if (ObjetReturn.equals(Date.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], Date.class).invoke(obj, res.getDate(nCol[i][1]));
                    } else if (ObjetReturn.equals(int.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], int.class).invoke(obj, res.getInt(nCol[i][1]));
                    } else if (ObjetReturn.equals(double.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], double.class).invoke(obj,
                                res.getDouble(nCol[i][1]));
                    } else if (ObjetReturn.equals(Timestamp.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], Timestamp.class).invoke(obj,
                                res.getTimestamp(nCol[i][1]));
                    } else if (ObjetReturn.equals(Time.class)) {
                        object.getClass().getDeclaredMethod(nFX[i], Time.class).invoke(obj, res.getTime(nCol[i][1]));
                    } else {
                        object.getClass().getDeclaredMethod(nFX[i], null).invoke(obj, null);
                    }
                } catch (Exception ex) {
                    throw ex;
                    /*
                     * ex.printStackTrace();
                     * System.out.println("BDD.ObjetBDD.CreerObject()" + ex);
                     */
                }
            }

            vect.add(obj);
        }
        return vect;
    }

    /// ========================Requete Select
    /// +============================================================
    public String PrepareReq(Object object) throws Exception {
        String str = "";
        String[] NAtt = this.getNameAtt(object);
        Vector vect = this.getVAleur(object);
        for (int i = 0; i < vect.size(); i++) {
            str += " and " + NAtt[i] + "='" + vect.elementAt(i) + "'";
        }
        return str;
    }

    /// ========================REQUETE SELECT
    /// ============================================================
    public String GetRequete(Object object) throws Exception {
        String str = "select * from " + this.getTableName(object) + " where 1=1";
        // System.out.println(str+this.PrepareReq());
        return str + this.PrepareReq(object);
    }
    //// ===============================================================================================================

    /// ========================MANISA ATTRIBUT TSYSY ANNOTATION
    /// ============================================
    public static int getNBR(Class cl) {
        int nbr = 0;
        for (int i = 0; i < cl.getDeclaredFields().length; i++) {
            if (cl.getDeclaredFields()[i].getAnnotation(NOTPRIS.class) == null) {
                nbr++;
            }
        }
        return nbr;
    }
    /// =====================================================================================================
    /// =========================GET COLONNE NAME
    /// ==========================================================

    //// ======================== MAKA NOM ATTRIBUT CLASS
    //// ===================================================
    public String[][] getAttributeName(Object objet) {

        int nbrAttribut = GeneriqueDAO.getNBR(objet.getClass());
        int GNBR = objet.getClass().getDeclaredFields().length;
        String[][] AttributeName = new String[nbrAttribut][2];
        int n = 0;
        for (int i = 0; i < GNBR; i++) {
            if (objet.getClass().getDeclaredFields()[i].getAnnotation(NOTPRIS.class) == null) {
                if (objet.getClass().getDeclaredFields()[i].getAnnotation(ColonneName.class) == null) {
                    AttributeName[n][0] = objet.getClass().getDeclaredFields()[i].getName();
                    AttributeName[n][1] = objet.getClass().getDeclaredFields()[i].getName();
                    n++;
                } else {
                    AttributeName[n][0] = objet.getClass().getDeclaredFields()[i].getName();
                    AttributeName[n][1] = objet.getClass().getDeclaredFields()[i].getAnnotation(ColonneName.class)
                            .ColonneName();
                    n++;
                }
            }
            if (n == nbrAttribut) {
                break;
            }
        }
        return AttributeName;
    }

    /// ===================================================================================================
    /// ================================ MAKA ATTRIBUT !=NULL
    /// =============================================
    /// ===================================================================================================
    /// =================== TEST OBJECT
    /// =====================================================================
    public boolean TESTOBJECT(Object obj) {

        if (obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Float
                || obj instanceof Date || obj instanceof Timestamp || obj instanceof Time) {
            return true;
        }
        return false;
    }

    /// =========================Test OBJECT
    /// ===============================================================
    public boolean Test(Object ob) {
        if (ob instanceof Integer || ob instanceof Double || ob instanceof Long) {
            if (ob instanceof Integer && ((Integer) (ob)).intValue() == 0) {
                return false;
            } else if (ob instanceof Double && ((Double) (ob)).doubleValue() == 0.0) {
                return false;
            } else if (ob instanceof Float && ((Float) (ob)).floatValue() == 0.0) {
                return false;
            } else if (ob instanceof Long && ((Long) (ob)).longValue() == 0.0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /// =================MAKA FONCTION
    /// ========================================================================
    public Vector<Method> getFonctionGS(Object object) throws Exception {
        Vector<Method> fx = new Vector<Method>();
        String[] nomFX = this.getFonctionGS(object, "get");
        for (int i = 0; i < nomFX.length; i++) {
            Object obj = null;
            try {
                // System.out.println("dfghjkl");
                obj = object.getClass().getDeclaredMethod(nomFX[i]).invoke(object);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            /// System.out.println(obj.toString().compareTo("0"));
            if (this.TESTOBJECT(obj)) {
                if (obj != null || this.Test(obj)) {//// || obj.toString().compareTo("0")!=0
                    if (obj.toString().compareTo("0") != 0 && obj.toString().compareTo("0.0") != 0) {
                        // System.out.println((obj.toString().compareTo("0"))+"ii"+this.Test(obj));
                        fx.add(object.getClass().getDeclaredMethod(nomFX[i]));
                        // i++;
                    }
                }
            }

        }
        return fx;
    }
    /// =========================MakA Nom Fonction getter na setter
    /// =============================================

    public String[] getFonctionGS(Object obj, String type) {
        String[][] NameAttribut = this.getAttributeName(obj);
        String[] fxName = new String[NameAttribut.length];
        for (int i = 0; i < NameAttribut.length; i++) {
            fxName[i] = type + GeneriqueDAO.setMajuscule(NameAttribut[i][0]);
        }
        return fxName;
    }

    /// =======================MANOVA MAJUSCULE
    /// ================================================================
    public static String setMajuscule(String str) {
        char[] letter = str.toCharArray();
        letter[0] = Character.toUpperCase(letter[0]);
        return String.copyValueOf(letter);
    }

    /// ================SETValeur========================================================
    public Vector getVAleur(Object object) throws Exception {
        Vector vect = new Vector();
        Vector<Method> fx = this.getFonctionGS(object);
        // System.out.println(fx[0].getName());
        // String[]met=this.getFonctionGS("get");
        for (int i = 0; i < fx.size(); i++) {
            try {
                vect.add(fx.elementAt(i).invoke(object));
            } catch (Exception ex) {
                continue;
            }

        }
        return vect;
    }

    /// ================GET TABLE NAME
    /// ==================================================
    public String getTableName(Object object) {
        String str = "";
        Class ClObject = object.getClass();
        if (ClObject.getDeclaredAnnotation(TableName.class) == null) {
            str = ClObject.getSimpleName();
        } else {
            str = object.getClass().getAnnotation(TableName.class).TableName();
        }
        return str;
    }

    /// =================================================================================
    /// ======================GET NAME
    /// =====================================================================
    public String[] getNAME(Object object) throws Exception {
        // System.out.println(this.getFonctionGS("get").length);
        String[] str = new String[this.getFonctionGS(object).size()];
        Vector<Method> string = this.getFonctionGS(object);
        for (int n = 0; n < string.size(); n++) {
            // System.out.println(string[n].getName()+"opopoppopopopp");
            if (string.elementAt(n) != null) {
                str[n] = string.elementAt(n).getName();
            }
        }
        return str;
    }

    /// =======================Manala GET
    /// ====================================================================
    public String MANALAGET(String str) {

        char[] lschar = str.toCharArray();
        char[] ls = new char[lschar.length - 3];
        for (int i = 3, n = 0; i < lschar.length; i++) {
            ls[n] = lschar[i];
            n++;
        }
        return String.copyValueOf(ls);
    }

    /// =======================String Attribut NAME
    /// =========================================================
    public String[] getNameAtt(Object object) throws Exception {
        String[] str = new String[this.getVAleur(object).size()];
        String[] string = this.getNAME(object);
        try {
            for (int i = 0, n = 0; i < str.length; i++) {
                if (string[i] != null) {

                    if (object.getClass().getDeclaredField(this.SetMinuscule(this.MANALAGET(string[i])))
                            .getAnnotation(ColonneName.class) == null) {
                        str[n] = this.MANALAGET(string[i]);
                    } else {
                        str[n] = object.getClass().getDeclaredField(this.SetMinuscule(this.MANALAGET(string[i])))
                                .getAnnotation(ColonneName.class).ColonneName();
                    }
                    n++;
                }

            }
        } catch (Exception ex) {
            throw ex;
        }
        return str;
    }

    /// =======================String Attribut NAME
    /// =========================================================
    public String[] getALLCOL(Object object) throws Exception {
        String[] str = new String[this.getVAleur(object).size()];
        String[] string = this.getNAME(object);
        try {
            for (int i = 0, n = 0; i < str.length; i++) {
                if (string[i] != null) {

                    if (object.getClass().getDeclaredField(this.SetMinuscule(this.MANALAGET(string[i])))
                            .getAnnotation(ColonneName.class) == null) {
                        str[n] = this.MANALAGET(string[i]);
                    } else {
                        str[n] = object.getClass().getDeclaredField(this.SetMinuscule(this.MANALAGET(string[i])))
                                .getAnnotation(ColonneName.class).ColonneName();
                    }
                    n++;
                }

            }
        } catch (Exception ex) {
            throw ex;
        }
        return str;
    }
    /// ================ PREPARE COLONNE NAME
    /// ============================================================================

    /// public Field[] getAtt
    public String SetColone(Object object) throws Exception {
        String str = "";
        // String[][] att = null;
        String[] atr = null;
        try {
            atr = this.getNameAtt(object);
            // att = this.getAttributeName(object);
            for (int i = 0; i < atr.length; i++) {
                if (i < atr.length - 1) {
                    // System.out.println(att[i]+"sdfghugf");
                    // str+=att[i][1]+",";
                    str += atr[i] + ",";
                } else {
                    str += atr[i];
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return str;
    }
    /// ==========================================================================================================

    /// ================ CREER REQ
    /// =================================================================
    public String CREERREQ(Object object) throws Exception {
        String str = "";
        Vector vect = this.getVAleur(object);
        try {
            for (int n = 0; n < vect.size(); n++) {
                if (n < vect.size() - 1) {
                    if (vect.elementAt(n) instanceof Double || vect.elementAt(n) instanceof Float) {
                        str += vect.elementAt(n).toString() + ",";
                    } else {
                        str += "'" + vect.elementAt(n) + "',";
                    }
                } else {
                    if (vect.elementAt(n) instanceof Double || vect.elementAt(n) instanceof Float) {
                        str += vect.elementAt(n);
                    } else {
                        str += "'" + vect.elementAt(n) + "'";
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("BDD.ObjetBDD.CREERREQ()" + ex.getMessage());
        }
        return str;
    }

    /// ==========================MAMADIKA
    /// MINUSCULE==========================================================
    public String SetMinuscule(String str) {
        char[] letter = str.toCharArray();
        letter[0] = Character.toLowerCase(letter[0]);
        return String.copyValueOf(letter);
    }

    /// =====================================================================================================
    /*
     * public static void main(String[] args) throws Exception {
     * // TODO code application logic here
     * GeneriqueDAO gen = new GeneriqueDAO();
     * ClassTest test = new ClassTest();
     * test.setId(5);
     * test.setIdTest(12);
     * test.setNom("Tanjona");
     * test.setPrenom("Miaro");
     * try {
     * System.err.println("REQUETE:" + gen.REQDELETE(test));
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * 
     * }
     */

    // @Override
    // public ArrayList Find(Object object, Connection con) throws Exception {
    // throw new UnsupportedOperationException("Not supported yet."); //To change
    // body of generated methods, choose Tools | Templates.
    // }

}

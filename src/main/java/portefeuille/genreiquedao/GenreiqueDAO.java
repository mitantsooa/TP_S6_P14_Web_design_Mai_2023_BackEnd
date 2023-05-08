/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portefeuille.genreiquedao;

import portefeuille.connection.ConnectionDB;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import portefeuille.AllAnnotation.NOTPRIS;

/**
 *
 * @author boaykely
 */
public class GenreiqueDAO {

    /**
     * @param args the command line arguments
     */
    
    
/**
 *
 * @author boaykely
 */
      /* TOLIST CREATION SELECT GENERALISER ET DELETE GENERALISER DEMAIN 
       GENERALISER CLASS MERE ET FILLE 
    
       Misy Amboarina Ao Am'le Creation Object
    
    */
    
    ///=========================Insert Generalise ========================================================
    
    public void CreateG(Connection con) throws Exception{
        int test=0;
        if(con==null){
            ConnectionDB connect=new ConnectionDB();
            con=connect.getConnection("postgres");
            test=1;
        }
        PreparedStatement stmt=null;
        try{
            String str=this.getRequeteInsert();
            System.out.println("requete insert"+str);
            stmt=con.prepareStatement(str);
            stmt.executeUpdate();
        }catch(Exception ex){throw ex;}
        finally{
            if(test==1){GenreiqueDAO.closeAll(con,stmt,null);}
        }
    }
    ///====================================================================================================
    
    ///========================UPDATE GENERALISER ========================================================
    
   /* public void UpdateG(Connection con) throws Exception{
         int test=0;
        if(con==null){
            ConnectionDB connect=new ConnectionDB();
            con=connect.getConnection("postgres");
            test=1;
        }
        PreparedStatement stmt=null;
        try{
            String str=this.Update();
            System.out.println(str);
            stmt=con.prepareStatement(str);
            stmt.executeUpdate();
        }catch(Exception ex){throw ex;}
       finally{
            if(test==1){GenreiqueDAO.closeAll(con,stmt,null);}



        }
    }*/
    ///=====================SETTER GENERALISER ==========================================================
    
    public void setter(Object obj){
        if(obj instanceof String){
            
        }
    } 
    
    ///====================== Creation Object ============================================================
    
    public Vector CreerObject(ResultSet res) throws Exception{
        Vector vect =new Vector();
        Class[]cl=this.getClassField();
        String[]nFX=this.getFonctionGS("set");
        String[]nFXGET=this.getFonctionGS("get");
        String[]nCol=this.getAttributeName();
        //Method[]fonction=this.getFX("set");
       
        while(res.next()){
            Object obj=this.getClass().getDeclaredConstructor().newInstance();
            for(int i=0;i<nFXGET.length;i++){
                //System.out.println((this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType()));
           try{
                if((this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType()).equals(String.class)){
                   // System.out.println("grfgvrv"+nCol[i]);
                    try{
                    this.getClass().getDeclaredMethod(nFX[i],String.class).invoke(obj,res.getString(nCol[i]));
                    //break;
                    }catch(Exception ex){System.out.println(ex);}
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(Integer.class)){
                    this.getClass().getDeclaredMethod(nFX[i],int.class).invoke(obj,res.getInt(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(Double.class)){
                    this.getClass().getDeclaredMethod(nFX[i],double.class).invoke(obj,res.getDouble(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(Float.class)){
                     this.getClass().getDeclaredMethod(nFX[i],float.class).invoke(obj,res.getFloat(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(Date.class)){
                     this.getClass().getDeclaredMethod(nFX[i],Date.class).invoke(obj,res.getDate(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(int.class)){
                     this.getClass().getDeclaredMethod(nFX[i],int.class).invoke(obj,res.getInt(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(double.class)){
                     this.getClass().getDeclaredMethod(nFX[i],double.class).invoke(obj,res.getDouble(nCol[i]));
                }else if(this.getClass().getDeclaredMethod(nFXGET[i]).getReturnType().equals(Timestamp.class)){
                     this.getClass().getDeclaredMethod(nFX[i],Timestamp.class).invoke(obj,res.getTimestamp(nCol[i]));
                }else{
                    this.getClass().getDeclaredMethod(nFX[i],null).invoke(obj,null);
                }
                 }catch(Exception ex){System.out.println("BDD.ObjetBDD.CreerObject()"+ex);}
                }
           
            vect.add(obj);
        }
        return vect;
    }
    
    ///========================SElectGeneraliser =========================================================
    
    public Vector Select(Connection con) throws NoSuchMethodException, Exception{
       Vector vect=new Vector();
       Method[]fonction=this.getFX("set");
       int test=0;
       if(con==null){
            ConnectionDB connect=new ConnectionDB();
            con=connect.getConnection("postgres");
            test=1;
        }
       PreparedStatement stmt=null;
       ResultSet res=null;
       try{
           String req=this.GetRequete();
           System.out.println(req);
           stmt=con.prepareStatement(req);
           res=stmt.executeQuery();
           vect=this.CreerObject(res);
           //System.out.println(vect.size());
       }catch(Exception ex){throw ex;}
       finally{
           if(test==1){GenreiqueDAO.closeAll(con,stmt,res);}
       }       
       return vect;
    }
    
    ///========================Requete Select +============================================================
    
    public String PrepareReq() throws Exception{
        String str="";        
        String[] NAtt=this.getNameAtt();
        Vector vect=this.getVAleur();
        for(int i=0;i<vect.size();i++){
            str+=" and "+NAtt[i]+"='"+vect.elementAt(i)+"'";
        }
        return str;
    }
    
    ///========================REQUETE SELECT ============================================================
    
    public String GetRequete() throws Exception{
        String str="select * from "+this.getClass().getSimpleName()+" where 1=1";
       // System.out.println(str+this.PrepareReq());
        return str+this.PrepareReq();
    }
    
    ///========================UPDATE GENERALISE ==========================================================
    
    /*public String Update() throws Exception{
        String str="";
        int id=this.getId();
        String nTab=this.getClass().getSimpleName();
        String strUP=this.UpdateSTR();
        //if(id!=0){}
        str+="update "+nTab+" set "+strUP+" where id='"+id+"'";
        return str;
    }*/
    
    ///=======================Manala GET ====================================================================
    
    public String MANALAGET(String str){
        
        char[]lschar=str.toCharArray();
        char[]ls=new char[lschar.length-3];
        for(int i=3,n=0;i<lschar.length;i++){
            ls[n]=lschar[i];
            n++;
        }
        return String.copyValueOf(ls);
    }
    
    ///======================GET NAME =====================================================================
    
    public String[] getNAME() throws Exception{
        //System.out.println(this.getFonctionGS("get").length);
        String[]str=new String[this.getFonctionGS().size()];
        Vector<Method> string=this.getFonctionGS();
        for(int n=0;n<string.size();n++){
             //System.out.println(string[n].getName()+"opopoppopopopp");
            if(string.elementAt(n)!=null){
                str[n]=string.elementAt(n).getName();
            }
        }
        return str;
    }
    
    ///=======================String Attribut NAME =========================================================
    
    public String[] getNameAtt() throws Exception{
        String[]str=new String[this.getVAleur().size()];
        String[] string=this.getNAME();
        for(int i=0,n=0;i<str.length;i++){
            if(string[i]!=null){
                 System.out.println("ATTTTT"+string[i]);
                 str[n]=this.MANALAGET(string[i]);
                 n++;
            }
           
        }return str;
    }
    
    ///========================STring UPDATE ==============================================================
    
    public String UpdateSTR() throws Exception{
        Vector valeur=this.getVAleur();
        String[] str=this.getNameAtt();
        String val="";
        for(int i=0;i<valeur.size();i++){
            if(i<valeur.size()-1){
                val+=str[i]+"='"+valeur.elementAt(i)+"',";
            }else{
                val+=str[i]+"='"+valeur.elementAt(i)+"'";
            } 
        }
        return val;
    }
    
    ///========================MANISA ATTRIBUT TSYSY ANNOTATION ============================================
    
    public static int getNBR(Class cl){
        int nbr=0;
        for(int i=0;i<cl.getDeclaredFields().length;i++){
            if(cl.getDeclaredFields()[i].getAnnotation(NOTPRIS.class)==null){
                nbr++;
            }
        }
        return nbr;
    }
    
    ////======================== MAKA NOM ATTRIBUT CLASS ===================================================
    public String[] getAttributeName(){
        
        int nbrAttribut=GenreiqueDAO.getNBR(this.getClass());
        int GNBR=this.getClass().getDeclaredFields().length;
        String[] AttributeName=new String[nbrAttribut];
        int n=0;
        for(int i=0;i<GNBR;i++){
            if(this.getClass().getDeclaredFields()[i].getAnnotation(NOTPRIS.class)==null){
                AttributeName[n]=this.getClass().getDeclaredFields()[i].getName();
                n++;
            }if(n==nbrAttribut){break;}
        }
        return AttributeName;
    }
    ///=======================MANOVA MAJUSCULE ================================================================
    
    public static String setMajuscule(String str){
        char[] letter=str.toCharArray();
        letter[0]=Character.toUpperCase(letter[0]);
        return String.copyValueOf(letter);
    }
    
    ///=========================MakA Nom Fonction getter na setter =============================================
    
    public String[] getFonctionGS(String type){
        String[]NameAttribut=this.getAttributeName();
        String[]fxName=new String[NameAttribut.length];
        for(int i=0;i<NameAttribut.length;i++){
            fxName[i]=type+this.setMajuscule(NameAttribut[i]);
        }
        return fxName;
    }
    ///=========================MAKA AN'LE CLASS an'LE FIELD ===================================================
    
    public Class[] getClassField(){
        Class[]classField=new Class[this.getAttributeName().length];
        for(int n=0;n<classField.length;n++){
            classField[n]=this.getClass().getDeclaredFields()[n].getDeclaringClass();
        }
        return classField;
    }
    
    ///=========================Test OBJECT ===============================================================
    
    public boolean Test(Object ob){
        if(ob instanceof Integer || ob instanceof Double || ob instanceof Long){
            if(ob instanceof Integer && ((Integer)(ob)).intValue()==0){
                return false;
            }else if(ob instanceof Double && ((Double)(ob)).doubleValue()==0.0){
                return false;
            }else if(ob instanceof Float && ((Float)(ob)).floatValue()==0.0){
                return false;
            }else if(ob instanceof Long && ((Long)(ob)).longValue()==0.0){return false;}
            else{return true;}
        }else{
            return false;
        }
    }
    
    ///=====================New REQUETE ===================================================================
    
    
    public String ReqINSERT(){
        String str="";
        
        
        
        return str;
    }
    
    ///================GET FIELD ============================================================================
    
   /// public Field[] getAtt
    public String SetColone(){
        String str="";
        String[]att=null;
        try{
            att=this.getNameAtt();
        }catch(Exception ex){System.out.println(ex.getMessage());}
        System.out.println(att.length+"dsdfghjkl;");
        for(int i=0;i<att.length;i++){
            if(i<att.length-1){
                //System.out.println(att[i]+"sdfghugf");
                str+=att[i]+",";
            }else{
                str+=att[i];
            }
        }
        return str;
    }
    
    ///=================================SETTER METHOD ==========================================================
    
    public Method[] getFX(String str) throws NoSuchMethodException{
        String[]nomFX=this.getFonctionGS(str);
        Method[] fonction=new Method[nomFX.length];
        for(int i=0;i<nomFX.length;i++){
            try{
                 fonction[i]=this.getClass().getDeclaredMethod(nomFX[i]);
            }catch(NoSuchMethodException ex){continue;}
          
        }
        return fonction;
    }
    
    ///=================== TEST OBJECT =====================================================================
    
    public boolean TESTOBJECT(Object obj){
        
        if(obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Float || obj instanceof Date || obj instanceof Timestamp){
            return true;
        }
        return false;
    }
    
    ///=================MAKA FONCTION ========================================================================
    
    public Vector<Method> getFonctionGS() throws Exception{
        Vector<Method> fx=new Vector<Method>();
        String[]nomFX=this.getFonctionGS("get");
        //for(int i=0;i<nomFX.length;i++){
        //int i=0;
       for(int i=0;i<nomFX.length;i++){
            //System.out.println("BDD.ObjetBDD.getFonctionGS()"+fx.length);
            Object obj=null;
            try{
                 //System.out.println("dfghjkl");
                 obj=this.getClass().getDeclaredMethod(nomFX[i]).invoke(this);
            }catch(Exception ex){System.out.println(ex);}
            ///System.out.println(obj.toString().compareTo("0"));
            if(this.TESTOBJECT(obj)){
                if(obj!=null || this.Test(obj)){////|| obj.toString().compareTo("0")!=0
                    if(obj.toString().compareTo("0")!=0 && obj.toString().compareTo("0.0")!=0){
                         //System.out.println((obj.toString().compareTo("0"))+"ii"+this.Test(obj));
                        fx.add(this.getClass().getDeclaredMethod(nomFX[i]));
                       // i++;
                    }
                }
            }
            
        }
        return fx;
    }
    ///================ CREER REQ =================================================================
    
    public String CREERREQ() throws Exception{
        String str="";
        Vector vect=this.getVAleur();
        try{
            for(int n=0;n<vect.size();n++){
                if(n<vect.size()-1){
                    if(vect.elementAt(n) instanceof Double || vect.elementAt(n) instanceof Float){
                        str+=vect.elementAt(n).toString()+",";
                    }else{
                        str+="'"+vect.elementAt(n)+"',";
                    }
                }else{
                    if(vect.elementAt(n) instanceof Double || vect.elementAt(n) instanceof Float){
                        str+=vect.elementAt(n);
                    }else{
                        str+="'"+vect.elementAt(n)+"'";
                    }
                }
            }
        }catch(Exception ex){System.out.println("BDD.ObjetBDD.CREERREQ()"+ex.getMessage());}
        return str;
    }
    
    ///close Valeur ===========================================================
    
    public static void closeAll(Connection con,PreparedStatement stmt,ResultSet res) throws SQLException{
         if(res!=null){res.close();}
         if(stmt!=null){stmt.close();}
         if(con!=null){con.close();}
    }
    
    ///================SETValeur========================================================
    
    public Vector getVAleur() throws Exception{
        Vector vect=new Vector();
        Vector<Method> fx=this.getFonctionGS();
//        System.out.println(fx[0].getName());
       // String[]met=this.getFonctionGS("get");
        for(int i=0;i<fx.size();i++){
            try{
                vect.add(fx.elementAt(i).invoke(this));
            }catch(Exception ex){continue;}
           
        }
        return vect;
    }
    
    ///======================CONTRUIRE LE REQUETE======================================
    
    public String getRequeteInsert() throws Exception{
         String str=new String();
         try{
        String nomTable=this.getClass().getSimpleName();
        //Vector<Method>fx=this.getFonctionGS();
       
        String nomcolone=this.SetColone();
        String valeur=this.CREERREQ();
        str="insert into "+nomTable+"("+nomcolone+")values("+valeur+")";
        System.out.println(str+"dfghjk");
        }catch(Exception ex){throw ex;}
        return str;
        
    }
    
    ///========================REturn ResultSet=====================================
    
    
    ////======================DATE ACTUALLe==========================================================
    public static Date getDateActus(Connection con) throws Exception{
        if(con==null){
            ConnectionDB connect=new ConnectionDB();
            con=connect.getConnection("postgres");
        }
        Date dt=null;
        PreparedStatement stmt=null;
        ResultSet res=null;
        try{
            stmt=con.prepareStatement("select current_date");
            res=stmt.executeQuery();
            while(res.next()){
                dt=Date.valueOf(res.getString("current_date"));
                break;
            }
        }catch(Exception ex){throw ex;}
        return dt;
    }
    ///============== GET RETURN TUYPE ================================================    
    public Class[] returnType(String[] input) throws Exception{
     Class[]classe=new Class[input.length];
        try {
            Method[]fonction=this.getMethod("get", input,null);
            
            for(int i=0;i<fonction.length;i++){
                classe[i]=fonction[i].getReturnType();
            }
        } catch (Exception ex) {
            throw ex;
           // Logger.getLogger(ObjetBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
     return classe;
    }
    
    ///=====================GET FONCTION STRING[] ===================================
    
    public Method[] getMethod(String type,String[] input,Class[] classe) throws Exception{
        Method[]fx=new Method[input.length];
        Class cl=this.getClass();
        for(int i=0;i<input.length;i++){
            try {
                if(classe!=null){
                    fx[i]=cl.getDeclaredMethod(type+GenreiqueDAO.setMajuscule(input[i]),classe[i]);
                }else{
                    fx[i]=cl.getDeclaredMethod(type+GenreiqueDAO.setMajuscule(input[i]));
                }
                
            } catch (Exception ex) {
                throw ex;
                //Logger.getLogger(ObjetBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
              
        }
        return fx;
    }
    
    ///=================SETTER GENERALISER ===========================================
    
    public void setterGeneraliser(String[]input,Object[]value) throws Exception{
        //Method[]fonction;
        Class[] classe=this.returnType(input);
        Method[]fx=this.getMethod("set", input, classe);
        for(int i=0;i<fx.length;i++){
            fx[i].invoke(this,value[i]);
        }
    }
    ///================== Class String[]======================================
    
    
    public static Class[] getSTR(String[] str){
        Class[] classe=new Class[str.length];
        
        for(int i=0;i<classe.length;i++){
            classe[i]=str[0].getClass();
        }
        return classe;
    }
    ///=================INSERT ALL ==============================================================
    
    
    ///=================UPDATE ALL ===============================================================
    
    /*public void UpdateTous(int[]id) throws Exception{
        Connection con=null;
        try {
            con = new ConnectionDB().getConnection("postgres");
            for(int i=0;i<id.length;i++){
                System.err.println(id[i]);
                this.setId(id[i]);
                //this.set
                this.UpdateG(con);
            }
        } catch (Exception ex) {
            throw ex;
        }finally{ObjetBDD.closeAll(con, null,null);}
    }*/
    
    ///=================  TRANSFORME JSON EN INT[] ================================================
    
    public static int[] ParseJSON(String json){
        int[] id=new int[json.split(",").length];
        System.err.println(json.split(",")[0]+"ertyuiop");
        for(int i=0;i<id.length;i++){
            System.err.println(json.split(",")[i]+"ertyuiop");
            id[i]=Integer.parseInt(json.split(",")[i]);
        }
        return id;
    }
    ///============================================================================================
    
    public  double getHeure(Timestamp st1,Timestamp st2){
        double time=st1.getTime()-st2.getTime();
        System.out.println(st1+"tanana.ObjetBDD.getHeure()"+time+"METYYYYY"+st2);
        System.out.println("tanana.ObjetBDD.getHeure()"+time/3600000);
        return time/3600000;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

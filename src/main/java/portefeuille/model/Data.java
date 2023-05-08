package portefeuille.model;

import java.util.ArrayList;
import java.util.Set;

public class Data {
    ArrayList liste;
    String mess;
    String status;
    Object obj;
    int nbrpage;

    public void setNbrpage(int nbrpage) {
        this.nbrpage = nbrpage;
    }

    public int getNbrpage() {
        return nbrpage;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setListe(ArrayList liste) {
        this.liste = liste;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList getListe() {
        return liste;
    }

    public String getMess() {
        return mess;
    }

    public String getStatus() {
        return status;
    }
}
